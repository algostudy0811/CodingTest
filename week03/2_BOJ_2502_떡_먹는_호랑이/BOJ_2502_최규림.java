package week03;

import java.io.*;
import java.util.*;

/**
 * 피보나치 수열 활용
 * 
 * x
 * y
 * 1(x+y) + y	-> 1
 * 2(x+y) + y	-> 1
 * 3(x+y) + 2y	-> 3
 * 5(x+y) + 3y	-> 5
 * 8(x+y) + 5y	-> 8
 * 13(x+y)+ 8y 	-> 13
 * 
 * y를 임시값으로 부여 후
 * 1) K - dp[i-1] * y -> dp[i] * (x + y)
 * 2) dp[i] * (x + y) 
 * -> 조건1. dp[i]로 나누어 떨어지는지
 * -> 조건2. x <= y
 * -> 둘다 충족하면 출력 후 return
 * 
 * 14188KB 104ms 
 */
public class BOJ_2502_최규림 {

	static int D, K;
	static int[] dp = new int[31];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp[3] = 1;
		dp[4] = 1;
		for(int i=5; i<=30; i++) {
			dp[i] = dp[i-2] + dp[i-1];			
		}
		
		// 3번인 경우는 x+y 이기 때문에 로직 필요 X
		if(D == 3) {
			int x = K/2;
			int y = K - x;
			System.out.println(x);
			System.out.println(y);
		} else {
			// 4번째의 경우, x+2y=K 를 가정해서  
			// y=(K-x)/2 에서 x=1 인 경우가 최대의 값  
			for(int y=(K-1)/2; y>=1; y--) {
				int temp = dp[D-1] * y;
				int result = K - temp;
				if(result <= 0 || result % dp[D] != 0) continue;
				int xPlusY = result / dp[D];								
				int x = xPlusY - y;
				
				if(x > 0 && x <= y) {
					System.out.println(x);
					System.out.println(y);
					break;
				}
			}			
		}				
	}

}


