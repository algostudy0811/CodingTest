package week06;

import java.io.*;
import java.util.*;

/**
 * 0. 해가 1년씩 증가할 때, <x:y>의 x,y 값들도 동시에 증가 
 * -> x, y의 두 조건을 모두 만족시켜야 함 
 * -> x의 조건을 무조건 만족시키는 조건으로 반복문을 진행하고, 해당 값이 y의 조건도 만족하는지 확인하면서 진행 
 * 1. x값을 기준으로 진행
 * 2. 입력받은 X 값에서 M을 더해가면서 확인 (M년 뒤)
 * 2-1. 해의 값은 N*M을 넘을 수 없음
 * 2-2. M년 뒤의 해의 값이 y의 조건을 만족하는지 확인
 * 2-2-1. N년으로 나눈 나머지가 y인 경우, y의 조건도 동시에 만족하는 경우이므로, 해당 해의 값은 유효한 값
 * 
 * 15656KB, 184ms
 */
public class BOJ_6064_최규림 {
	
	static int M, N, x, y;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		
		while(T-- != 0) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			int answer = simulate();
			sb.append(answer).append('\n');
		}					
		
		System.out.println(sb);
	}
	
	static int simulate() {
		int maxValue = N * M;		
		
		int k = x;
		while(k <= maxValue) {
			if((k-y)%N == 0) return k;
			k += M;
		}
		
		return -1;		
	}

}
