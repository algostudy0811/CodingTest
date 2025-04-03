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
 * 다익스트라 사용
 * 1 -> V1 -> V2 -> N
 * 1 -> V2 -> V1 -> N
 * 중 최단 경로 사용
 * 
 * 70168KB 704ms
 */
public class BOJ_1504_이석범 {
	
	static int N, E;
	static List<Node>[] list;
	static int V1, V2;
	static final int MAX = 1_000_000_000;
	
	static class Node implements Comparable<Node> {
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
	
	static int Dijstra(int start, int end) {
		int[] distance = new int[N+1];
		Arrays.fill(distance, MAX);
		
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		
		pq.offer(new Node(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			int idx = cur.num;
			int dir = cur.cost;
			
			if(distance[idx] < dir) continue;
			
			for(Node n:list[idx]) {
				
				int next = n.num;
				int cost =n.cost;
				
				if(distance[next] < dir+cost) continue;
				
				distance[next] = dir+cost;
				pq.offer(new Node(next, distance[next]));
				
			}
			
		}
		
		return distance[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		
		for(int i=1; i<N+1;i++) {
			list[i] = new ArrayList<>();
		}
		
 		for(int i=0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, cost));
			list[b].add(new Node(a, cost));
		}
 		
 		st = new StringTokenizer(br.readLine());
 		
 		V1 = Integer.parseInt(st.nextToken());
 		V2 = Integer.parseInt(st.nextToken());
 		
 		//MAX를 10억으로 하여 long으로 계산
 		long first = 0;
 		first += Dijstra(1, V1);
 		first += Dijstra(V1, V2);
 		first += Dijstra(V2, N);
 		
 		long second = 0;
 		second += Dijstra(1, V2);
 		second += Dijstra(V2, V1);
 		second += Dijstra(V1, N);
 		
 		//둘다 MAX 보다 크거나 같을 경우 -1 출력
 		if((first>=MAX) && (second >= MAX)) System.out.println(-1);
 		else System.out.println(Math.min(first, second));
		
	}
}
