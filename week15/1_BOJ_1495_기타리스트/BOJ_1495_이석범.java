package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * dp�� ��� 1000*50���� �ð� �ʰ��� �ȳ�
 * dp 1���� �迭�� ����� ��� ���� ���� �ٲ�� ������ ��� �߻�
 * dp 2�������� ��������
 * 11948KB 64ms
 */
public class BOJ_1495_�̼��� {
	
	static int N, S, M;
	
	static int[] arr;
	
	static int result = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		
		for(int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		int[] dp = new int[M+1];
		int[] nextDp;
		Arrays.fill(dp, -1);
		
		dp[S] = 0;
		
		
		for(int i=0; i<N;i++) {

			nextDp = new int[M+1];
			
			int next = arr[i];
			
			for(int j=0; j<M+1;j++) {
				if(dp[j]==i) {
					
					if(j-next >= 0) {
						nextDp[j-next] = i+1;
					}
					
					if(j+next <= M) {
						nextDp[j+next] = i+1;
					}
					
				}
			}
			
			System.arraycopy(nextDp, 0, dp, 0, M+1);
			
		}
		
		for(int i=0; i<M+1;i++) {
			if(dp[i]==N) {
				result = i;
			}
		}
		
		
		System.out.println(result);
		
		
	}
}
