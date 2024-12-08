import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * X 와 Y가 같으면 즉 승률이 100이므로 더이상 바뀔 수 없으므로 -1 출력
 * 그외에 먼저 승률을 구하고 +1을 하고 나서 횟수를 빼면 됨
 * 11524KB 68ms
 */
public class BOJ_1072_이석범 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		
		//승률 소수점 이하는 버림
		//여기서 100을 나중에 곱하면 오차가 발생? -> 틀렸습니다가 나옴
		int z = (int) Math.floor(((1.0 * Y  * 100) / X));
//		int z = (int) Math.floor(((1.0 * Y) / X) * 100);
		
		if(z>=99) {
			System.out.println(-1);
		}
		else {
			double a = Math.ceil((double)(100*Y - (z+1)*X) / (z-99));
			
			int res = (int) a;
			
			System.out.println(res);
		}
		
	}
}
