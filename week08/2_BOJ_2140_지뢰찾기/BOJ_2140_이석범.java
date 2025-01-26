package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * �𸣴� ���� �������� 8���� Ž���ϸ鼭 0�� �ϳ��� ������ ���ڰ� �ƴϰ�
 * �� �ܿ��� ������ ��� �̹Ƿ� üũ�ϰ� �������� ���ڸ� -1�� ����
 * 11832KB 72ms
 */
public class BOJ_2140_�̼��� {
	
	static int[][] graph;
	static int N;
	
	//-1�� ��, -2�� �����µ� ����, -3�� ����
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
