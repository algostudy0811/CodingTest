package czx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 모든 사람들의 의견을 받고 정렬 후 개수에 따라서 잘라서 계산
 * 반올림 함수는 round
 * 46952kb	316ms
 */
public class BOJ_18110_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] voteList = new int[N];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			voteList[i] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(voteList);
		
		//제외되는 사람 계산 반올림
		int ex = (int)Math.round(N * 0.15);
		
//		System.out.println(ex);
		int sum = 0;
		for(int i=ex;i<N-ex;i++) {
			sum += voteList[i];
		}
		
		//double로 바꾸기 위해 1.0을 곱함
		int res = (int)Math.round((1.0*sum) / (N - (ex*2)));
		System.out.println(res);
		
	}
}
