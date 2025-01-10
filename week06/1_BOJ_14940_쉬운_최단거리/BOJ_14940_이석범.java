package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 목표지점에서 단순 레벨별 BFS로 탐색하면서 visited배열에 레벨넣기
 * 주의사항
 * 1. 시작 좌표의 visited를 0으로 할 경우 계속 찾기 때문에 임시로 -1을 넣고 추후에 0을 삽입
 * 2. 시작 좌표의 graph의 값이 2이므로 주의
 * 3. 갈수 있는 좌표 즉 graph에 값이 1이면서 visited가 0인 좌표의 경우 -1 출력
 * 
 * 92436KB 1956ms
 */
public class BOJ_14940_이석범 {
	
	static int R,C;
	static int[][] graph;
	static int startR, startC;
	
	static int[][] visited;
	
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
	
	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(startR, startC));
		
		//시작좌표를 -1로 임시 초기화
		visited[startR][startC] = -1;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(--size>=0) {
				Node n = queue.poll();
				
				int r = n.r;
				int c = n.c;
				
				for(int d=0;d<4;d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					//0인 경우로 할 경우 목표지점을 계속 방문 할 수 있음
					if(nr < 0 || nr >=R || nc < 0 || nc >=C || graph[nr][nc]!=1) continue;
					
					if(visited[nr][nc]!=0) continue;
					
					visited[nr][nc] = cnt+1;
					queue.offer(new Node(nr, nc));
					
				}
			}
			cnt++;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		visited = new int[R][C];
		
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				
				if(graph[r][c]==2) {
					startR = r;
					startC = c;
				}
				
			}
		}
		
		BFS();
		
		visited[startR][startC] = 0;
		for(int r=0; r<R;r++) {
			for(int c=0; c<C;c++) {
				//주의 갈수 있지만 실제로 갈수 없으면 -1 출력
				if(graph[r][c]==1 && visited[r][c]==0) System.out.print(-1+" ");
				else System.out.print(visited[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		
	}
}
