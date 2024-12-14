package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n�� �־����� ũ�Ⱑ n�� �ﰢ���� �������
 * �̶� n�� ũ��� �غ��� ����
 * �� arr[500][500]���� �Է°��� ����
 * �ϴ� ���� �Ϲ����� ����� ������ ��� ��Ʈ��ŷ�� �����ϴµ�
 * ��Ʈ��ŷ�� �ð��� ���� �ɸ�
 * �������� ������ �� dp
 * dp�� ��� �������� �ϴ� ���� �ݺ��ؼ� ����ϸ� ��
 * �������� �ϴ� ������ N-1��° �ٿ��� ����, ������ �ڽĿ� ���� ���ϴµ� �� ���� �ִ밡 �Ǿ���
 * �� N��° �ٿ� J��°��� �Ѵٸ� N-1��° ���� ������ J, J+1 �� ū���� N��°�� J��° ���� ���ϸ� �ȴ�
 * �� dp�� ��� ��� �迭�� ���� ������ �ִ� O(N^2)�� �߻�
 * 
 * 25276KB 184ms
 */
public class BOJ_1932_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][N];
		int[][] dp = new int[N+1][N];
		//���پ� ������ 1�� �þ�Ƿ� ���ǻ� 0�� �ƴ� 1���� �����ϵ��� ����
		for(int i=1; i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<N+1;i++) {
			for(int j=0; j<i;j++) {
				//���� ��ġ�� ������ ���� ���ؾߵǹǷ� �Էµ� �迭�� ������ġ�� ���� �߰�
				dp[i][j] += arr[i][j];
				if(i!=N) {
					//�ڽ��� ���� �����ʿ� ���簪�� ū��� ��ü �ƴϸ� �״���ϱ�
					dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
					dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
				}
			}
		}
		
		int res = 0;
		
		//�� ���������� ������ �迭�� ���鼭 ���� ū���� ���ϸ� ��
		for(int i=0; i<N;i++) {
			res = Math.max(res, dp[N][i]);
		}
		
		System.out.println(res);
		
	}
}
