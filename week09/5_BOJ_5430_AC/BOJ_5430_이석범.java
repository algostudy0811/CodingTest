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
public class BOJ_5430_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		A: for(int t=1; t<=T;t++) {
			String tmp = br.readLine();
			
			int num = Integer.parseInt(br.readLine());
			
			String inputArr = br.readLine();
			
			//만약 개수가 0인데 제거하는 D가 있으면 에러이므로 다음으로 넘기기
			if(num==0 && tmp.contains("D")) {
				sb.append("error").append("\n");
				continue;
			}
			
			//입력받은 수 파싱
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
			
			//빼는 기준
			boolean isFirst = true;
			
			for(int i=0; i<tmp.length();i++) {
				if(tmp.charAt(i)=='R') {
					isFirst = !isFirst;
				}
				else {
					//만약 제거할건데 큐가 비어있으면 에러
					if(queue.isEmpty()) {
						sb.append("error").append("\n");
						continue A;
					}
					
					//뒤에서 빼면 pollFirst가 뒤에서 빼는 것
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
			
			//[1, 2, ] 이런식으로 toString하면 띄어쓰기가 되는데 띄어쓰기를 제거해야 성공
			sb.append(Arrays.toString(output).replace(" ", "")).append("\n");
			
		}
		System.out.println(sb);
		
	}
}
