package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 
 * 130152KB 968ms
 */
public class BOJ_5430_�̼��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		A: for(int t=1; t<=T;t++) {
			String tmp = br.readLine();
			
			int num = Integer.parseInt(br.readLine());
			
			String inputArr = br.readLine();
			
			//���� ������ 0�ε� �����ϴ� D�� ������ �����̹Ƿ� �������� �ѱ��
			if(num==0 && tmp.contains("D")) {
				sb.append("error").append("\n");
				continue;
			}
			
			//�Է¹��� �� �Ľ�
			inputArr = inputArr.replace("[", "");
			inputArr = inputArr.replace("]", "");
			
			String[] inputList = inputArr.split(",");
//			int[] intList = Arrays.stream(inputList)
//					.mapToInt(Integer::parseInt)
//					.toArray();
	
			
//			System.out.println(inputList[0]+" "+inputList.length);
			
			Deque<Integer> queue = new ArrayDeque<>();
			
			for(int i=0; i<num;i++) {
				queue.push(Integer.parseInt(inputList[i]));
			}
			
			//���� ����
			boolean isFirst = true;
			
			for(int i=0; i<tmp.length();i++) {
				if(tmp.charAt(i)=='R') {
					isFirst = !isFirst;
				}
				else {
					//���� �����Ұǵ� ť�� ��������� ����
					if(queue.isEmpty()) {
						sb.append("error").append("\n");
						continue A;
					}
					
					//�ڿ��� ���� pollFirst�� �ڿ��� ���� ��
					if(!isFirst) {
						queue.pollFirst();
					}
					else {
						queue.pollLast();
					}
				}
			}
			
			int[] output = new int[queue.size()];
			int idx = 0;
			if(!isFirst) {
				while(!queue.isEmpty()) {
					output[idx++] = queue.pollFirst();
				}
			}
			else {
				while(!queue.isEmpty()) {
					output[idx++] = queue.pollLast();
				}
			}
			
			//[1, 2, ] �̷������� toString�ϸ� ���Ⱑ �Ǵµ� ���⸦ �����ؾ� ����
			sb.append(Arrays.toString(output).replace(" ", "")).append("\n");
			
		}
		System.out.println(sb);
		
	}
}
