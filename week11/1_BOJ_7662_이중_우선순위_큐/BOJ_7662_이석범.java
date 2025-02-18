package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * Tree에서의 PQ treeMap
 * 키가 저장되는 동시에 자동으로 오름차순으로 정렬
 * treeSet이라는 형태도 존재하는데 값의 존재 여부만 파악 가능 -> 값은 유일한 값
 * 
 * 442448KB 2548ms
 */
public class BOJ_7662_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			//자동으로 정렬해주는 tree firstkey와 lastkey를 알 수 있음
			TreeMap<Integer, Integer> pq = new TreeMap<>();
			
			
			for(int i=0; i<N;i++) {
				st = new StringTokenizer(br.readLine());
				
				String way = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
//				if(!pq.isEmpty()) System.out.println(pq.firstEntry());
				
				if(way.equals("I")) {
					pq.put(num, pq.getOrDefault(num, 0)+1);
				}
				else {
					if(num==1) {
						if(pq.isEmpty()) continue;
						
						if(pq.get(pq.lastKey()) > 1) pq.put(pq.lastKey(), pq.get(pq.lastKey())-1);
						else pq.remove(pq.lastKey());
					}
					else {
						if(pq.isEmpty()) continue;

						if(pq.get(pq.firstKey()) > 1) pq.put(pq.firstKey(), pq.get(pq.firstKey())-1);
						else pq.remove(pq.firstKey());
					}
				}
				
			}
			
			if(pq.isEmpty()) {
				sb.append("EMPTY").append("\n");
				continue;
			}
			
			int min = pq.firstKey();
			int max = pq.lastKey();
			
			sb.append(max).append(" ").append(min).append("\n");
			
		}
		System.out.println(sb);
	}
}
