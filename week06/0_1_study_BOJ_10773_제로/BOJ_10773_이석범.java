package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
 * 	25872KB 204ms
 */
public class BOJ_10773_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			if(num==0) stack.pop();
			else stack.push(num);
			
		}
		long result = 0;
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		
		System.out.println(result);
		
	}
}
