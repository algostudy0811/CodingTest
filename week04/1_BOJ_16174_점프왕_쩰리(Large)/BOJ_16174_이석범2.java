package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * �������̳� �Ʒ��� �� �������� ���� offset��ŭ �̵��Ͽ� ���ϴ����� �� �� ������ 
 * "HaruHaru"�ƴϸ� "Hing"
 * BFS�� �������� �ش��ϴ� ������ �̵��� �� ����
 * 12268KB 80ms
 */
public class BOJ_16174_�̼���2 {
	
	static int N;
	static int[][] graph, dp;
	
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		dp = new int[N][N];
		
		for(int r=0; r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = graph[0][0];
		
		for(int r=0; r<N;r++) {
			for(int c=0; c<N;c++) {
				if(dp[r][c] != 0) {
					
					int offset = dp[r][c];
					
					for(int d=0; d<2;d++) {
						int nr = r + dr[d] * offset;
						int nc = c + dc[d] * offset;
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						
						dp[nr][nc] = graph[nr][nc];
					}
				}
			}
		}
		
		System.out.println(dp[N-1][N-1] == -1 ? "HaruHaru":"Hing");
		
		
	}
}
