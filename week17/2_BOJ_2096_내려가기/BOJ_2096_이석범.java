package algorithm;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class BOJ_2096_이석범 {
	
	static final int MAX = 1_000_000_000;
	static final int BIG = 1, SMALL = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N][3];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<3;j++) {
				int n = Integer.parseInt(st.nextToken());
				graph[i][j] = n;
			}
		}
		
		//0은 최소 1은 최대
		int[][][] dp = new int[2][N+1][3];
		
		for(int r=0; r<N;r++) {
			for(int c=0; c<3;c++) {
				int num = graph[r][c];
				
				if(r==0) {
					dp[SMALL][r][c] = num;
					dp[BIG][r][c] = num;
					continue;
				}
				
				switch (c) {
				case 0:
					dp[SMALL][r][c] = Math.min(dp[SMALL][r-1][0], dp[SMALL][r-1][1]) + num;
					dp[BIG][r][c] = Math.max(dp[BIG][r-1][0], dp[BIG][r-1][1]) + num;
					break;
				case 1:
					dp[SMALL][r][c] = Math.min(dp[SMALL][r-1][2], Math.min(dp[SMALL][r-1][1],dp[SMALL][r-1][0])) + num;
					dp[BIG][r][c] = Math.max(dp[BIG][r-1][2], Math.max(dp[BIG][r-1][1],dp[BIG][r-1][0])) + num;
					break;

				case 2:
					dp[SMALL][r][c] = Math.min(dp[SMALL][r-1][2], dp[SMALL][r-1][1]) + num;
					dp[BIG][r][c] = Math.max(dp[BIG][r-1][2], dp[BIG][r-1][1]) + num;
					break;
				}
				
			}
		}
		int min = Math.min(dp[SMALL][N-1][0], Math.min(dp[SMALL][N-1][1], dp[SMALL][N-1][2]));		
		int max = Math.max(dp[BIG][N-1][0], Math.max(dp[BIG][N-1][1], dp[BIG][N-1][2]));
		System.out.println(max+" "+min);
	}
}
