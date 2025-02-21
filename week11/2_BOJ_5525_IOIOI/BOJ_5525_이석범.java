package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	28572KB 180ms
 */
public class BOJ_5525_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[len];
		
		String tmp = br.readLine();
		for(int i=0; i<tmp.length();i++) {
			char a = tmp.charAt(i);
			
			arr[i] = a == 'I' ? 1 : 0;
		}
		
		int[] key = new int[2*N+1];
		for(int i=0; i<2*N+1;i++) {
			key[i] = i % 2 == 0 ? 1 : 0;
		}
		
		//cnt는 01의 개수를 찾기
		//result는 일치하는 문자열 개수
		int result = 0;
		int cnt = 0;
		
		for(int i=0; i<len;i++) {
			//배열의 끝 이후이면 break
			if(i + 2 >= len) {
				break;
			}
			
			if(arr[i] == 1 && arr[i+1] == 0 && arr[i+2]==1) {
				
				//다음으로 넘어가는것은 +2가 되야하므로 i++을 한번 더함
				cnt++;
				i++;
				
				//cnt--를 하는 이유는 겹치는 것이 있을 수 있으므로 -1을 함
				if(cnt == N) {
					result++;
					cnt--;
				}
			}
			else {
				cnt = 0;
			}
		}

//		50점
//		int start = 0;
//		int end = 0;
//		int cnt = 0;
//		A: while(start<len) {
//			
//			if(arr[start]==0) start++;
//			else {
//				
////				System.out.println(start);
//				
//				int cnt = 0;
//				end = start+1;
//				
//				while(true) {
//					
//					
//					if(cnt==N) {
//						result++;
////						System.out.println(start+" result");
//						break;
//					}
//					
//					if(end+1 >= len) break;
//					
//					if(arr[end]==0 && arr[end+1]==1) {
//						cnt++;
//						end += 2;
//					}
//					else {
//						start++;
//						continue A;
//					}
//					
//					
//				}
//				
//				start += 2;
//				
//				
//			}
//		}
		
//     틀림
//		A: for(int i=0; i<len-(2*N+1);i++) {
//			for(int j=0; j<(2*N+1);j++) {
//				if(arr[j+i]!=key[j]) continue A; 
//			}
////			System.out.println(i);
//			result += 1;
//		}
//		
		System.out.println(result);
		
	}
}
