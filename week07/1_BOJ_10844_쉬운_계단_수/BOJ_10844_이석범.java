package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * n�� 1�� ��� 9
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7
 * 8
 * 9
 * n�� 2�� ��� 17
 * 10, 12
 * 21, 23
 * 32, 34
 * 43, 45
 * 54, 56
 * 65, 67
 * 76, 78
 * 87, 89
 * 98, 
 * n�� n�� ���
 * n�� 1~8�� ��� ���ڸ��� 2������ ��찡 ����
 * n�� 9�Ǵ� 0�� ��� 1������ ��츸 ����
 * 
 * dp[�ڸ���][����] -> [N][10]
 * �ڸ����� �ö����� �����ڸ� ���� m(0~9)�� ������ dp�� ����
 * ���� ������ ������
 * �ҿ�ð�: 15��
 * 11532KB 68ms
 */
public class BOJ_10844_�̼��� {
	
	static final int NUM = 101;
	static final long DIV = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[NUM][10];
		
		Arrays.fill(dp[1], 1L);
		dp[1][0] = 0;
		
		for(int i=2;i<NUM;i++) {
			for(int j=0; j<10;j++) {
				if(j==0) {
					dp[i][j] = dp[i-1][j+1];
				}
				else if(j==9) {
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				}
				dp[i][j] %= DIV;
			}
		}
		
		long result = 0;
		for(int i=0; i<10;i++) {
			result += dp[N][i];
		}
		
		System.out.println(result % DIV);
		
	}
}
