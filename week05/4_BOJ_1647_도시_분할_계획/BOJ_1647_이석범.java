package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * MST로 먼저 사이클이 되지 않는 그래프를 구한다음 가장 큰 간선을 빼면 답이 나옴
 * MST의 경우 크루스칼을 사용하면 된다
 * 
 * 324324KB 1416ms
 */
public class BOJ_1647_이석범 {
	
	static int N;
	static int[] parent;
	
	static class Node implements Comparable<Node> {
		int a;
		int b;
		int cost;
		
		Node(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//간선의 비용대로 정렬하는 큐
		PriorityQueue<Node> pq = new PriorityQueue<>();
		//비용리스트에서 가장큰 수를 빼기 위해 사용하는 큐
		PriorityQueue<Integer> costList = new PriorityQueue<>();
		
		parent = new int[N+1];
		for(int i=1; i<=N;i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			Node node = new Node(a, b, cost);
			
			pq.offer(node);
		}
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			boolean check = union(node.a, node.b);
			
			if(check) {
				costList.offer(node.cost);
				
			}
			
		}
		
		
		int result = 0;
		
		int size = costList.size();
		
		while(--size>=1) {
			result += costList.poll();
		}
		System.out.println(result);
		
	}
	
	//부모 찾는 메서드
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	//부모 합치기
	//주의사항으로는 parent[parentA]의 값 즉 부모의 부모를 바꿔야함
	static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA < parentB) parent[parentA] = b;
		else if(parentA > parentB) parent[parentB] = a;
		else {
			return false;
		}
		
		return true;
		
	}
}
