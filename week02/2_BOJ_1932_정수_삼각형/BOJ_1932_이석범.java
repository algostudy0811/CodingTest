package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n이 주어지면 크기가 n인 삼각형이 만들어짐
 * 이때 n의 크기는 밑변의 길이
 * 즉 arr[500][500]으로 입력값을 만듦
 * 일단 먼저 일반적인 방식을 생각할 경우 백트래킹을 생각하는데
 * 백트래킹은 시간이 많이 걸림
 * 다음으로 생각할 건 dp
 * dp의 경우 마지막의 하는 일을 반복해서 사용하면 됨
 * 마지막에 하는 행위는 N-1번째 줄에서 왼쪽, 오른쪽 자식에 값을 더하는데 그 값이 최대가 되야함
 * 즉 N번째 줄에 J번째라고 한다면 N-1번째 줄의 누적된 J, J+1 중 큰값에 N번째의 J번째 값을 더하면 된다
 * 이 dp의 경우 모든 배열을 돌기 때문에 최대 O(N^2)이 발생
 * 
 * 25276KB 184ms
 */
public class BOJ_1932_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][N];
		int[][] dp = new int[N+1][N];
		//한줄씩 개수가 1씩 늘어나므로 편의상 0이 아닌 1부터 시작하도록 설정
		for(int i=1; i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<N+1;i++) {
			for(int j=0; j<i;j++) {
				//현재 위치에 누적된 값을 구해야되므로 입력된 배열의 현재위치의 값도 추가
				dp[i][j] += arr[i][j];
				if(i!=N) {
					//자신의 왼쪽 오른쪽에 현재값이 큰경우 교체 아니면 그대로하기
					dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
					dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
				}
			}
		}
		
		int res = 0;
		
		//맨 마지막에는 마지막 배열을 돌면서 가장 큰값만 구하면 됨
		for(int i=0; i<N;i++) {
			res = Math.max(res, dp[N][i]);
		}
		
		System.out.println(res);
		
	}
}
