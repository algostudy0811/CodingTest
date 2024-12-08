import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * X 와 Y가 같으면 즉 승률이 100이므로 더이상 바뀔 수 없으므로 -1 출력
 * 그외에 먼저 승률을 구하고 +1을 하고 나서 횟수를 빼면 됨
 * 11556KB 64ms
 */
public class BOJ_1072_이석범2 {
	
	static long X;
	static long Y;
	static int z;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		//승률 소수점 이하는 버림
		//여기서 100을 나중에 곱하면 오차가 발생?
		z = (int) Math.floor(((1.0 * Y  * 100) / X));
		
		if(z >=99) {
			System.out.println(-1);
		}
		else {
			int res = binarySearch();
			System.out.println(res);
		}
		
	}
	
	private static int binarySearch() {
		
		int start = 0;
		int end = 1_000_000_000;
		
		int res = 0;
		
		while(start<=end) {
			int mid = (start + end) / 2;
			
			int next = (int) Math.floor(((1.0 * (mid+Y)  * 100) / (X+mid)));
			
			if(next==z) {
				start = mid+1;
			}
			else {
				end = mid-1;
				res = mid;
			}
		}
		
		return res;
	}
}
