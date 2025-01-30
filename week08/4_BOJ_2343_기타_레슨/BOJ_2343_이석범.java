package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이진탐색으로 0~10억까지 중 블루레이 개수가 M미만인것의 최소값 세기
 * 개수가 미만인 경우도 한개씩 나눠 가지면 M개가 되므로 상관없음
 * 45분 소요
 * 23484KB 208ms
 */
public class BOJ_2343_이석범 {

	//1만*10만
	private static final int END = 1_000_000_000;
	//최소값
	private static int result = Integer.MAX_VALUE;
	private static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] array = new int[N];
		
		//배열의 최대값을 구하기 위함
		//최대값을 구해서 최대값보다 작은 블루레이는 못담기때문
		//하지 않으면 50퍼에서 틀림
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
			max = Math.max(array[i], max);
		}
		//이진탐색
		binarySearch(array, max);
		System.out.println(result);
		
	}
	
	private static void binarySearch(int[] array, int max) {
		int start = max;
		int end = END;
		
		int mid = 0;
		
		while(start<=end) {
			mid = (start + end) / 2;
			boolean flag = check(array, mid);
			//블루레이 여부에 따라 범위 다르게 적용
			if(flag) {
//				System.out.println(start+":"+end+":"+mid);
				result = Math.min(result, mid);
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
	}
	
	private static boolean check(int[] array, int num) {
		
		//블루레이 개수
		int blueLayNum = 0;
		//강의의 총 시간
		int tmp = 0;
		for(int i=0; i<N;i++) {
			//강의를 더한것이 블루레이 시간보다 커지면 블루레이 개수를 +1하고 시간은 초기화
			if(tmp+array[i]>num) {
				blueLayNum++;
				tmp = array[i];
			}
			else {
				tmp += array[i];
			}
		}
		//tmp에 0이 아닌 숫자가 있으면 강의가 남아있으므로 +1추가
		if(tmp !=0) blueLayNum++;
		
		//개수가 미만인것도 상관없음
		if(blueLayNum<=M) {
//			System.out.println(blueLayNum+" "+num);
			return true;
		}
		
		return false;
	}
}
