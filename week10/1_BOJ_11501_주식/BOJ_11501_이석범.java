package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 역방향으로 탐색
 * 이전의 수보다 다음날 수가 크면 주식을 사서 파는게 좋으므로 차이만큼 더함
 * 이전 수가 크면 기준을 바꿈
 * 311220KB 1152ms
 */
public class BOJ_11501_이석범 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		int[] arr;
		
		for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int num = arr[N-1];
			long result = 0;
			
			for(int i=N-2; i>=0;i--) {
				if(num >= arr[i]) result += num - arr[i];
				else num = arr[i];
			}
			
			
			sb.append(result).append("\n");
			
		}
		
		System.out.println(sb);
	}
}
