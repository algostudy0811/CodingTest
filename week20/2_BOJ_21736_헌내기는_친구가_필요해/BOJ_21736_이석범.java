package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS 
//26580KB 280ms
public class BOJ_21736_ÀÌ¼®¹ü {
	
	static int R, C;
	static int[][] graph;
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int result = 0;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void BFS(int startR, int startC) {
		boolean[][] visited = new boolean[R][C];
		Queue<Node> queue = new ArrayDeque<>();
		
		queue.offer(new Node(startR, startC));
		visited[startR][startC] = true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			for(int d=0; d<4;d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				
				if(nr < 0 || nr >=R  || nc < 0 || nc >= C || graph[nr][nc]==1 || visited[nr][nc]) continue;
				
				if(graph[nr][nc]==2) result++;
				
				queue.offer(new Node(nr, nc));
				visited[nr][nc] = true;
				
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int startR = 0, startC = 0;
		
		graph = new int[R][C];
		
		for(int r=0; r<R;r++) {
			
			String tmp = br.readLine();
			
			for(int c=0; c<C;c++) {
				
				switch (tmp.charAt(c)) {
				case 'O':
					graph[r][c] = 0;
					break;
				case 'X':
					graph[r][c] = 1;
					break;
				case 'I':
					startR = r;
					startC = c;
					graph[r][c] = 0;
					
					break;
				case 'P':
					
					graph[r][c] = 2;
					
					break;
				}
			}
		}
		
//		print();
		BFS(startR, startC);
		System.out.println(result==0 ? "TT" : result);
		
		
	}
	
	static void print() {
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				System.out.print(graph[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
