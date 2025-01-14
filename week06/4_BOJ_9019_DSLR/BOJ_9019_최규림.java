package week06;

import java.io.*;
import java.util.*;

/**
 * 1. BFS 활용
 * 2. Point 객체에 현재의 숫자(a)와 현재의 숫자가 만들어지기까지의 사용된 명령어(s)를 가짐
 * 3. 해당 정보를 큐에 담아가면서 방문처리 및 B와의 일치 여부 확인
 * 
 * 300796KB, 4564ms
 */
public class BOJ_9019_최규림 {

	static int T;
	static int A, B;	
	static String answer;	
	static StringBuilder sb = new StringBuilder();
	
	static class Point {
		int a;
		String s;
		
		Point(int a, String s) {
			this.a = a;
			this.s = s;
		}
	}
	
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());		
		while(T-- != 0) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			answer = "";
			visited = new boolean[10000];
			
			bfs();
			sb.append(answer).append('\n');			
		}
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Point> q = new ArrayDeque<Point>();
		Point start = new Point(A, "");
		q.add(start);
		visited[A] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.a == B) {
				answer = now.s;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int newA = calc(now.a, i);				
				if(visited[newA]) continue;
				
				visited[newA] = true;
				String temp = i == 0 ? "D" : i == 1 ? "S" : i == 2 ? "L" : "R";
				q.add(new Point(newA, now.s + temp));
			}
		}
		
	}
	
	static int calc(int n, int idx) {
		if(idx == 0) return (n * 2) % 10000;
		else if(idx == 1) return n == 0 ? 9999 : n - 1; 
		else if(idx == 2) return (n % 1000) * 10 + (n / 1000);
		else return (n % 10) * 1000 + (n / 10);
	}	
}
