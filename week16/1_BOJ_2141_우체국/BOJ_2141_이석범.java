package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 사람까지의 합이 최소가 되는 경우는 우체국 기준으로 왼쪽과 오른쪽의 사람의 수가 중간이 되야함
 * 따라서 중간이 되는 위치를 찾으면 됨
 * 50112KB 532ms
 */
public class BOJ_2141_이석범 {
	
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
		
		//인덱스로 정렬
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
