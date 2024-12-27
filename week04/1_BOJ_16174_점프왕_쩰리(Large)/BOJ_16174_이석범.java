package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 오른쪽이나 아래중 한 방향으로 현재 offset만큼 이동하여 우하단으로 갈 수 있으면 
 * "HaruHaru"아니면 "Hing"
 * BFS로 오프셋의 해당하는 곳으로 이동할 수 있음
 * 12268KB 80ms
 */
public class BOJ_16174_이석범 {
	
	static int N;
	static int[][] graph;
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	
	static boolean BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		queue.offer(new Node(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			int r = node.r;
			int c = node.c;
			
			int offset = graph[r][c];
			
			for(int d=0; d<2;d++) {
				int nr = r + dr[d] * offset;
				int nc = c + dc[d] * offset;
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N||visited[nr][nc]) continue;
				
				if(graph[nr][nc] == -1) {
					return true;
				}
				
				queue.offer(new Node(nr, nc));
				visited[nr][nc] = true;
				
			}
			
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		
		for(int r=0; r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean flag = BFS();
		
		
		
		System.out.println(flag ? "HaruHaru":"Hing");
		
		
	}
}
