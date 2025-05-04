package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * 341060KB 8112ms
 */
public class BOJ_11067_이석범 {
	
	static class Node implements Comparable<Node> {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		
		public int compareTo(Node n) {
			return this.r == n.r ? Integer.compare(this.c, n.c) : Integer.compare(this.r, n.r);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
//			PriorityQueue<Node> pq = new PriorityQueue<>();
			List<Node> list = new ArrayList<>();
			
			//가상의 좌표 넣기
			list.add(new Node(-1, 0));
			
			for(int i=0; i<N;i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
//				pq.offer(new Node(r, c));
				list.add(new Node(r, c));
				
			}
			
			//리스트 정렬
			Collections.sort(list);
			
            int idx = 1;
            
            //리스트 보면서 거꾸로 하기
            while (idx <= N) {
                if (list.get(idx).r == list.get(idx - 1).r) {
                    idx++;
                } else if (list.get(idx).c == list.get(idx - 1).c) {
                    idx++;
                } else {
                    int cur = idx;
                    int curX = list.get(idx).r;
 
                    while (idx <= N && curX == list.get(idx).r) {
                        idx++;
                    }
 
                    List<Node> subList = list.subList(cur, idx);
                    Collections.reverse(subList);
                }
            }
			
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());

			
			for(int i=0; i<m;i++) {
				int num = Integer.parseInt(st.nextToken());
				
				
				Node n = list.get(num);
				sb.append(n.r).append(" ").append(n.c).append("\n");
			}

		}
		System.out.println(sb);
		
 	}
}
