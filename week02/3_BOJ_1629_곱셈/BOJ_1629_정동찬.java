package Q1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1629 {
	public static long divide(long a, long b, long c) {
		if (b == 0) return 1;

		long half = divide(a, b / 2, c);
		long result = (half * half) % c;
		
		if (b % 2 != 0) {
			result = (result * a) % c;
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
