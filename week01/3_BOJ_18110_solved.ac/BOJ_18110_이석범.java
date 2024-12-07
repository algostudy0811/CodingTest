package czx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * ��� ������� �ǰ��� �ް� ���� �� ������ ���� �߶� ���
 * �ݿø� �Լ��� round
 * 46952kb	316ms
 */
public class BOJ_18110_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] voteList = new int[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			voteList[i] = Integer.parseInt(st.nextToken());
		}
		
		//����
		Arrays.sort(voteList);
		
		//���ܵǴ� ��� ��� �ݿø�
		int ex = (int)Math.round(N * 0.15);
		
//		System.out.println(ex);
		int sum = 0;
		for(int i=ex;i<N-ex;i++) {
			sum += voteList[i];
		}
		
		//double�� �ٲٱ� ���� 1.0�� ����
		int res = (int)Math.round((1.0*sum) / (N - (ex*2)));
		System.out.println(res);
		
	}
}
