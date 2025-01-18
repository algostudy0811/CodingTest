package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp로 풀면 됨
 * dp의 경우 n번째를 선택했을때 포도주의 양을 나타냄
 * 0은 고른 경우 1은 고르지 않은 경우
 * 20분 소요
 * 13704KB 96ms
 */
public class BOJ_2156_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] array = new int[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		//1일 경우 out of bound방지
		if(N==1) {
			System.out.println(array[0]);
			return;
		}
		
		int[][] dp = new int[N][2];
		
		//선택한것 체크 해보는 용도
//		int[][] selected = new int[N][2];
		
		dp[0][0] = array[0];
		dp[0][1] = 0;
		
		dp[1][0] = array[0] + array[1];
		dp[1][1] = array[0];
		
		for(int i=2;i<N;i++) {
			//현재 포도주를 마실지 안마실지가 중요
			//안마시는 경우 -> 이전것을 선택한 것의 최대 개수
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
			
//			if(dp[i-1][0] >= dp[i-1][1]) {
//				dp[i][1] = dp[i-1][0];
//				selected[i][1] = 0;
//			}
//			else {
//				dp[i][1] = dp[i-1][1];
//				selected[i][1] = 1;
//			}
			
			//마시는 경우 -> 이전것을 선택하지 않은 경우와 2번째 이전의 것을 선택하지 않고 이전것을 선택하는 것중 큰것 선택 
			dp[i][0] = Math.max(dp[i-1][1], dp[i-2][1]+array[i-1]) + array[i];
			
//			if(dp[i-1][1] >= dp[i-2][1]+array[i-1]) {
//				dp[i][0] = dp[i-1][1]+array[i];
//				selected[i][0] = 0;
//			}
//			else {
//				dp[i][0] = dp[i-2][1]+array[i-1] + array[i];
//				selected[i][0] = 1;
//			}
			
		}
		
//		for(int[] r: selected) {
//			for(int c:r) {
//				System.out.print(c+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
		
		
	}
}
