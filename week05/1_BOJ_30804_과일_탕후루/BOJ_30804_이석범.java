package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


/*
 * �� ������ ���
 * ���� ��ġ���� 2���� �����͸� �̿�
 * 1 1 2 2 3 1 -> �̰��� ��� 1122�� 4��������
 * 37292KB 340ms
 */
public class BOJ_30804_�̼��� {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			int n = Integer.parseInt(st.nextToken());
			
			input[i] = n;
			
		}
		
		int result = 1;
		
		//�������ͷ� Ư�� ���ǿ� �����Ҷ����� right�� �����̰� ������ �ȸ����� left�� �����̸� �ȴ�.
		int left = 0;
		int right = 0;
		
		//�������͸� �����̸鼭 ���� �����ϱ� ���� ���
		HashMap<Integer, Integer> map = new HashMap<>();
		
		
		while(right < N) {
			
//			System.out.println(left+" "+right);
//			System.out.println(result);
			
			//����� 2������ ��� 
			//���� ������ �����Ѵٸ� �������� +1 �߰�
			if(map.containsKey(input[right])) {
				map.put(input[right], map.get(input[right])+1);
			}
			//�׿ܿ�
			else {
				//���� ������ 2���ִµ� ���� ������ ��� 3���� ������ �ǹǷ� left�� ����������
				if(map.size()==2) {
					//left�� ������ ã�� ���� ������ 1���� map���� ����
					int num = map.get(input[left]);
//						System.out.println("num: "+num);
					
					if(num==1) map.remove(input[left]);
					
					//�׿ܿ��� -1�� �Ѱ��� ����
					else {
						map.put(input[left], num-1);
					}
					
					//left�� �� +1 ����
					left++;
					continue;
				}
				
				map.put(input[right], 1);
			}
			
			//�ִ밳�� ����
			result = Math.max(result, right-left+1);
			
			right++;



		}
		
		System.out.println(result);
		

	}
}
