package week04;

import java.io.*;
import java.util.*;

/**
 * simulate_1
 * - set과 while문 활용 + 위에서 아래로 탐색
 * - 시간 초과
 * 
 * simulate_2
 * - set과 while문 활용 + 아래서 위로 탐색
 * - 94108KB, 552ms
 * 
 * simulate_3
 * - boolean 배열과 while문 활용, 아래에서 위로 탐색
 * - 46516KB, 420ms
 * 
 * -- 메모리 사용
 * HashSet 
 * -> 객체 참조 및 해시 코드를 저장하므로, 메모리 사용량이 비교적 큼
 * -> 2^20 개의 값이 들ㄹ어갈 수 있으므로, 메모리 오버헤드 크게 작용할 수 있음
 * 
 * boolean 배열
 * -> 메모리 사용량이 더 효율적, 범위가 고정되어 있다면 메모리 사용량에서 boolean 배열이 압도적으로 효율적
 * 
 * -- 시간 효율성
 * HashSet
 * -> 삽입 및 조회 평균적으로 0(1), 충돌이 많아질 경우 최대 O(n)
 * 
 * boolean 배열
 * -> O(1), 단 배열 크기가 클 경우 초기화에 추가적인 시간 필요
 * 
 * -- 결론
 * -> 범위가 고정되어 있고, 메모리 효율성이 중요한 경우 -> boolean 배열이 훨씬 적합함
 * -> 범위가 동적으로 변하거나 값의 범위가 매우 큰 경우 -> HashSet을 사용하는 것이 유연성이 높고 메모리 사용량이 더 적을 수 있음(실제로 저장된 값만 관리하기 때문)
 */
public class BOJ_20364_최규림 {
	
	static int N, Q;	
	static Set<Integer> set = new HashSet<Integer>();
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		
		for(int i=0; i<Q; i++) {
			int target = Integer.parseInt(br.readLine());
			sb.append(String.valueOf(simulate_3(1, target))).append('\n');
		}						
		
		System.out.println(sb.toString());
	}
	
	// boolean 배열과 while문 활용, 아래에서 위로 탐색
	static int simulate_3(int start, int target) {
		int depth = target;
		int result = 0;
		
		while(depth != 0) {
			// 중간에 점유된 곳 확인
			// 처음 마주치는 점유된 땅을 찾아야 하기 때문에, break 하지 않고 while문이 종료될 때까지 확인 필수
			if(visited[depth]) result = depth;
			// 위로 올라감
			depth /= 2;
		}

		// 루트까지 올라간 경우, 중간에 점유된 곳이 없었으므로, 원하는 땅 점유 처리
		if(result == 0) visited[target] = true;
		return result;
	}
	
	// set과 while문 활용, 아래에서 위로 탐색
	static int simulate_2(int start, int target) {
		int depth = target;
		int result = 0;
		
		while(depth != 0) {
			// 중간에 점유된 곳 확인
			// 처음 마주치는 점유된 땅을 찾아야 하기 때문에, break 하지 않고 while문이 종료될 때까지 확인 필수
			if(set.contains(depth)) result = depth;
			// 위로 올라감
			depth /= 2;
		}
		
		// 루트까지 올라간 경우, 중간에 점유된 곳이 없었으므로, 원하는 땅 점유 처리
		if(result == 0) set.add(target);
		return result;
	}
	
	// set 과 while문 활용, 위에서 아래로 탐색
	static int simulate_1(int start, int target) {
		while(true) {
			int left = start*2;
			int right = start*2+1;
			
			// 원하는 땅을 만난 경우
			if(left == target || right == target) {
				// 원하는 땅 점유 처리 후에 0 리턴
				set.add(target);
				return 0;			
			}
			
			int leftRight = left * 2 + 1;
			int rightLeft = right * 2;
			
			// 왼쪽 노드로 이동
			if(target <= leftRight) {
				// 왼쪽 노드를 지나가지 못한다면 처음 마주치는 점유된 번호 리턴
				if(set.contains(left)) return left;
				start = left;
			} 
			// 오른쪽 노드로 이동		
			else if(target >= rightLeft) {
				// 오른쪽 노드를 지나가지 못한다면 처음 마주치는 점유된 번호 리턴
				if(set.contains(right)) return right;
				start = right;
			}		
		}			
	}
	
}
