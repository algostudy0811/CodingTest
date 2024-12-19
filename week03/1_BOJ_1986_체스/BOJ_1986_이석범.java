package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 거꾸로 놓는게 편할 듯 
 */
public class BOJ_1986_이석범 {
	
	static int R, C;
	static int[][] graph;
	static List<Node>[] list;
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] kdr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] kdc = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	static int[] qdr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] qdc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R+1][C+1];
		
		list = new List[4];
		
		
		
		//1은 퀸, 2는 나이트, 3은 폰
		for(int i=1; i<=3;i++) {
			list[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			for(int n=0; n<N;n++) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				graph[r][c] = i;
				
				list[i].add(new Node(r, c));	
			}
		}
		
		//나이트의 경우
		for(Node n:list[2]) {
			knightMove(n);
		}
		
		for(Node n:list[1]) {
			queenMove(n);
		}
		int answer = 0;
		for(int r=1; r<=R;r++) {
			for(int c=1; c<=C;c++) {
				if(graph[r][c]==0) {
					answer +=1;
				}
			}
		}
		
//		print();
		
		System.out.println(answer);
		
	}
	
	public static void print() {
		for(int r=0; r<=R;r++) {
			for(int c=0; c<=C;c++) {
				System.out.print(graph[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void knightMove(Node n) {
		for(int d=0; d<8;d++) {
			int nr = n.r + kdr[d];
			int nc = n.c + kdc[d];
			
			if(nr <= 0 || nr > R || nc <= 0 || nc > C || graph[nr][nc]!=0) continue;
			
			graph[nr][nc] = -1;
			
		}
	}
	
	public static void queenMove(Node n) {
		for(int d=0; d<8;d++) {
			int nr = n.r;
			int nc = n.c;
			
			while(true) {
				nr += qdr[d];
				nc += qdc[d];
				
				if(nr < 0 || nr > R || nc < 0 || nc > C||graph[nr][nc]>0) break;
				
				graph[nr][nc] = -1;
			}
			
		}
	}

}
