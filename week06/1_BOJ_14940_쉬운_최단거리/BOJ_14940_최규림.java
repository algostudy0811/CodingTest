package week06;

import java.io.*;
import java.util.*;

/**
 * 1. 결과를 나타내는 2차원 배열 result 를 최대 이동 거리로 초기화 -> 이동 거리 최소화를 위함
 * 2. 입력받는 2차원 배열 board 에서 값이 2이면 start 객체 생성, 0이면 result 배열에 0 할당
 * 3. bfs 를 통해 다음의 조건을 만족시키는지 확인하면서 최소 거리 갱신
 * 3-1. 범위 이탈 여부 확인
 * 3-2. 땅 여부 확인
 * 3-3. 해당 위치까지의 이동거리가 이전의 이동거리보다 줄어들었는지 확인
 * 3-3-1. 이전의 이동거리보다 멀면 continue
 * 3-3-2. 이전의 이동거리보다 가까우면 갱신
 * 58000KB, 680ms
 */
public class BOJ_14940_최규림 {
	
	static int R, C;
	static int[][] board, result;
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Point start;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};		
	static final int INF = 1000 * 1000 + 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		result = new int[R][C];
		
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(result[r], INF);
			for(int c=0; c<C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if(board[r][c] == 2) start = new Point(r, c);		
				else if(board[r][c] == 0) result[r][c] = 0;
			}
		}
		
		bfs();
		print();
	}
	
	static void bfs() {
		Queue<Point> q = new ArrayDeque<Point>();
		q.add(start);
		result[start.r][start.c] = 0;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				// 범위 이탈
				if(!checkRange(nr, nc) || board[nr][nc] != 1) continue;
				int value = result[now.r][now.c] + 1;
				
				// 현재 (nr, nc)로 이동한 횟수가 이전에 (nr, nc)로 이동한 횟수보다 많다면, 최소의 경우가 아니므로 PASS
				if(value >= result[nr][nc]) continue;
				
				// 최소 경로로 갱신
				result[nr][nc] = value;
				q.add(new Point(nr, nc));
			}
		}		
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				int num = result[r][c] == INF?-1:result[r][c];
				sb.append(num + " ");
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	static boolean checkRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

}
