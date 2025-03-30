package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 슬라이딩 윈도우 사용
 * result long타입 사용
 * 39460KB 208ms
 */
public class BOJ_3078_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String[] score = new String[N];
		
		for(int i=0; i<N;i++) {
			String tmp = br.readLine();
			
			score[i] = tmp;
		}
		
		long cnt = 0;
		
		int[] lenList = new int[21];
		
		int startIdx = 0;
		int endIdx = 0;
		
		for(int i=0; i<=K;i++) {
			String tmp = score[endIdx];
			
			int len = tmp.length();
			
			if(lenList[len] > 0) {
				cnt += lenList[len];
			}
			
			lenList[len] += 1;
			
			
			endIdx++;
		}
		
		endIdx--;
		
//		System.out.println(cnt);
		
		
		for(int i=endIdx+1;i<N;i++) {
			int start = score[startIdx].length();
			lenList[start] -= 1;
			startIdx++;
			
			int end = score[i].length();
			
			if(lenList[end] > 0) {
				cnt += lenList[end];
			}
			
			lenList[end] += 1;
		}
		
		System.out.println(cnt);
		
		
	}
}
