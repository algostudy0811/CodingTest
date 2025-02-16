package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 
 * 	11884KB 76ms
 */
public class BOJ_14503_이석범 {
	
	static int R, C;
	static int[][] graph;
	
	static class Node {
		int r;
		int c;
		int d;
		
		Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
		void move() {
			//1. 현재칸 청소 안된 경우 청소로 변경 2
			if(graph[r][c]==0) {
				graph[r][c] = 2;
			}
			
			//4방향 청소 여부 확인
			boolean flag = false;
			
			for(int d=0; d<4;d++) {
				int nr = this.r + dr[d];
				int nc = this.c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]==1) continue;
				
				//청소 안한 부분 존재하면 true
				if(graph[nr][nc]==0) {
					flag = true;
					break;
				}
			}
			//청소 하는 곳 없으면
			if(!flag) {
				//후진 가능이면
				int nr = this.r - dr[d];
				int nc = this.c - dc[d];
				
				//후진 불가능
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]==1) return;
				
				this.r = nr;
				this.c = nc;
				
				this.move();
				
			}
			//청소할 곳 있으면
			else {
				this.d = this.d - 1 < 0 ? 3 : this.d - 1;
				
				int nr = this.r + dr[d];
				int nc = this.c + dc[d];
				//앞에가 청소 안한 부분 이 아니면 move
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]!=0) {
					this.move();
				};
				//앞에서 청소안한 부분이면 전진
				if(graph[nr][nc]==0) {
					this.r = nr;
					this.c = nc;
					this.move();
				}
				
				
				
			}
		}
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		
		st = new StringTokenizer(br.readLine());
		
		int startR = Integer.parseInt(st.nextToken());
		int startC = Integer.parseInt(st.nextToken());
		int startD = Integer.parseInt(st.nextToken());
		
		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Node n = new Node(startR, startC, startD);
		
		n.move();
		
		int cnt = 0;
		
		for(int[] r: graph) {
			for(int c: r) {
//				System.out.print(c+" ");
				if(c == 2) cnt++;
			}
//			System.out.println();
		}
		
		System.out.println(cnt);
	}
}
