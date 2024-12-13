package week12_2;

import java.io.*;
import java.util.*;

/**
 * 전위, 중위, 후위 순회 알고리즘
 * class 활용
 * 14252KB, 104ms
 */
public class BOJ_1991_최규림 {

	static int N;
	static class Node {
		int left, right;
		Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	static Node[] nodeArr;		
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodeArr = new Node[N + 1];
		for(int i=0; i<=N; i++) {
		}
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int target = charToInt(st.nextToken().charAt(0));
			int left = charToInt(st.nextToken().charAt(0));
			int right = charToInt(st.nextToken().charAt(0));			
			nodeArr[target] = new Node(left, right);
		}
		
		preOrder(0);
		sb.append('\n');
		inOrder(0);		
		sb.append('\n');
		postOrder(0);	
		sb.append('\n');
		System.out.println(sb.toString());
	}
	
	static void preOrder(int num) {
		int left = nodeArr[num].left;
		int right = nodeArr[num].right;
		
		sb.append(intToChar(num));
		if(left != -1) preOrder(left);
		if(right != -1) preOrder(right);	
	}
	
	static void inOrder(int num) {
		int left = nodeArr[num].left;
		int right = nodeArr[num].right;
		
		if(left != -1) inOrder(left);
		sb.append(intToChar(num));
		if(right != -1) inOrder(right);		
	}
	
	static void postOrder(int num) {
		int left = nodeArr[num].left;
		int right = nodeArr[num].right;
		
		if(left != -1) postOrder(left);
		if(right != -1) postOrder(right);
		sb.append(intToChar(num));		
	}
	
	static int charToInt(char c) {
		if(c == '.') return -1;
		return c - '0' - 17;
	}
	
	static char intToChar(int i) {
		return (char)(i + 'A');
	}

}
