package week03;

import java.io.*;
import java.util.*;

// 시간초과
public class BOJ_1394_최규림 {
	
	static char[] cards, password;
	static Map<Character, Integer> map = new HashMap<>();
	static final int DIV = 900528;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cards = br.readLine().toCharArray();
		password = br.readLine().toCharArray();
		
		for(int i=0; i<cards.length; i++) {
			map.put(cards[i], i+1);
		}
		
		long answer = 0;
		// O(N*M) = 1_000_000 * 100 -> 시간초과
		for(int i=0; i<password.length; i++) {			
			answer += map.get(password[i]) * calculate(password.length - 1 - i) % DIV;
		}
		System.out.println(answer==0?0:answer% DIV);
	}
	
	static long calculate(int repeat) {
		long result = 1;
		for(int i=0; i<repeat; i++) {
			result *= cards.length;
			result %= DIV;
		}
		return result;
	}
}

