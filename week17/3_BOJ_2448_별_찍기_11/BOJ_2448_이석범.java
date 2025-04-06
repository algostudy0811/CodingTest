package algorithm;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 
 * 236404KB 464ms
 */
public class BOJ_2448_이석범 {
	
	static int N;
	static char[][] star;
	
	//N == 24 -> 24 * 47
	//N == 12 => 12 * 23
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		star = new char[N+1][2*N];
		//' '빈칸으로 채우기 
		for(int r=0; r<N+1;r++) {
			Arrays.fill(star[r], ' ');
		}
		//위, 왼쪽 아래, 오른쪽 아래의 로 나누어 재귀
		//꼭짓점을 기준으로 하기
		recur(0, N-1, N);
		
		StringBuilder sb = new StringBuilder();
		for(char[] r: star) {
			for(char c:r) {
				sb.append(c);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void recur(int r, int c, int n) {
		if(n==3) {
			star[r][c] = '*';
			star[r+1][c-1] = '*';			
			star[r+1][c+1] = '*';
			
			for(int i=-2;i<=2;i++) {
				star[r+2][c+i] = '*';
			}
		}
		else {
			int offset = n/2;
			recur(r, c, offset);
			recur(r+ offset, c-offset, offset);
			recur(r+ offset, c+offset, offset);
		}
	}
}
