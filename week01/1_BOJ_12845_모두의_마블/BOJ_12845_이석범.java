package czx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ������ ī�带 ���� �� ���� -> ���� ��� ������ �״��
 * ������ �״������ ���� ������ ��
 * 40 30 50 40 30 �� ��� 90 + 80 + 80 + 90 -> �� ���� ���� ū ���� ������ �͵��� �� + �ִ밪 * N-1
 * 	11808kb	72ms
 */
public class BOJ_12845_�̼��� {
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
		
		//n�� 1�� ��� �ռ��� ���ϹǷ� 0
		if(N==1) {
			System.out.println(0);
		}
		//2�� �̻��� ���
		else {
			int res = (sum - max) + (max * (N-1));
			
			System.out.println(res);
		}
	}
}
