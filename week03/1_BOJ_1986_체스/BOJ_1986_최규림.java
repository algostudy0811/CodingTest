package week03;

import java.io.*;
import java.util.*;

/**
 * Queen과 Knight 조건에 맞춰서 이동
 * 이동 과정에서 점유하는 위치 count 하기(Quuen, Knight 중복 고려)
 * 전체 칸 수에서 Queen과 Knight가 점유한 칸수 빼기
 * 16608KB 124ms
 */
public class BOJ_1986_최규림 {

	static int R, C;
	static char[][] board;
	static int answer;
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}	
	
	static Queue<Point> queenQ = new ArrayDeque<Point>();
	static Queue<Point> knightQ = new ArrayDeque<Point>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R+1][C+1];
		answer = R * C;
		
		// Queen 입력
		st = new StringTokenizer(br.readLine());
		int queenCnt = Integer.parseInt(st.nextToken());
		for(int i=0; i<queenCnt; i++) {
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			queenQ.add(new Point(r, c));
			board[r][c] = 'Q';
			answer -= 1;
		}
				
		// Knight 입력
		st = new StringTokenizer(br.readLine());
		int knightCnt = Integer.parseInt(st.nextToken());
		for(int i=0; i<knightCnt; i++) {
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			knightQ.add(new Point(r, c));
			board[r][c] = 'K';
			answer -= 1;
		}
		
		// Pawn 입력
		st = new StringTokenizer(br.readLine());
		int pawnCnt = Integer.parseInt(st.nextToken());
		for(int i=0; i<pawnCnt; i++) {
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());			
			board[r][c] = 'P';
			answer -= 1;
		}
				
		moveQueen();
		moveKnight();
				
		System.out.println(answer);
	}
	 
	static void moveQueen() {
		int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
		int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
		
		while(!queenQ.isEmpty()) {
			Point now = queenQ.poll();
			for(int i=0; i<8; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				// 이동한 칸의 위치가 범위를 벗어나지 않고 장애물이 없는 경우 -> 이동
				while(checkRange(nr, nc) && (board[nr][nc] == 0 || board[nr][nc] == 'q')) { 					
					if(board[nr][nc] == 0) answer -= 1;
					board[nr][nc] = 'q';
					nr += dr[i];
					nc += dc[i];										
				}
			}
		}
	}
	
	static void moveKnight() {
		int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
		int[] dc = {-2, -1, 1, 2, -2, -1, 1, 2};
		
		while(!knightQ.isEmpty()) {
			Point now = knightQ.poll();
			for(int i=0; i<8; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				if(checkRange(nr, nc) && board[nr][nc] == 0) {
					answer -=1;
					board[nr][nc] = 'k';																		
				}
			}
		}
	}
	
	static boolean checkRange(int r, int c) {
		return 1 <= r && r <= R && 1 <= c && c <= C;
	}
	
}

