package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 모듈러 계산시 A*B mod c = (A mod c * B mod c) mod c가 된다
 * 즉 A^B에서 B가 짝수이면 A^B를 B/2로 나눠서 2번계산
 * B가 홀수면 A를 한번더 곱함
 * 	11508KB 68ms
 */
public class BOJ_1629_이석범 {
	
	static long A, B, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		long res = cal(A, B);
		
		System.out.println(res);
		
	}
	
	public static long cal(long A, long B) {
		if(B==1) {
			return A % C;
		}
		
		//분할 정복
		long cur = cal(A, B/2);
		
		//홀수 일 경우 A를 곱함
		if(B % 2 == 1) {
			return (cur * cur % C) * A % C;
		}
		return cur * cur % C;
	}

}
