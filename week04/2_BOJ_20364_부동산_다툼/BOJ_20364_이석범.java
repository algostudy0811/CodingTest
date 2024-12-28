package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Ʈ���� ������ �ڽ��� ������ �ڽİ� ���� �ڽ��� ��ȣ��
 * 2K, 2K+1�̹Ƿ� ������ �ڽ��� �ε����� 1���� �θ� ��带 ���鼭 true�� ���� Ȯ���ϴ� ������ ���� �� ��ȣ ����
 * O(NlogN)�� �ð����⵵�� ����
 * 50908KB 392ms
 */
public class BOJ_20364_�̼��� {
	
	static boolean[] graph;
	static int N, Q;
	
	static int check(int idx) {
		
		int res = 0;
		int tmp = idx;
		
		//��Ʈ ����� 1���� Ȯ��
		while(tmp>0) {
			//�̹� �����ϴ� ���
			if(graph[tmp]) {
				res = tmp;
			}
			
			//Ȧ���� ��� �߰������� 1�� ��
			if(tmp % 2 == 1) {
				tmp--;
			}
			
			tmp = tmp / 2;
		}
		
		if(res==0) {
			graph[idx] = true;
		}
		
		return res;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N+1];
		
		for(int i=0; i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			
			int res = check(idx);
			sb.append(res).append("\n");	
		}
		
		System.out.println(sb);
		
	}
}
