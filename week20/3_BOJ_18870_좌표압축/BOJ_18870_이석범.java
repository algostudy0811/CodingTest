package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//363564KB 1760ms
public class BOJ_18870_ÀÌ¼®¹ü {
	
	static int N;
	static int[] input, sorted;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		sorted = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N;i++) {
			input[i] = Integer.parseInt(st.nextToken());			
		}
		
		System.arraycopy(input, 0, sorted, 0, N);
		
		Arrays.sort(sorted);
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i:sorted) {
			if(map.containsKey(i)) continue;
			
			map.put(i, map.size());
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i:input) {
			sb.append(map.get(i)).append(" ");
		}
		
		System.out.println(sb);
		
		
	}
}