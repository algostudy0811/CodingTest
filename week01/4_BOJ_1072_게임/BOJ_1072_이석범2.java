package czx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * X �� Y�� ������ �� �·��� 100�̹Ƿ� ���̻� �ٲ� �� �����Ƿ� -1 ���
 * �׿ܿ� ���� �·��� ���ϰ� +1�� �ϰ� ���� Ƚ���� ���� ��
 * 11556KB 64ms
 */
public class BOJ_1072_�̼���2 {
	
	static long X;
	static long Y;
	static int z;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		//�·� �Ҽ��� ���ϴ� ����
		//���⼭ 100�� ���߿� ���ϸ� ������ �߻�?
		z = (int) Math.floor(((1.0 * Y  * 100) / X));
		
		if(z >=99) {
			System.out.println(-1);
		}
		else {
			int res = binarySearch();
			System.out.println(res);
		}
		
	}
	
	private static int binarySearch() {
		
		int start = 0;
		int end = 1_000_000_000;
		
		int res = 0;
		
		while(start<=end) {
			int mid = (start + end) / 2;
			
			int next = (int) Math.floor(((1.0 * (mid+Y)  * 100) / (X+mid)));
			
			if(next==z) {
				start = mid+1;
			}
			else {
				end = mid-1;
				res = mid;
			}
		}
		
		return res;
	}
}
