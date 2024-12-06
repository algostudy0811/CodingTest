package week12_1;

import java.io.*;
import java.util.*;

/*
 * n의 범위가 크기 때문에 정렬 불가
 * 입력 받는 과정에서 전체 합 구하기
 * 우선순위 큐를 사용해서 제거 대상(최대값과 최소값) 총합에서 빼기
 * 시간 복잡도 : O(N log N)
 */
public class BOJ_18110_최규림 {

	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int sumValue = 0;
		PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>((o1, o2) -> o2-o1);
		PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
		
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			sumValue += x;
			maxQ.add(x);
			minQ.add(x);			
		}
		
		// 절사평균		
		double m = Math.round((double)N * 15 / 100);		
		for(int i=0; i<m; i++) {
			int maxValue = maxQ.poll();
			int minValue = minQ.poll();
			sumValue -= maxValue;
			sumValue -= minValue;	
		}
				
		int answer = (int)Math.round((double) sumValue / (N - 2 * m));
		System.out.println(answer);
	}

}
