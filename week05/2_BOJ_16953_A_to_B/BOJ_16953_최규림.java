package week05;

import java.io.*;
import java.util.*;

/**
 * DFS 활용
 * 1) 2를 곱함
 * 2) 10을 곱하고 1을 더함
 * - 위 두 조건을 계산해서 B와 같은지 여부 확인
 * - B보다 큰 경우는 중단
 * - A,B의 MAX 값이 10^9에 가깝기 때문에 곱셈 연산중 int 범위를 벗어날 수 있음 -> long으로 해결
 * 
 * 14208KB, 100ms
 */
public class BOJ_16953_최규림 {

	static long A, B, answer;
	static final int MAX_VALUE = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		answer = MAX_VALUE;
		dfs(A, 1);		
		System.out.println(answer==MAX_VALUE?-1:answer);
	}
	
	static void dfs(long num, long cnt) {
		if(num == B) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		if(num * 2 <= B) dfs(num * 2, cnt+1);
		if(calc(num) <= B) dfs(calc(num), cnt+1);
	}
	
	static long calc(long a) {
		return a * 10 + 1;
	}

}
