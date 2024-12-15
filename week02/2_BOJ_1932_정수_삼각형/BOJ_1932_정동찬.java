package Q1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			
			for (int lc = 0; lc < lr + 1; lc++) {
				if (lc == 0) {
					T[lr][lc] = T[lr - 1][lc] + Integer.parseInt(line[lc]);
					continue;
				}
				if (lc == lr) {
					T[lr][lc] = T[lr - 1][lc - 1] + Integer.parseInt(line[lc]);
					continue;
				}
				
				T[lr][lc] = Integer.parseInt(line[lc]) + Math.max(T[lr - 1][lc - 1], T[lr - 1][lc]);
			}
		}

		System.out.println(Arrays.stream(T[N - 1]).max().getAsInt());
	}

}
