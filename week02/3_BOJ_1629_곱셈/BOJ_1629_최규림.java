package algorithm0811.week02;

import java.io.*;
import java.util.*;

/**
 * N의 크기가 매우 크기 때문에 B번 연산은 불가
 * 
 * 실패 풀이 
 * - ex) 10 2 2 ... -> 나머지 값이 같은 값으로 연속 발생하면, 해당 값 리턴
 * - ex) (1 2 3), (1 ,,, -> 다음과 같이 규칙성이 발생한 경우, 연산을 종료하고 특정값 리턴
 * -> 2번째 규칙성이 유지된다고 보장할 수 없기 떄문에 실패한 것 같음    
 * 
 * 문제 풀이 방식 참고
 * - 지수 법칙과 모듈러 성질 활용 
 * - 지수 법칙 : a^(n+m) -> a^n * a^m
 * - 모듈러 성질 : (a*b)%c -> (a%c * b%c)%c
 * - A^B -> (A*A)^(B/2)
 * 	- 제곱수를 반으로 줄이고 A를 곱
 * 	- B가 홀수인 경우, 2로 나누어 떨어지지 않기 때문에 A를 하나 가져와서 연산에 포함시
 * 연산 횟수를 계속 줄여가면서 진행 -> 시간복잡도 O(log N)
 * 
 *  14076KB, 1000ms
 */
public class BOJ_1629_최규림 {

	static long A, B, C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		long answer = 1;
		while(true) {
			if(B == 1) break;
			
			// B가 홀수라면 A를 따로 빼서 짝수로 변환해서 처
			if(B % 2 == 1) {
				answer *= A;
				answer %= C;
				B -= 1;
			}
			
			// 지수(B) 반으로 줄이
			A *= A;
			A %= C;			
			B /= 2;
		}
		System.out.println((answer * A) % C);
	}

}
