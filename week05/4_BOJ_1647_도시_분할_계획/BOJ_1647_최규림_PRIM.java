package week05;

import java.io.*;
import java.util.*;

/**
 * 1) 마을의 모든 집을 최소 유지비로 모두 연결하는 길을 구함 (N개의 집을 연결하는 M개의 길을 N-1개의 길로 축소)
 * -> 최소 간선 트리
 * 2) 위에서 구한 총 유지비에서 최대 유지비가 드는 길 하나를 제거 (1개의 마을을 유지하는 N-1개의 길에서 하나가 제거됐기 때문에 2개로 분리될 수 밖에 없음)
 * 351604KB, 1744ms
 */
public class BOJ_1647_최규림_PRIM {
	
	static int N, M;
	static class Node implements Comparable<Node>{
		int end, dist;
		Node(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.dist - n.dist;
		}
	}
	
	static List<Node>[] list;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		list = new List[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}
		
		// 1) 마을의 모든 집을 최소 유지비로 모두 연결하는 길을 구함 (N개의 집을 연결하는 M개의 길을 N-1개의 길로 축소)
		// 1번 집에서 탐색 시작
		pq.add(new Node(1, 0));		
		int totalDist = 0;
		int maxDist = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll(); // 유지비가 가장 저렴한 순으로 poll
	
			if(visited[now.end]) continue; // 이전에 방문한 집이면 스킵						
			visited[now.end] = true; // 집 방문 처리			
			totalDist += now.dist; // 집 연결했다고 가정하고 유지비 누적
			maxDist = Math.max(maxDist, now.dist); // 연결된 길 중 최대 유지비 갱신	
						
			// now.end 가 이제 출발지점, now.end와 연결된 마을 탐색
			for(int i=0; i<list[now.end].size(); i++) {
				Node next = list[now.end].get(i);
				if(visited[next.end]) continue; // 다음 집이 이전에 방문한 적 있으면 스킵				
				pq.add(new Node(next.end, next.dist));
			}
		}
				
		// 2) 위에서 구한 총 유지비에서 최대 유지비가 드는 길 하나를 제거 (1개의 마을을 유지하는 N-1개의 길에서 하나가 제거됐기 때문에 2개로 분리될 수 밖에 없음)
		System.out.println(totalDist - maxDist);		
	}

}
