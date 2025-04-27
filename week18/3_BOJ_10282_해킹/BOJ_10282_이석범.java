package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라
 * 151368KB 692ms
 */
public class BOJ_10282_이석범{
	
	static int N, start;
	static List<Node>[] list;
	static int result, cnt;
	
	static class Node implements Comparable<Node>{
		int num;
		int cost;
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		public int compareTo(Node n) {
			return Integer.compare(this.cost, n.cost);
		}
	}
	
	static int Dijstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] visited = new int[N+1];
		boolean[] check = new boolean[N+1];
		Arrays.fill(visited, 1_000_000_000);
		pq.offer(new Node(start, 0));
		visited[start] = 0;
		cnt = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			int idx = n.num;
			int cost = n.cost;
			//방문 처리 안하면 시간 초과
			if(check[idx]) continue;
			
			cnt++;
			check[idx] = true;
			
			for(Node node:list[idx]) {
                if(check[node.num]) continue;
                //같은 경우도 자르기
				if(visited[node.num] <= cost+node.cost) continue;
				
				visited[node.num] = cost+node.cost;
				
				pq.offer(new Node(node.num, cost+node.cost));
				
				
			}
			
		}
		result = 0;
		for(int i=1; i<N+1;i++) {
			if(check[i]) result = Math.max(result, visited[i]);
		}
		
		return result;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			list = new List[N+1];
			for(int i=1; i<N+1;i++) {
				list[i] = new ArrayList<>();
			}
			
			int d = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<d;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				list[b].add(new Node(a, cost));
			}
			
			int result = Dijstra(start);
			
			sb.append(cnt).append(" ").append(result).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
}
