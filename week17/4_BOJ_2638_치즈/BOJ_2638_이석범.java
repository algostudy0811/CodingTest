package algorithm;

import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * BFS 반복하여 수행 tmpGraph에 카운트 해놓았다가 2이상인 것만 0으로 제거
 * 31400KB 188ms
 */
public class BOJ_2638_이석범 {
	
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
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		int[][] tmpGraph = new int[R][C];

		queue.offer(new Node(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			int r = n.r;
			int c = n.c;
			
			for(int d=0; d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >=C || visited[nr][nc]) continue;
				
				if(graph[nr][nc]==1) {
					tmpGraph[nr][nc] += 1;
				}
				else {
					visited[nr][nc] = true;
					queue.offer(new Node(nr, nc));
				}
				
			}
		}
		int cnt = 0;
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				if(tmpGraph[r][c] >=2) {
					cnt++;
					graph[r][c] = 0;
				}
			}
		}
		
		return cnt;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		
		int cnt = 0;
		
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				if(graph[r][c]==1) cnt++;
			}
		}
		int result = 0;
		while(cnt!=0) {
			int zero = BFS();
			cnt -= zero;
			result++;
		}
		
		System.out.println(result);
	}
}
