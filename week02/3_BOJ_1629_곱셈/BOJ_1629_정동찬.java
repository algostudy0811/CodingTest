package Q1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1629 {
	// 나머지 분배 법칙
	// (a x b) % c = ((a % c) * (b % c)) % c
	
	public static long divide(long a, long b, long c) {
		// 가장 잘게 쪼개고 나면 b == 0 or 1
		// b == 0이면 1 % c = 1을 반환
		if (b == 0) return 1;

		// b == 1이면 half = a % c
		long half = divide(a, b / 2, c);

		long result = (half * half) % c;
		
		// b가 홀수라면 b = 2 * (b // 2) + 1
		// + 1 부분을 추가로 처리해야 함
		if (b % 2 != 0) {
			result = (result * (a % c)) % c;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tokens = br.readLine().split(" ");
		long A = Integer.parseInt(tokens[0]);
		long B = Integer.parseInt(tokens[1]);
		long C = Integer.parseInt(tokens[2]);

		System.out.println(divide(A, B, C));
	}

}
