package czx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * X �� Y�� ������ �� �·��� 100�̹Ƿ� ���̻� �ٲ� �� �����Ƿ� -1 ���
 * �׿ܿ� ���� �·��� ���ϰ� +1�� �ϰ� ���� Ƚ���� ���� ��
 * 11524KB 68ms
 */
public class BOJ_1072_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		
		//�·� �Ҽ��� ���ϴ� ����
		//���⼭ 100�� ���߿� ���ϸ� ������ �߻�? -> Ʋ�Ƚ��ϴٰ� ����
		int z = (int) Math.floor(((1.0 * Y  * 100) / X));
//		int z = (int) Math.floor(((1.0 * Y) / X) * 100);
		
		if(z>=99) {
			System.out.println(-1);
		}
		else {
			double a = Math.ceil((double)(100*Y - (z+1)*X) / (z-99));
			
			int res = (int) a;
			
			System.out.println(res);
		}
		
	}
}
