package week12_1;

import java.io.*;
import java.util.*;

/**
 * 승리 게임 횟수를 증가시킴과 동시에 승률을 한번에 계산하는 방법은 없음, 전체 게임 횟수도 같이 증가되기 때문에
 * 특정 값(추가된 게임 횟수)을 계산하고 Z의 변화 여부를 판단해야 하지만, X의 범위가 너무 커서 +1 씩 증가시키며 검증하는 방법은 비효율 O(N)인데 N이 너무 큼
 * 일일이 대입하면서 검증해야 하지만, N의 크기가 너무 큰 경우 이분탐색 활용, O(longN)
 * @@ 부동 소수점 이슈로 오답 다수 발생 @@
 * 
 * 시간 복잡도 : O(logN)
 * 메모리 : 14292	KB, 시간 : 104ms
 */
public class BOJ_1072 {
	
	static double X, Y, Z;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Double.parseDouble(st.nextToken());
		Y = Double.parseDouble(st.nextToken());		
		Z = (int) Math.floor(Y * 100 / X);
		if(Z >= 99) System.out.println(-1);
		else {					
			int minWinCnt = binarySearch(0, (long)X);			
			if(minWinCnt == 0) System.out.println(-1);
			else System.out.println(minWinCnt);			
		}

	}
	
	static int binarySearch(long left, long right) {
		long result = 0;
		while(right >= left) {
			long mid = (left + right) / 2; // 추가 진행할 게임 수			
			long newZ = (long) Math.floor((Y+mid)*100/(X+mid));				
			if(newZ == Z) {				
				left = mid + 1;
			} else {
				result = mid;
				right = mid - 1;
			}
		}		
		return (int)result;
	}	
}
