package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
 * 스택 사용
 * Deque 대신 stack을 사용하면 특정 인덱스 위치을 알 수 있음
 */
public class BOJ_9935_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		String word = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		int inputLen = input.length();
		int wordLen = word.length();
		
		for(int i=0; i<inputLen;i++) {
			char c = input.charAt(i);
			
			stack.push(c);
			
			if(stack.size() >= wordLen) {
				int cnt = 0;
				for(int j=0; j<wordLen;j++) {
					if(stack.get(stack.size() - wordLen + j) == word.charAt(j)) {
						cnt++;
					}
				}
				
				if(cnt==wordLen) {
					for(int j=0; j<wordLen;j++) {
						stack.pop();
					}
				}
				
			}
			
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}
		else {
			StringBuilder sb = new StringBuilder();
			
			for(char c:stack) {
				sb.append(c);
			}
			
			System.out.println(sb);
		}
		
	}
}
