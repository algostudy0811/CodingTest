package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * union-parent
 * 
 * 소요시간 10분
 * 54452KB 452ms
 */
public class BOJ_1717_이석범 {
	
	static int[] parent;
	
	//최상위 부모 찾기
	static int find(int x) {
		if(parent[x] != x) parent[x] = find(parent[x]);
		return parent[x];
	}
	
	//합치기
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA>parentB) parent[parentA] = parentB;
		else parent[parentB] = parentA;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i=1; i<N+1;i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cal==1) {
				if(find(a)==find(b)) {
					sb.append("YES").append("\n");
				}
				else sb.append("NO").append("\n");
			}
			else {
				union(a,b);
			}
			
		}
		System.out.println(sb);
		
	}
}
