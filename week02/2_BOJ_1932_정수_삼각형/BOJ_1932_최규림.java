package algorithm0811.week02;

import java.io.*;
import java.util.*;

// 정수 삼각형
/**
 * 삼각형 아래층에서부터 최대값들 더해가면서 진행
 * 시간 복잡도 :O(N^2)
 * 25608KB, 260ms
 */
public class BOJ_1932_최규림 {
	
	static int N;
	static int[][] arr;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());			
			for(int c=0; c<=r; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r=N-1; r>0; r--) {
			for(int c=0; c<N-1; c++) {
				arr[r-1][c] += Math.max(arr[r][c], arr[r][c+1]);
			}
		}
	
		System.out.println(arr[0][0]);		
	}
}
