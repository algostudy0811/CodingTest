package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ����Ž������ 0~10����� �� ��緹�� ������ M�̸��ΰ��� �ּҰ� ����
 * ������ �̸��� ��쵵 �Ѱ��� ���� ������ M���� �ǹǷ� �������
 * 45�� �ҿ�
 * 23484KB 208ms
 */
public class BOJ_2343_�̼��� {

	//1��*10��
	private static final int END = 1_000_000_000;
	//�ּҰ�
	private static int result = Integer.MAX_VALUE;
	private static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] array = new int[N];
		
		//�迭�� �ִ밪�� ���ϱ� ����
		//�ִ밪�� ���ؼ� �ִ밪���� ���� ��緹�̴� ����⶧��
		//���� ������ 50�ۿ��� Ʋ��
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
			max = Math.max(array[i], max);
		}
		//����Ž��
		binarySearch(array, max);
		System.out.println(result);
		
	}
	
	private static void binarySearch(int[] array, int max) {
		int start = max;
		int end = END;
		
		int mid = 0;
		
		while(start<=end) {
			mid = (start + end) / 2;
			boolean flag = check(array, mid);
			//��緹�� ���ο� ���� ���� �ٸ��� ����
			if(flag) {
//				System.out.println(start+":"+end+":"+mid);
				result = Math.min(result, mid);
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
	}
	
	private static boolean check(int[] array, int num) {
		
		//��緹�� ����
		int blueLayNum = 0;
		//������ �� �ð�
		int tmp = 0;
		for(int i=0; i<N;i++) {
			//���Ǹ� ���Ѱ��� ��緹�� �ð����� Ŀ���� ��緹�� ������ +1�ϰ� �ð��� �ʱ�ȭ
			if(tmp+array[i]>num) {
				blueLayNum++;
				tmp = array[i];
			}
			else {
				tmp += array[i];
			}
		}
		//tmp�� 0�� �ƴ� ���ڰ� ������ ���ǰ� ���������Ƿ� +1�߰�
		if(tmp !=0) blueLayNum++;
		
		//������ �̸��ΰ͵� �������
		if(blueLayNum<=M) {
//			System.out.println(blueLayNum+" "+num);
			return true;
		}
		
		return false;
	}
}
