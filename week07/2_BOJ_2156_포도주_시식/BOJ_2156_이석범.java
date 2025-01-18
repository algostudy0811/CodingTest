package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp�� Ǯ�� ��
 * dp�� ��� n��°�� ���������� �������� ���� ��Ÿ��
 * 0�� �� ��� 1�� ���� ���� ���
 * 20�� �ҿ�
 * 13704KB 96ms
 */
public class BOJ_2156_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] array = new int[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		//1�� ��� out of bound����
		if(N==1) {
			System.out.println(array[0]);
			return;
		}
		
		int[][] dp = new int[N][2];
		
		//�����Ѱ� üũ �غ��� �뵵
//		int[][] selected = new int[N][2];
		
		dp[0][0] = array[0];
		dp[0][1] = 0;
		
		dp[1][0] = array[0] + array[1];
		dp[1][1] = array[0];
		
		for(int i=2;i<N;i++) {
			//���� �����ָ� ������ �ȸ������� �߿�
			//�ȸ��ô� ��� -> �������� ������ ���� �ִ� ����
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
			
//			if(dp[i-1][0] >= dp[i-1][1]) {
//				dp[i][1] = dp[i-1][0];
//				selected[i][1] = 0;
//			}
//			else {
//				dp[i][1] = dp[i-1][1];
//				selected[i][1] = 1;
//			}
			
			//���ô� ��� -> �������� �������� ���� ���� 2��° ������ ���� �������� �ʰ� �������� �����ϴ� ���� ū�� ���� 
			dp[i][0] = Math.max(dp[i-1][1], dp[i-2][1]+array[i-1]) + array[i];
			
//			if(dp[i-1][1] >= dp[i-2][1]+array[i-1]) {
//				dp[i][0] = dp[i-1][1]+array[i];
//				selected[i][0] = 0;
//			}
//			else {
//				dp[i][0] = dp[i-2][1]+array[i-1] + array[i];
//				selected[i][0] = 1;
//			}
			
		}
		
//		for(int[] r: selected) {
//			for(int c:r) {
//				System.out.print(c+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
		
		
	}
}
