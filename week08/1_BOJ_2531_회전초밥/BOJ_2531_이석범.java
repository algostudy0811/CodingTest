package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2포인터로 1인덱스씩 움직이면서 확인 
 * 원형 큐의 형태이므로 배열 2개를 붙여서 사용
 * 
 * 35분 소요
 * 17980KB 152ms
 */
public class BOJ_2531_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int kind = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());
		
		int[] input = new int[2*N];
		
		//원형큐이므로 배열 2개를 이어 붙임
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			input[i] = tmp;
			input[N+i] = tmp;
		}
		
		int start = 0;
		int end = 0;
		int cnt = 0;
		
		//개수현재 포인터 사이의 존재 여부 체크
		//boolean으로 할 경우 중간에 중복되는 경우 체크가 안됨
		// 1 2 1일 경우 앞에서 이미 true가 됨
		int[] visited = new int[kind+1];
		
		int result = 0;
		
		while(start!=N) {
			//연속 k가지 먹을때 까지 반복
			if(end-start < k) {
				int n = input[end];
				//만약 처음 먹는 초밥이면 cnt+1
				if(visited[n]==0) cnt++;
				
				//먹는 것 체크
				visited[n] += 1;
				//end 포인터 이동
				end++;
			}
			//연속 k개 먹은 경우 계산
			else {
				//쿠폰인 초밥 안먹었으면 개수+1
				if(visited[coupon]==0) result = Math.max(cnt+1, result);
				else result = Math.max(cnt, result);
				
//				System.out.println(start+" "+end);
//				System.out.println(result);
				
				int n = input[start];
				//먹은 것 체크-1
				visited[n] -= 1;
				//만약 개수가 0이면 안먹은 걸로 취급 개수-1 
				if(visited[n]==0) cnt--;
				start++;
			}
		}
		
		System.out.println(result);
		
	}
}
