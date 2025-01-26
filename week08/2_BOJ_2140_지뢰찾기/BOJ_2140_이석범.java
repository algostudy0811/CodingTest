package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 모르는 것을 기준으로 8개를 탐색하면서 0이 하나라도 있으면 지뢰가 아니고
 * 그 외에는 지뢰인 경우 이므로 체크하고 나머지들 숫자를 -1씩 빼줌
 * 11832KB 72ms
 */
public class BOJ_2140_이석범 {
	
	static int[][] graph;
	static int N;
	
	//-1은 모름, -2는 몰랐는데 없음, -3은 지뢰
	static void check(int R, int C) {
		for(int r=R-1; r<=R+1;r++) {
			for(int c=C-1; c<=C+1;c++) {
				if(graph[r][c]==0) {
					graph[R][C]= -2;
					return;
				}
			}
		}
		graph[R][C] = -3;
		for(int r=R-1; r<=R+1;r++) {
			for(int c=C-1; c<=C+1;c++) {
				if(graph[r][c]>0) {
					graph[r][c]--;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for(int r=0; r<N;r++) {
			String tmp = br.readLine();
			for(int c=0; c<N;c++) {
				if(tmp.charAt(c)=='#') {
					graph[r][c] = -1;
				}
				else {
					graph[r][c] = tmp.charAt(c) - '0';
				}
			}
		}
		
		if(N==1 || N==2) {
			System.out.println(0);
			return;
		}
		
		for(int r=1; r<N-1; r++) {
			for(int c=1; c<N-1;c++) {
				check(r, c);
			}
		}
		
		int cnt = 0;
		
		for(int[] r: graph) {
			for(int c: r) {
				if(c==-3) cnt++;
			}
		}
		System.out.println(cnt);
		
//		print();
		
	}
	
	static void print() {
		for(int[] r: graph) {
			for(int c: r) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
