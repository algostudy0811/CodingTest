package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 단순 백트래킹으로 할 경우 시간 초과
 * 분할 정복으로 풀기
 * offset을 둬서 현재 크기의 특정값을 보정
 * 11512KB 68ms
 */
public class BOJ_1074_이석범2 {
	
	static int back(int N, int r, int c) {
		
		//N의 크기가 최소인 2x2일때 조건에 맞게 return
		if(N==2) {
			if(r==0 && c==0) {
				return 0;
			}
			else if(r==0 && c==1) {
				return 1;
			}
			else if(r==1 && c==0) {
				return 2;
			}
			else if(r==1 && c==1) {
				return 3;
			}
		}
		//현재 r, c가 4사분면 중 어디인지 체크하기 위함
		int check = N / 2;
		//4사분면의 offset 보정을 위함 계산시 check의 제곱의 0, 1, 2, 3배
		int add = (int) Math.pow(check, 2);
		int offset = 0;
		if(r < check && c <check) {
			offset = 0;
		}
		else if(r < check && c >=check) {
			c -= check;
			offset = add;
		}
		else if(r >=check && c < check) {
			r -= check;
			offset = add*2;
		}
		else {
			r -= check;
			c -= check;
			offset = add*3;
		}
//		System.out.println(r+" "+c+" "+offset);
		return offset + back(N/2, r, c);
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int N = (int) Math.pow(2, n);
		
		int result = back(N, r, c);
		
		System.out.println(result);
	}
}
