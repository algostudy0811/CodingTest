package week12_1;

import java.io.*;
import java.util.*;

// 모두의 마블
/**
 * 인접하다는 조건을 보고 index를 고려해야 한다고 생각할 수 있으나,
 * 골드를 획득하는 과정에서 레벨이 변하지 않기 때문에, 최대 레벨이 고정이 되면서 업그레이드를 해야 최대 골드 획득
 * 인접한 상황을 고려할 필요 없이 어차피 더해질 값들이므로 인덱스 굳이 고려 X
 * 시간 복잡도 : O(n)
 */
public class BOJ_12845_최규림 {

	static int N;
	static int[] cards;	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cards = new int[N];
		int maxValue = -1;
		int sumValue = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
			sumValue += cards[i];
			if(cards[i] > maxValue) maxValue = cards[i];
		}
		
		int answer = maxValue * (N-2) + sumValue;
		System.out.println(answer);
	}

}
