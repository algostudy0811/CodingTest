import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static int[] countingSort(int n, int[] L) {
		int max = L[0];
		for (int idx = 0; idx < n; idx++) {
			if (max < L[idx]) max = L[idx];
		}
		
		int len = max + 1;
		
		int[] cnt = new int[len];
		Arrays.fill(cnt, 0);
		
		int[] ret = new int[n];
		Arrays.fill(ret, 0);
		
		for (int idx = 0; idx < n; idx++) {
			cnt[L[idx]] += 1;
		}

		for (int idx = 1; idx < len; idx++) {
			cnt[idx] += cnt[idx - 1];
		}

		for (int idx = n - 1; idx >= 0; idx--) {
			cnt[L[idx]] -= 1;
			ret[cnt[L[idx]]] = L[idx];
		}
		
		return ret;
	}
	
	public static int func(int n, int[] L) {
		if (n == 0) return 0;
		
		int trim = (int) Math.round((double) n * 0.15);
		int[] cs_L = countingSort(n, L);
		
		int sum = 0;
		for (int idx = trim; idx < n - trim; idx++) {
			sum += cs_L[idx];
		}
		return (int) Math.round((double) sum / (n - 2 * trim));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int[] L = new int[n];
		
		for (int idx = 0; idx < n; idx++) {
			L[idx] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(func(n, L));
		br.close();
	}
}
