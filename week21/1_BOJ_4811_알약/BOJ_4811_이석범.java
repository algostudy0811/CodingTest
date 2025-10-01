package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 30���� ������ ������ �������� ��� ž�ٿ�
 * 
 * 	11456KB 64ms
 */
public class BOJ_4811_�̼��� {
	
	static long[][] dp = new long[31][31];
	
	static long memo(int w, int h) {
		
		if(dp[w][h] != 0) return dp[w][h];
		
		if(w==0) return 1;
		
		if(w > 0) {
			dp[w][h] = memo(w-1, h+1);
		}
		
		if(h> 0) {
			dp[w][h] += memo(w, h-1);
		}
		
		return dp[w][h];
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		memo(30, 0);
		StringBuilder sb = new StringBuilder();
		//0�� 1���� ������ ���, 1�� �ݰ��� ������ ���
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			if(n == 0) break;
			
			sb.append(dp[n][0]).append("\n");
		}
		
		System.out.println(sb);
		
	}
}
