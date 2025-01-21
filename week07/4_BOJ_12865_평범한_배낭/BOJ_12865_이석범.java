package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * knapsack 문제
 * 51336KB 140ms
 */
public class BOJ_12865_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//입력값
		int[][] array = new int[N+1][2];
		
		for(int i=1; i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			array[i][0] = w;
			array[i][1] = v;
		}
		
		//N번째를 넣을 경우 M+1이 최대인 가방을 채울때 최대 value
		int[][] dp = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1; j<=M;j++) {
				//i번째 물건을 가방에 넣을 건데 무게가 j보다 클 경우
				if(array[i][0] > j) {
					//i-1번째 물건을 넣었던 value를 가져옴
					dp[i][j] = dp[i-1][j];
				}
				//작거나 같은 경우
				else {
					//i-1번째 물건을 넣었던 value거나 i-1번째 물건의 j - i번째 물건의 무게를 뺀 value중 큰 것을 선택
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-array[i][0]] + array[i][1]);
				}
			}
		}
		
		System.out.println(dp[N][M]);
		
	}
}
