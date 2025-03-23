package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * 11652KB 68ms
 */
public class BOJ_2225_이석범 {
	
	static final int DIV = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//k개를 골라서 N을 만드는 개수
		int[][] dp = new int[K+1][N+1];
		
		Arrays.fill(dp[1], 1);
		
		
		for(int i=2; i<K+1;i++) {
			for(int j=0; j<N+1;j++) {
				if(j==0) {
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % DIV;
			}
		}
		
		System.out.println(dp[K][N]);
	}
}
