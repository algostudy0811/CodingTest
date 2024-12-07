package czx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 인접한 카드를 붙일 수 있음 -> 붙일 경우 레벨은 그대로
 * 레벨은 그대로지만 골드는 레벨의 합
 * 40 30 50 40 30 일 경우 90 + 80 + 80 + 90 -> 즉 가장 수가 큰 것을 제외한 것들의 합 + 최대값 * N-1
 * 	11808kb	72ms
 */
public class BOJ_12845_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] cardList = new int[N];
		
		int max = Integer.MIN_VALUE;
		int sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			cardList[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, cardList[i]);
			sum += cardList[i];
		}
		
		//n이 1일 경우 합성을 못하므로 0
		if(N==1) {
			System.out.println(0);
		}
		//2개 이상일 경우
		else {
			int res = (sum - max) + (max * (N-1));
			
			System.out.println(res);
		}
	}
}
