package week06;

import java.io.*;
import java.util.*;

/**
 * 1. HashMap 활용
 * 2. 이름과 번호를 각각의 key로 활용하는 HashMap에 삽입
 * 3. M번의 입력 중, 입력값이 숫자인지 문자인지 판별 후, 적절한 map에서 get
 * 
 * 126032KB, 708ms
 */
public class BOJ_1620_최규림 {

	static int N, M;
	static Map<String, Integer> strMap = new HashMap<>();
	static Map<Integer, String> intMap = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++) {
			String name = br.readLine();
			strMap.put(name, i);
			intMap.put(i, name);
		}
		
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			// 입력값이 양수라면
			if(input.matches("\\d+")) {
				int num = Integer.parseInt(input);				
				sb.append(intMap.get(num)).append('\n');
			} else {
				sb.append(strMap.get(input)).append('\n');
			}
		}

		System.out.println(sb);
	}

}
