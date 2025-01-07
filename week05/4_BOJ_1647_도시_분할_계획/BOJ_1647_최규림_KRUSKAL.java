package week05;

import java.io.*;
import java.util.*;

/**
 * 크루스칼 알고리즘 활용(union-find)
 * 1) parent 1차원 정수 배열 -> 각 집의 부모를 저장(그룹화하기 위함)
 * - 처음엔 자기자신이 부모
 * 2) 유지비 오름차순으로 길의 정보 추출
 * - 추출한 길의 정보에서 start, end 
 * 	- 집의 부모가 서로 다르다면(서로 다른 그룹이라면)
 * 		- union(같은 그룹으로 합치기) 
 * 		- start, end의 부모 값 중 값이 작은 부모 값을 부모로 섬기기
 * 	- 집의 부모가 서로 같다면(이미 같은 그룹이라면)
 * 		- 무시하기
 * 		- 이미 같은 그룹이라서 사이클 발생하게됨
 * 3) 길의 개수는 N-2개 필요
 * - 마을이 1개 -> 전부 최소로 연결되어야 하므로 N-1개의 길 필요
 * - 마을이 2개 -> 1개의 그룹으로 되어 있는 길 N-1개에서 1개라도 빠지면, 2개의 마을로 분리되기 때문에 N-2개의 길 필요 
 * 
 */
public class BOJ_1647_최규림_KRUSKAL {
	
	static int N, M;
	static int[] parent;
	
	static class Node implements Comparable<Node> {
		int start, end, dist;
		Node(int start, int end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.dist - n.dist;
		}
	}
	
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.add(new Node(start, end, dist));			
		}
		
		int answer = 0;
		int edgeCnt = 0;
		
		// 길의 개수를 N-1개 까지 진행하면 하나의 마을이 생성(parent의 값이 1로 통일됨)
		// 그 상태에서 1개의 길을 제외하면 2개의 마을로 분리되기 때문에, N-2개의 길로 연결될 때까지 반복
		// N-1개의 길 중에서 유지비가 가장 비싼 길을 제외하게 됨(우선순위 큐로 추출하기 떄문)
		while(edgeCnt < N - 2) {
			Node now = pq.poll();
			
			int p1 = findParent(now.start);
			int p2 = findParent(now.end);
			
			// 서로 같은 마을의 그룹이 아니라면
			if(p1 != p2) {
				// 같은 마을로 통합
				union(now.start, now.end);
				// 연결한 길의 유지비 누적
				answer += now.dist;
				// 연결한 길 개수 누적
				edgeCnt += 1;
			}
		}
		
		System.out.println(answer);
	}
	
	static int findParent(int x) {
		if(x != parent[x]) return findParent(parent[x]);
		return x;			
	}
	
	static void union(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;		
	}
}
