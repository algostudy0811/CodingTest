import java.util.Scanner;

class Main {
	public static int func(int count, int[] L) {
		
		int max = L[0];
		int sum = 0;
		for (int idx = 0; idx < count; idx++) {
			sum += L[idx];
			if (max < L[idx]) max = L[idx];
		}

		// L의 최대값 제외 총합 = sum - max
		// 최대값을 L의 길이 - 1만큼 더함 = max * (count - 1)
		return sum + max * (count - 2);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int count = sc.nextInt();
		int[] L = new int[count];
		for (int idx = 0; idx < count; idx++) {
			L[idx] = sc.nextInt();
		}

		System.out.println(func(count, L));
	}
}
