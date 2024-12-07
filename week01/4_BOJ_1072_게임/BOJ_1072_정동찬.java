import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static int func(int X, int Y) {
		long numerator = 100L * Y;
		int P = (int) (numerator / X);
		if (P >= 99) return -1;
		
		int Z = (int) Math.ceil((double) ((1 + P) * X - 100 * Y) / (99 - P));
		return Z;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int X = Integer.parseInt(input[0]);
		int Y = Integer.parseInt(input[1]);
		
		System.out.println(func(X, Y));
	}

}
