package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 동전을 여러번 사용 가능
 * 동전 중복 가능
 * set으로 중복 해결
 * 
 * 12252KB 88ms
 */
public class BOJ_2294_이석범 {
	
	static final int MAX = 100_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			set.add(n);
		}
		
		int[] dp = new int[K+1];
		Arrays.fill(dp, MAX);
		
		for(int i:set) {
//			System.out.println(i);
			
			/*
			 * 1 1
			 * 3
			 * 일 경우 3은 배열을 넘어가기 때문에 예외처리
			 */
			if(i>=K+1) continue; 
			
			//i 한개를 사용하는 것이 최소 이므로 1로 만들기 
			dp[i] = 1;
			
			for(int j=i; j<K+1;j++) {
				
				dp[j] = Math.min(dp[j], dp[j-i]+1);
			}
		}
		
		System.out.println(dp[K]==MAX ? -1:dp[K]);
		
	}
}
