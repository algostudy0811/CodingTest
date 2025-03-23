package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * LIS 이진탐색으로 처리
 * 18080KB 192ms
 */
public class BOJ_2565_이석범 {
	
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][2];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			arr[i][0] = start;
			arr[i][1] = end;
		}
		
		//시작지점을 기준으로 정렬
		Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0], o2[0]));
//		Arrays.sort(arr, new Comparator<int[]>() {
//			public int compare(int[] o1, int[] o2) {
//				return Integer.compare(o1[0], o2[0]);
//			}
//		});
		
		dp = new int[N];
		
		int LIS = 0;
		
		for(int i=0; i<N;i++) {
			int idx = binarySearch(arr[i][1], 0, LIS, LIS+1);
			if(idx==-1) {
				dp[LIS++] = arr[i][1];
			}
			else {
				dp[idx] = arr[i][1];
			}
		}
		
		System.out.println(N - LIS);
		
	}
	
	static int binarySearch(int num, int start, int end, int idx) {
		int res = 0;
		
		while(start<=end) {
			int mid = (start+end) / 2;
			
			if(dp[mid] >= num) {
				res = mid;
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}	
		}
		
		if(start==idx) {
			return -1;
		}
		else {
			return res;
		}
	}
}
