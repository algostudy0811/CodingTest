package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 접근 과정
 * 1. M이 10이고 N이 12일때를 가정
 * N을 기준으로 y가 계속되게 반복
 * y가 9일 경우 X는 순서대로 9 -> 1(9+12 % 10) -> 3(1+12 % 10)
 * 따라서 X가 x인 경우를 찾으면 됨
 * -1인 경우는 반복하다가 루프되는 순간이 오는 경우
 * 35524KB 248ms
 */
public class BOJ_6064_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		A: for(int t=1; t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[M+1];
			
			//x부분을 구하는 경우
			//밖에 부분은 y가 M보다 큰 경우
			//M이 4 y가 9인 경우 M의 값은 1이 되어야함-> 이때 %를 한 값이 0이면 M의 값이 되어야함 M이 되는 수를 포함하므로
			//안될경우 그대로 y의 값이 됨
			int X = y > M ? (y % M == 0 ? M : y % M) : y;
			int cnt = y;
			
			//방문 즉 루프될 경우 -1 출력
			while(!visited[X]) {
				//X가 x인경우
				if(X==x) {
					sb.append(cnt).append("\n");
					continue A;
				}
				
				//방문처리
				visited[X] = true;
				
				//y의 값이 계속 되야하므로 N이 더해져야함
				X += N;
				
				if(X > M) {
					X = X % M;
				}
				//0인경우 M그대로 되게 해야함
				if(X % M == 0) {
					X = M;
				}
				
				cnt += N;
				
			}
			
			sb.append(-1).append("\n");
			
		}
		System.out.println(sb);
		
	}
}
