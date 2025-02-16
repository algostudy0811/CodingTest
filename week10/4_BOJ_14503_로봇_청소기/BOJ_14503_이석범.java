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
public class BOJ_14503_�̼��� {
	
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
			//1. ����ĭ û�� �ȵ� ��� û�ҷ� ���� 2
			if(graph[r][c]==0) {
				graph[r][c] = 2;
			}
			
			//4���� û�� ���� Ȯ��
			boolean flag = false;
			
			for(int d=0; d<4;d++) {
				int nr = this.r + dr[d];
				int nc = this.c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]==1) continue;
				
				//û�� ���� �κ� �����ϸ� true
				if(graph[nr][nc]==0) {
					flag = true;
					break;
				}
			}
			//û�� �ϴ� �� ������
			if(!flag) {
				//���� �����̸�
				int nr = this.r - dr[d];
				int nc = this.c - dc[d];
				
				//���� �Ұ���
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]==1) return;
				
				this.r = nr;
				this.c = nc;
				
				this.move();
				
			}
			//û���� �� ������
			else {
				this.d = this.d - 1 < 0 ? 3 : this.d - 1;
				
				int nr = this.r + dr[d];
				int nc = this.c + dc[d];
				//�տ��� û�� ���� �κ� �� �ƴϸ� move
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]!=0) {
					this.move();
				};
				//�տ��� û�Ҿ��� �κ��̸� ����
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
