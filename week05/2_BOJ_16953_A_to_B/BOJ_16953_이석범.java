package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 역으로 끝에서부터 시작부분으로 탑다운으로 풀면 빠르게 풀수 있음
 * 짝수인 2로 끝나면 나누기 2, 끝이 1인 경우 % 10으로 첫째자리를 자르면 됨
 * BFS와 같이 큐에 넣고 최소가 되는 시간을 출력하면 됨
 * 11584KB 64ms
 */
public class BOJ_16953_이석범 {

	static int BFS(int s, int e) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(e);

		int cnt = 0;

		while(!queue.isEmpty()) {

			int size = queue.size();
			while(--size>=0) {
				int n = queue.poll();

//				System.out.println(n);

				if(n==s) return cnt+1;

				//끝자리가 1이고 시작 수보다 같거나 큰것만 큐에 저장
				if(n%10==1 && (s <= (n / 10))) {
					queue.offer(n/10);
				}

				//2로 나누어떨어지고 시작 수보다 같거나 큰것만 큐에 저장
				if(n % 2 == 0 && (s <= (n / 2))) queue.offer(n/2);
			}

			cnt++;
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int result = BFS(s, e);
		System.out.println(result);
	}
}