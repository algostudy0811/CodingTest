package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 1~N까지 스택에 넣고 idx를 0부터 N까지 움직이면서 idx를 1씩 움직이는데 스택에 남아있으면 NO 출력
 * 31484KB 280ms
 */
public class BOJ_1874_이석범 {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		Deque<Integer> stack = new ArrayDeque<>();
		
		N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			arr[i] = num;
		}
		
		int idx = 0;
		for(int i=1; i<=N;i++) {
			//stack에 먼저 넣기
			stack.push(i);
			sb.append("+").append("\n");
			
			//pop 조건이 되면 -
			while(!stack.isEmpty() && stack.peek() == arr[idx]) {
				
				idx++;
				stack.pop();
				sb.append("-").append("\n");
				
			}
		}
		
		if(!stack.isEmpty()) System.out.println("NO");
		else System.out.println(sb);
		
	}
}
