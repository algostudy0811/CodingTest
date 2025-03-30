package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * ��������� ���� �ּҰ� �Ǵ� ���� ��ü�� �������� ���ʰ� �������� ����� ���� �߰��� �Ǿ���
 * ���� �߰��� �Ǵ� ��ġ�� ã���� ��
 * 50112KB 532ms
 */
public class BOJ_2141_�̼��� {
	
	static int N;
	static int[][] input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		input = new int[N][2];
		
		long sum = 0;
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int home = Integer.parseInt(st.nextToken());
			int people = Integer.parseInt(st.nextToken());
			
			input[i][0] = home;
			input[i][1] = people;
			sum += people;
		}
		
		long middle = (sum+1) / 2;
		
		//�ε����� ����
		Arrays.sort(input, (o1, o2)-> 
			o1[0] - o2[0]
		);
		
		long pre = 0;
		
		int res = 0;
		
		for(int i=0; i<N;i++) {
			pre += input[i][1];
			if(pre >= middle) {
				res = input[i][0];
				break;
			}
		}
		
		System.out.println(res);
		
	}
}
