package algorithm;

//11700KB 68ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928_이석범 {
	
	static int N, M;
	
	static int[] graph;
	
	static class Node {
		int p;
		int count;
		
		Node(int p, int count) {
			this.p = p;
			this.count = count;
		}
	}
	
	//BFS로 가장 빠르게 도착한 최소횟수 반환
	static int BFS(int start) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(start, 0));
		
		boolean[] visited = new boolean[101];
		visited[1] = true;
		 
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			for(int i=1; i<=6;i++) {
				int next = n.p + i;
				int nextCount = n.count + 1;
				
				if(visited[next]) continue;
				
				if(next > 100) continue;
				
				if(next == 100) {
					return nextCount;
				}
				
				if(graph[next]!=0) {
					next = graph[next];
				}
				
				queue.offer(new Node(next, nextCount));
				visited[next] = true;
				
			}
			
		}
		
		return 0;

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[101];
		
		for(int i=0; i<N+M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start] = end;
		}
		
		int result = BFS(1);
		System.out.println(result);
	}
}
