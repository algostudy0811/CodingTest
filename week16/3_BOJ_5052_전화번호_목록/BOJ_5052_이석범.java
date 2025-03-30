package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * Trie 문 사용
 * 사용전에 정렬을 해야함
 * 188872KB 644ms
 */
public class BOJ_5052_이석범 {
	
	static class Node {
		Map<Character, Node> child;
		boolean endOfPhone;
		
		Node() {
			child = new HashMap<>();
			endOfPhone = false; 
		}
	}
	
	static class Trie {
		private Node root;
		
		Trie() {
			root = new Node();
		}
		
		boolean insert(String phone) {
			int len = phone.length();
			
			Node node = root;
			
			for(int i=0; i<len;i++) {
				char c = phone.charAt(i);
				
				if(!node.child.containsKey(c)) {
					node.child.put(c, new Node());
				}
				
				if(node.child.get(c).endOfPhone) return true;
				
				node = node.child.get(c);
				
			}
			
			node.endOfPhone = true;
			
			return false;
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		String[] phoneList;
		
		A: for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			Trie trie = new Trie();
			
			PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) ->
				Integer.compare(o1.length(), o2.length())
					);
			 
			for(int i=0; i<N;i++) {
				String phone = br.readLine();
				pq.offer(phone);
			}
			
			while(!pq.isEmpty()) {
				String phone = pq.poll();
				boolean flag = trie.insert(phone);
				if(flag) {
					sb.append("NO").append("\n");
					continue A;
				}
			}
			
			sb.append("YES").append("\n");
			
		}
		
		System.out.println(sb);
		
	}
}
