package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15565_ÀÌ¼®¹ü {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(arr[i]==1) {
				list.add(i);
			}
		}
		
		if(list.size() < K) {
			System.out.println(-1);
			return;
		}
		
		int distance = Integer.MAX_VALUE;
		
		for(int start=0; start<list.size();start++) {
			int end = start;
			int cnt = 0;
			
			while(end < list.size() && cnt < K) {
				cnt++;
				end++;
				
			}
			
			if(cnt==K) {
                int left = list.get(start);
                int right = list.get(end-1);
                distance = Math.min(distance, right-left+1);
			}
			
		}
		
		System.out.println(distance);
		
		
		
	}
}
