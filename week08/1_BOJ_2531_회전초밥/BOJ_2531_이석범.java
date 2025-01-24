package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2�����ͷ� 1�ε����� �����̸鼭 Ȯ�� 
 * ���� ť�� �����̹Ƿ� �迭 2���� �ٿ��� ���
 * 
 * 35�� �ҿ�
 * 17980KB 152ms
 */
public class BOJ_2531_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int kind = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());
		
		int[] input = new int[2*N];
		
		//����ť�̹Ƿ� �迭 2���� �̾� ����
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			input[i] = tmp;
			input[N+i] = tmp;
		}
		
		int start = 0;
		int end = 0;
		int cnt = 0;
		
		//�������� ������ ������ ���� ���� üũ
		//boolean���� �� ��� �߰��� �ߺ��Ǵ� ��� üũ�� �ȵ�
		// 1 2 1�� ��� �տ��� �̹� true�� ��
		int[] visited = new int[kind+1];
		
		int result = 0;
		
		while(start!=N) {
			//���� k���� ������ ���� �ݺ�
			if(end-start < k) {
				int n = input[end];
				//���� ó�� �Դ� �ʹ��̸� cnt+1
				if(visited[n]==0) cnt++;
				
				//�Դ� �� üũ
				visited[n] += 1;
				//end ������ �̵�
				end++;
			}
			//���� k�� ���� ��� ���
			else {
				//������ �ʹ� �ȸԾ����� ����+1
				if(visited[coupon]==0) result = Math.max(cnt+1, result);
				else result = Math.max(cnt, result);
				
//				System.out.println(start+" "+end);
//				System.out.println(result);
				
				int n = input[start];
				//���� �� üũ-1
				visited[n] -= 1;
				//���� ������ 0�̸� �ȸ��� �ɷ� ��� ����-1 
				if(visited[n]==0) cnt--;
				start++;
			}
		}
		
		System.out.println(result);
		
	}
}
