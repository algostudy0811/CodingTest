package algorithm;

import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * BFS를 통해 시작점을 기준으로 k이상인 곳이면 cnt+1하기
 * 285868KB 920ms
 */
public class BOJ_15591_이석범 {
	
	static List<Node>[] graph;
	static int N, Q;
	static boolean[] visited;
	static final int MAX = 1_000_000_001;
	
	static class Node {
		int v;
		int e;
		
		Node(int v, int e) {
			this.v = v;
			this.e = e;
		}
	}
	
	static int BFS(int k, int v) {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.offer(v);
		visited[v] = true;
		
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(Node n: graph[cur]) {
				int next = n.v;
				int cost = n.e;
				
				if(!visited[next] && cost >=k) {
					cnt++;
					queue.offer(next);
					visited[next] = true;
					
				}
				
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, e));
			graph[b].add(new Node(a, e));
			
		}
		
		for(int i=0; i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N+1];
			
			int cnt = BFS(k, v);
			
			sb.append(cnt).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
}
