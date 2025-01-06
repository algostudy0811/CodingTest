package week05;

import java.io.*;
import java.util.*;

/**
 * 투포인터
 * 1.  
 * 
 * 29180KB 276ms
 */
public class BOJ_30804_최규림 {

	static int N;
	static int[] arr;
	static int[] buckets = new int[10];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
				
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());			
		}
			
		int a; // 왼쪽 과일
		int b; // 오른쪽 과일
		int left = 0; // 왼쪽 위치
		int right = 0; // 오른쪽 위치
		int typeCnt = 0; // 과일 종류 개수 
		int totalCnt = 0; // 왼쪽 구간과 오른쪽 구간내의 과일 총 개수
		int answer = -1;
		
		// 오른쪽 과일이 마지막 과일을 가리킬 때까지 반복
		while(right < N) {		
			a = arr[left];
			b = arr[right];
			
			// 해당 과일이 처음 담긴다면, 과일 종류 증가
			if(buckets[b] == 0) typeCnt+=1;
			
			// 전체 과일 개수와 b 과일의 개수 증가
			totalCnt+=1;
			buckets[b]+=1;
			
			// 과일의 종류가 2개 초과됐다면, 왼쪽 포인터 이동
			if(typeCnt>2) {
				// 왼쪽 과일 개수 차감
				buckets[a]-=1;
				
				// 왼쪽 과일을 덜어내서, 해당 과일 개수가 0개가 됐다면, 종류 개수 차감
				if(buckets[a]==0) typeCnt-=1; 
				
				// a 과일을 덜어냈기 떄문에 전체 과일 개수 차감
				totalCnt-=1;
				left+=1;
			}
			right+=1;			
			answer = Math.max(answer,  totalCnt);			
		}
		
		System.out.println(answer);
	}

}
