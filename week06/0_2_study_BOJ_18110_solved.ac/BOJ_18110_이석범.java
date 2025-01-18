package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 49136KB 336ms
 */
public class BOJ_18110_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
//		int N = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] score = new int[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			score[i] = num;
			
		}
		
		Arrays.sort(score);

		int div = (int) Math.round(N*0.15);
		
		
		int sum = 0;
		for(int i=div;i<N-div;i++) {
			sum += score[i];
		}
		
		
		int res = (int)Math.round((1.0*sum) / (N - (div*2)));
		
		
		
		System.out.println(res);
		
		
	}
}
