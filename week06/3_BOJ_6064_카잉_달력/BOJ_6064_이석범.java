package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ���� ���� ����
 * 1. M�� 10�̰� N�� 12�϶��� ����
 * N�� �������� y�� ��ӵǰ� �ݺ�
 * y�� 9�� ��� X�� ������� 9 -> 1(9+12 % 10) -> 3(1+12 % 10)
 * ���� X�� x�� ��츦 ã���� ��
 * -1�� ���� �ݺ��ϴٰ� �����Ǵ� ������ ���� ���
 * 35524KB 248ms
 */
public class BOJ_6064_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		A: for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[M+1];
			
			//x�κ��� ���ϴ� ���
			//�ۿ� �κ��� y�� M���� ū ���
			//M�� 4 y�� 9�� ��� M�� ���� 1�� �Ǿ����-> �̶� %�� �� ���� 0�̸� M�� ���� �Ǿ���� M�� �Ǵ� ���� �����ϹǷ�
			//�ȵɰ�� �״�� y�� ���� ��
			int X = y > M ? (y % M == 0 ? M : y % M) : y;
			int cnt = y;
			
			//�湮 �� ������ ��� -1 ���
			while(!visited[X]) {
				//X�� x�ΰ��
				if(X==x) {
					sb.append(cnt).append("\n");
					continue A;
				}
				
				//�湮ó��
				visited[X] = true;
				
				//y�� ���� ��� �Ǿ��ϹǷ� N�� ����������
				X += N;
				
				if(X > M) {
					X = X % M;
				}
				//0�ΰ�� M�״�� �ǰ� �ؾ���
				if(X % M == 0) {
					X = M;
				}
				
				cnt += N;
				
			}
			
			sb.append(-1).append("\n");
			
		}
		System.out.println(sb);
		
	}
}
