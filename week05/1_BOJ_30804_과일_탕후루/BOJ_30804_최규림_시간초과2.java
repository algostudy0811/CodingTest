package week05;

import java.io.*;
import java.util.*;

/**
 * 1) 왼쪽에서 오른쪽 방향으로 탐색하면서, 과일의 종류가 바뀌는 index를 탐색
 * 2) 해당 index 만으로 2가지의 과일 종류가 나오는 케이스 확인
 * -> ex) 1 2 1 1 1 1 1 3 3 3
 * -> leftIndexList = [0, 1, 2, 7]
 * -> left = 0, right = 1, temp = 7 
 * -> leftNum = 1, rightNum = 2, tempNum = 3f
 * -> 1,2,1 이 부분에서 과일의 종류가 바뀌는 횟수가 2번이지만 3가지 종류가 아닌 2가지 종류이므로
 * -> leftNum, rightNum 에 과일 종류를 할당 후, tempNum에 새로운 종류가 나올 때까지 탐색
 * -> 새로운 종류 과일 index(temp) 가 나오면, leftIndex 부터 해당 index까지가 구간
 * 3) 시간 초과 ;;
 * -> 생각해보니 leftIndexList의 크기가 크면 O(n^2)가 될 수 있었음
 */
public class BOJ_30804_최규림_시간초과2 {

	static int N;
	static int[] arr;
	static boolean[] visited = new boolean[10];
	static int typeCnt = 0;
	static List<Integer> leftIndexList = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken()); 
			arr[i] = num;
			if(!visited[num]) {
				visited[num] = true;
				typeCnt += 1;
			}
		}
		
		// 이미 종류가 2가지면 출력 후 리턴
		if(typeCnt <= 2) {
			System.out.println(N);
			return;
		}
				
		// 과일 종류가 바뀌는 순간의 Index 별도 저장
		int leftNum = arr[0];
		leftIndexList.add(0);
		for(int i=1; i<N; i++) {
			if(arr[i] != leftNum) {
				leftNum = arr[i];
				leftIndexList.add(i);
			}
		}
				
		
		int answer = -1;
		for(int i=0; i<leftIndexList.size() - 1; i++) {
			// 과일 탐색 시작지점(a 과일의 시작)
			int leftIndex = leftIndexList.get(i);
			
			// 모든 과일을 탐색한 경우
			if(i+1 == leftIndexList.size() - 1) {
				// 제일 마지막부터 a과일 시작지점까지의 구간
				answer = Math.max(answer, N-leftIndex);
				break;
			}
						
			answer = Math.max(answer, findRightIndexAndAnswer(leftIndex, i));			
		}
		System.out.println(answer);
	}

	// a 과일과 b 과일을 구하고 c 과일(새로운 과일 종류)이 발견되는 구간을 찾는 메서드
	static int findRightIndexAndAnswer(int leftIndex, int i) {		
		int leftNum = arr[leftIndex]; // 과일 a
		int rightNum = -1; // 과일 b 초기화
		
		// 과일 b 찾기
		for(int j=i+1; j<leftIndexList.size(); j++) {
			// 과일의 종류가 바뀌는 시점들 탐색
			int rightIndex = leftIndexList.get(j);
			// 과일 a와 다른 종류라면 b로 지정
			if(arr[rightIndex] != leftNum) {
				rightNum = arr[rightIndex];
				i = j;
				break;
			}
		}
		
		// 모든 과일이 a 인 경우
		if(rightNum == -1) return N-1-leftIndex+1;
		
		// a,b와 다른 종류의 과일인 c 찾기
		for(int j=i+1; j<leftIndexList.size(); j++) {
			int tempIndex = leftIndexList.get(j);
			// a,b 둘다 아닌 경우, 해당 구간이 마지막 구간이므로 (a,b) 길이 구하기
			if(arr[tempIndex] != leftNum && arr[tempIndex] != rightNum) {
				return (tempIndex - 1) - leftIndex + 1;
			}
		}
		
		// 모두 a,b 인 경우
		return N-leftIndex;
	}
}
