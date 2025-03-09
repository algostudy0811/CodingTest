package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 
 * 최대 경로 찾기
 * 각 좌표에서 최대 길이 구하기
 * N==1일때 구하기
 * 127392KB 2984ms
 */
public class BOJ_1967_이석범 {
	
	static int N;
	static List<Node>[] graph;
	static boolean[] visited;
	
	static int max = Integer.MIN_VALUE;
	
	static class Node {
		int idx;
		int cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	static void DFS(int idx, int cost) {
		
		visited[idx] = true;
//		System.out.println(idx);
		
		for(Node n: graph[idx]) {
			int next = n.idx;
			int nextCost = n.cost;
			
			if(visited[next]) continue;
			
			max = Math.max(max, cost+nextCost);
//			System.out.println(cost+nextCost);
			
			DFS(next, cost+nextCost);
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		
		for(int i=1; i<N+1;i++) {
			graph[i] = new ArrayList<>();
		}
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		
		for(int i=0; i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[parent].add(new Node(child, cost));
			graph[child].add(new Node(parent, cost));
		}
		
		
		for(int i=1; i <N+1;i++) {
			visited = new boolean[N+1];
//			Arrays.fill(visited, -1);
			
//			System.out.println("idx: "+i);
			
			DFS(i, 0);
		}
		
		System.out.println(max);
		
	}
}
