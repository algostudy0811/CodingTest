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
 * 플로이드 워셜은 시간 초과
 * 다익스트라로 이용
 */
public class BOJ_1916_이석범 {
	
	static class Node implements Comparable<Node> {
		int index;
		int cost;

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;

		}

		public int compareTo(Node o) {

			return Integer.compare(this.cost, o.cost);
		}



	}

	static int n, m;
	static int start, end;
	static int[] distance;

	static List<Node>[] list;


	static void dijstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[start] = 0;
		pq.add(new Node(start, 0));

		while(!pq.isEmpty()) {
			Node current = pq.poll();

			int index = current.index;
			int dist = current.cost;
			if(distance[index] < dist) continue;

			for (Node node : list[index]) {
				int cost = dist + node.cost;
				int next = node.index;
				if(distance[next] > cost) {
					distance[next] = cost;
					pq.offer(new Node(next, cost));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		distance = new int[n+1];

		Arrays.fill(distance, Integer.MAX_VALUE);

		list = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) {
			list[i] = new ArrayList<>();
		}

		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list[first].add(new Node(second, cost));
		}


		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijstra(start);

		System.out.println(distance[end]);

	}
}
