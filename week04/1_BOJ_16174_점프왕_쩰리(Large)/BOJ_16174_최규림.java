package week04;

import java.io.*;
import java.util.*;

/**
 * BFS 활용
 * 이동 방향은 오른쪽과 아래 두 방향으로 제한
 * 14884KB, 120ms
 */
public class BOJ_16174_최규림 {
	
	static final String SUCCESS = "HaruHaru";
	static final String FAIL = "Hing";
	
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	
	static int N;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력값
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// BFS 함수를 통해서 true를 반환하면 "HaruHaru", false을 반환하면 "Hing" 출력
		System.out.println(bfs()?SUCCESS:FAIL);	
	}
	
	static boolean bfs() {
		// (0, 0) 부터 시작
		Point start = new Point(0, 0);
		Queue<Point> q = new ArrayDeque<Point>();
		q.add(start);
		
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;						
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			int num = board[now.r][now.c];
			
			for(int i=0; i<2; i++) {
				int nr = now.r + dr[i] * num;
				int nc = now.c + dc[i] * num;
				
				// 범위 이탈
				if(!checkRange(nr, nc) || visited[nr][nc]) continue;
				
				// 끝 점 도달한 경우 마무리
				if(checkDestination(nr, nc)) return true;
				
				// 큐에 넣고 방문처리하면서 다음 지점 탐색으로 넘어감
				q.add(new Point(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		return false;
	}
	
	// 끝 점 확인 메서드
	static boolean checkDestination(int r, int c) {
		return r == N-1 && c == N-1;
	}
	
	// 범위 확인 메서드
	static boolean checkRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
}
