package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS로 4가지를 반복하여 숫자 찾을때까지 반복
 * 	297992KB 5552ms
 */
public class BOJ_9019_이석범 {
	
	static final int D=0, S=1, L=2, R=3;
	
	static int start, end;
	
	//num이랑 그때의 회전 순서
	static class Node {
		int num;
		String way;
		
		Node(int num,String way) {
			this.num = num;
			this.way = way;
		}
	}
	
	static String BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[10001];
		queue.offer(new Node(start, ""));
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int num = n.num;
			String way = n.way;
			
			if(num==end) {
				return way;
			}
			
			for(int d=0; d<4;d++) {
				
				int next = 0;
				int tmp;
				String nextWay = "";
				
				switch (d) {
				case D:
					next = (num*2) % 10000;
					nextWay = way+"D";
					break;
				case S:
					next = num == 0 ? 9999: num - 1;
					nextWay = way+"S";
					break;
				case L:
					tmp = num / 1000;
					next = (num % 1000) * 10 + tmp;
					nextWay = way+"L";
					break;
				case R:
					tmp = num % 10;
					next = num / 10 + (tmp*1000);
					nextWay = way+"R";
					break;
				}
				
				if(visited[next]) continue;
					
				
				
				queue.offer(new Node(next, nextWay));
				visited[next] = true;
				
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			String result = BFS();
			
			sb.append(result).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
}
