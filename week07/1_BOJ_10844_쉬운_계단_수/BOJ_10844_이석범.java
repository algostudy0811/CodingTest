package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * n이 1일 경우 9
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7
 * 8
 * 9
 * n이 2일 경우 17
 * 10, 12
 * 21, 23
 * 32, 34
 * 43, 45
 * 54, 56
 * 65, 67
 * 76, 78
 * 87, 89
 * 98, 
 * n이 n인 경우
 * n이 1~8일 경우 앞자리가 2가지의 경우가 생김
 * n이 9또는 0일 경우 1가지의 경우만 생김
 * 
 * dp[자릿수][숫자] -> [N][10]
 * 자릿수가 늘때마다 일의자리 수가 m(0~9)인 개수를 dp에 저장
 * 이전 개수를 가져옴
 * 소요시간: 15분
 * 11532KB 68ms
 */
public class BOJ_10844_이석범 {
	
	static final int NUM = 101;
	static final long DIV = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[NUM][10];
		
		Arrays.fill(dp[1], 1L);
		dp[1][0] = 0;
		
		for(int i=2;i<NUM;i++) {
			for(int j=0; j<10;j++) {
				if(j==0) {
					dp[i][j] = dp[i-1][j+1];
				}
				else if(j==9) {
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				}
				dp[i][j] %= DIV;
			}
		}
		
		long result = 0;
		for(int i=0; i<10;i++) {
			result += dp[N][i];
		}
		
		System.out.println(result % DIV);
		
	}
}
