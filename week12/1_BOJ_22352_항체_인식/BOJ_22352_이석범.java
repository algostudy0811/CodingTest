package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 그래프 2개를 비교
 * 이전과 이후의 그래프를 비교해서 다른 곳을 찾고 BFS를 통해 값을 바꾸고 다시 비교
 * 하나라도 맞지 않은 부분 존재시 NO 출력
 * 11988KB 76ms
 */
public class BOJ_22352_이석범 {
	
	static int R, C;
	static int[][] graph, next;
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void BFS(int startR, int startC, int after) {
		boolean[][] visited = new boolean[R][C];
		Queue<Node> queue = new ArrayDeque<>();
		
		int before = graph[startR][startC];
		
		queue.offer(new Node(startR, startC));
		graph[startR][startC] = after;
		
		visited[startR][startC] = true;
		
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			for(int d=0; d<4;d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				
				if(nr < 0 || nr >=R || nc < 0 || nc >= C || visited[nr][nc] || graph[nr][nc] != before) continue;
				
				graph[nr][nc] = after;
				visited[nr][nc] = true;
				queue.offer(new Node(nr, nc));
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		next = new int[R][C];
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int startR=0, startC=0, after=-1;
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				int n = Integer.parseInt(st.nextToken());
				next[r][c] = n;
				if(graph[r][c]!=n) {
					startR = r;
					startC = c;
					after = n;
				}
			}
		}
		
//		System.out.println(startR+" "+startC+" "+after);
		
		if(after==-1) {
			System.out.println("YES");
		}
		else {
			BFS(startR, startC, after);
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					if(graph[r][c] != next[r][c]) {
//						System.out.println(r+" "+c);
						System.out.println("NO");
//						print();
						return;
					}
				}
			}
			System.out.println("YES");
		}
		
		
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
