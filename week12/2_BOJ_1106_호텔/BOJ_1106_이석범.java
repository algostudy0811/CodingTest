package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1106_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//N+1로 할 경우 N일때보다 N+1일때가 작을 수도 있음 따라서 101로 수정
		int[] dp = new int[N+101];
		
		Arrays.fill(dp, 100_000_000);
		dp[0] = 0;
		
		int[][] arr = new int[M][2];
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());
			
			arr[i][0] = cost;
			arr[i][1] = people;
		}
		
		for(int i=0; i<M;i++) {
			int idx = arr[i][1];
			int cost = arr[i][0];
			for(int j=idx; j<N+101;j++) {
				dp[j] = Math.min(dp[j], dp[j-idx]+cost);
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i=N;i<N+101;i++) {
			min = Math.min(min, dp[i]);
		}
		
		System.out.println(min);
		
		System.out.println(Arrays.toString(dp));
		
//		System.out.println(dp[N]);
	}
}
