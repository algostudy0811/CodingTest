package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_18223_ÀÌ¼®¹ü {
	
	static int V, E, P;
	static List<Node>[] list;
	
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
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] distance = new int[V+1];
		
		Arrays.fill(distance, 1_000_000_000);
		
		pq.offer(new Node(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			for(Node node:list[n.num]) {
				if(node.cost + n.cost< distance[node.num]) {
					distance[node.num] = node.cost + n.cost;
					
					pq.offer(new Node(node.num, distance[node.num]));
				}
			}
			
		}
		
		return distance[end];
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		list = new List[V+1];
		
		for(int i=1; i<V+1;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, cost));
			list[end].add(new Node(start, cost));
			
		}
		
        if(Dijstra(1,V)==Dijstra(1,P) + Dijstra(P,V)){
            System.out.println("SAVE HIM");
        }
        else System.out.println("GOOD BYE");
		
		
		
	}
}
