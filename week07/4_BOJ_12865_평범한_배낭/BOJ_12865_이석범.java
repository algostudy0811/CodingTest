package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * knapsack ����
 * 51336KB 140ms
 */
public class BOJ_12865_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//�Է°�
		int[][] array = new int[N+1][2];
		
		for(int i=1; i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			array[i][0] = w;
			array[i][1] = v;
		}
		
		//N��°�� ���� ��� M+1�� �ִ��� ������ ä�ﶧ �ִ� value
		int[][] dp = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1; j<=M;j++) {
				//i��° ������ ���濡 ���� �ǵ� ���԰� j���� Ŭ ���
				if(array[i][0] > j) {
					//i-1��° ������ �־��� value�� ������
					dp[i][j] = dp[i-1][j];
				}
				//�۰ų� ���� ���
				else {
					//i-1��° ������ �־��� value�ų� i-1��° ������ j - i��° ������ ���Ը� �� value�� ū ���� ����
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-array[i][0]] + array[i][1]);
				}
			}
		}
		
		System.out.println(dp[N][M]);
		
	}
}
