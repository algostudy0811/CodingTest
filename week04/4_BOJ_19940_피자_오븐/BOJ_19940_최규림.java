package week04;

import java.io.*;
import java.util.*;

/**
 * 
 * N의 최댓값 10_000_000
 * 1) ADDH (t+60) 의 연산을 먼저 처리 -> N의 값을 0~59의 범위로 줄이고 시작
 * 2) 5가지 방식으로 백트래킹, 백트래킹 과정에서 visited 배열을 사용하여 중복 접근 방지, 각 숫자에 할당된 cnt의 값이 더 크면 PASS
 * 3) N 값을 만났을 경우
 * 3-1) 버튼 누른 횟수가 동일하게 최소 횟수라면, 우선순위 비교해서 갱신 여부 결정
 * 3-2) 버튼 누른 횟수가 이전 횟수보다 더 적은 횟수라면 갱신
 * 18160KB, 128ms
 */
public class BOJ_19940_최규림 {

	static int T, N, num, totalCnt;
	static int addh, addt, mint, addo, mino;
	static int[] result, visited;
	static final int MAX_VALUE = 70;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		while(T-- != 0) {						
			reset();
			N = Integer.parseInt(br.readLine());						
			
			// 1) ADDH (t+60) 의 연산을 먼저 처리 -> N의 값을 0~59의 범위로 줄이고 시작
			int x = N/60;
			addh += x;
			N -= 60 * x;			
			
			// N은 0~59, MAX_VALUE == 70
			// ex) 53: 0(+60) -> 60(+3) -> 63(-10) -> 53 ==> 총 5번
			// 위의 예시와 같은 케이스를 통해 MAX_VALUE가 60이 아닌 70으로 해야함을 확인
			visited = new int[MAX_VALUE]; 			
			Arrays.fill(visited, MAX_VALUE);
			totalCnt = MAX_VALUE;
			
			// 변경된 N 값을 대상으로 백트래킹
			backTracking(0, 0);				
			setPrint();
		}
		System.out.println(sb.toString());
	}		
	
	// 2) 5가지 방식으로 백트래킹, 백트래킹 과정에서 visited 배열을 사용하여 중복 접근 방지, 각 숫자에 할당된 cnt의 값이 더 크면 PASS
	static void backTracking(int num, int cnt) {
		// 3) N 값을 만났을 경우
		if(num == N) {				
			// 3-1) 버튼 누른 횟수가 동일하게 최소 횟수라면, 우선순위 비교해서 갱신 여부 결정
			if(totalCnt == cnt) {								
				considerPriority();
			}
			// 3-2) 버튼 누른 횟수가 이전 횟수보다 더 적은 횟수라면 갱신
			else if(totalCnt > cnt) {												
				visited[N] = cnt;
				changeResult();
			}
			return;
		}		
						
		// ADDH
		int result = num + 60;
		if(result < visited.length && checkCondition(result, cnt)) {		
			visited[result] = cnt;
			addh += 1;
			backTracking(result, cnt + 1);
			addh -= 1;					
		}
		
		// ADDT
		result = num + 10;
		if(result < visited.length && checkCondition(result, cnt)) {			
			visited[result] = cnt;
			addt += 1;
			backTracking(result, cnt + 1);
			addt -= 1;			
		}
		
		// MINT
		result = num - 10<0?0:num-10;
		if(checkCondition(result, cnt)) {
			visited[result] = cnt;
			mint += 1;
			backTracking(result, cnt + 1);
			mint -= 1;
		}
		
		// ADDO
		result = num + 1;
		if(result < visited.length && checkCondition(result, cnt)) {
			visited[result] = cnt;
			addo += 1;
			backTracking(result, cnt + 1);
			addo -= 1;
		}
		
		// MINO
		result = num-1<0?0:num-1;
		if(checkCondition(result, cnt)) {
			visited[result] = cnt;
			mino += 1;
			backTracking(result, cnt + 1);
			mino -= 1;
		}		
		
	}
	
	// num에 처음 접했거나, 이전의 방문횟수보다 적은 경우 갱신 가능
	static boolean checkCondition(int num, int cnt) {
		return visited[num] == MAX_VALUE || visited[num] > cnt;
	}	
	
	// 누르는 방법 할당
	static void changeResult() {
		result[0] = addh;
		result[1] = addt;
		result[2] = mint;
		result[3] = addo;
		result[4] = mino;
	}
	
	// 순서대로 누르는 횟수가 이전보다 적으면 누르는 방법 갱신
	static void considerPriority() {
		if(addh < result[0]) changeResult();
		if(addt < result[1]) changeResult();
		if(mint < result[2]) changeResult();
		if(addo < result[3]) changeResult();
		if(mino < result[4]) changeResult();
	}
	
	// print문 세팅
	static void setPrint() {
		sb.append(result[0]).append(" ")
		.append(result[1]).append(" ")
		.append(result[2]).append(" ")
		.append(result[3]).append(" ")
		.append(result[4]).append('\n');
	}

	// 초기화
	static void reset() {
		result = new int[5];
		num = 0;
		addh = 0;
		addt = 0;
		mint = 0;
		addo = 0;
		mino = 0;
	}
}
