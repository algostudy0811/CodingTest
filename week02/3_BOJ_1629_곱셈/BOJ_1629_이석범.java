package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ��ⷯ ���� A*B mod c = (A mod c * B mod c) mod c�� �ȴ�
 * �� A^B���� B�� ¦���̸� A^B�� B/2�� ������ 2�����
 * B�� Ȧ���� A�� �ѹ��� ����
 * 	11508KB 68ms
 */
public class BOJ_1629_�̼��� {
	
	static long A, B, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		long res = cal(A, B);
		
		System.out.println(res);
		
	}
	
	public static long cal(long A, long B) {
		if(B==1) {
			return A % C;
		}
		
		//���� ����
		long cur = cal(A, B/2);
		
		//Ȧ�� �� ��� A�� ����
		if(B % 2 == 1) {
			return (cur * cur % C) * A % C;
		}
		return cur * cur % C;
	}

}
