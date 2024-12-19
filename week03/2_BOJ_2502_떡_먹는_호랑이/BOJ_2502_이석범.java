package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DP 거꾸로 하면 될듯
 * 100000*30 이므로 O(N)
 * 11488KB 72ms
 */
public class BOJ_2502_이석범 {
	
	static int D, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] dp = new int[D+1];
		
		dp[D] = K;
		//이전날짜보다 크거나 같아야하므로 k를 2로 나눈 값을 반복해서 저장
		A: for(int i=1; i<=K/2;i++) {
			dp[D-2] = i;
			dp[D-1] = K-i;
			for(int d=D-3;d>0;d--) {
				dp[d] = dp[d+2] - dp[d+1];
				if(dp[d]< 0 || dp[d] > dp[d+1]) continue A;
			}
			
			break;
		}
		System.out.println(dp[1]);
		System.out.println(dp[2]);
		
	}
}
