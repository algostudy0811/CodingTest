package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 플로이드-워셜 문제
 * 
 * 400^3이므로 1억 미만
 * 
 * 58796KB 480ms
 */
public class BOJ_1956_이석범 {
	
	static final int MAX = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[V+1][V+1];
		for(int i=0;i<V+1;i++) {
			Arrays.fill(graph[i], MAX);
		}
		
		for(int i=0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
//			graph[s][e] = Math.min(graph[s][e], cost);
			graph[s][e] = cost;
		}
		
		for(int k=1; k<V+1;k++) {
			for(int r=1; r<V+1;r++) {
				for(int c=1; c<V+1;c++) {
					if(r==c) continue;
					
					graph[r][c] = Math.min(graph[r][c], graph[r][k]+graph[k][c]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int r=1; r<V+1;r++) {
			for(int c=1; c<V+1;c++) {
				if(r==c) continue;
				
				result = Math.min(result, graph[r][c]+graph[c][r]);
			}
		}
		
		//주의사항 한곳에서만 최대값일 수도 있기 때문에 >= 이상으로 해야함
		System.out.println(result>=MAX ? -1: result);
	}
}
