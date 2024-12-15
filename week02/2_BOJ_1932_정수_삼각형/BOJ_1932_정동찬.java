package Q1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Q1932 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] T = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				T[r][c] = 0;
			}
		}
		
		int root = Integer.parseInt(br.readLine());
		T[0][0] = root;
		
		for (int lr = 1; lr < N; lr++) {
			String[] line = br.readLine().split(" ");
			
			// 삼각형에 값 넣기
			// line의 값에, 부모 요소의 값을 추가할 것
			for (int lc = 0; lc < lr + 1; lc++) {
				// 맨 좌측이면 자신 값에 부모 요소 값을 추가
				if (lc == 0) {
					T[lr][lc] = T[lr - 1][lc] + Integer.parseInt(line[lc]);
					continue;
				}
				
				// 맨 우측이면 자신 값에 부모 요소 값을 추가
				if (lc == lr) {
					T[lr][lc] = T[lr - 1][lc - 1] + Integer.parseInt(line[lc]);
					continue;
				}
				
				// 자신 값에 부모 요소 둘 중 큰 값을 추가
				T[lr][lc] = Integer.parseInt(line[lc]) + Math.max(T[lr - 1][lc - 1], T[lr - 1][lc]);
			}
		}

		// 맨 마지막 줄의 가장 큰 값을 출력
		System.out.println(Arrays.stream(T[N - 1]).max().getAsInt());
	}

}
