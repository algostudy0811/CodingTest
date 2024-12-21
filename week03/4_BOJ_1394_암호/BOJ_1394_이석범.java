package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 한자리씩 보면서 계산을 하는것이 좋을 듯
 * a b c인 경우
 * a -> 1
 * aa -> 4
 * aaa -> 13
 * b -> 2
 * bb -> 8
 * 이므로 이전문자열들 * len + 현재문자 형태가 된다
 * 
 * 19928KB 192ms	
 */
public class BOJ_1394_이석범 {
	
	public static final int MOD = 900528;

	private static Map<Character, Integer> map;
	
	private static String word, password;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine();
		password = br.readLine();
		
		int wordLen = word.length();
		int pwdLen = password.length();
		
		//특정 문자가 몇번째인지 체크하기 위해 맵사용
		map = new HashMap<>();
		for(int i=0; i<wordLen;i++) {
			map.put(word.charAt(i), i+1);
		}

		//만약 한자리 수이면 바로 출력
		if(pwdLen==1) {
			System.out.println(map.get(password.charAt(0)));
			return;
		}
		
		int res = 0;

		//이전 문자열들을 가져오면서 계산
		//모듈러 연산의 특징
		//(a + b) % mod N -> ( (a mod N) + (b mod N) ) mod N 의 형태로 계산 가능
        for (int i = 0; i < pwdLen; i++) {
            char c = password.charAt(i);
            res = (res * wordLen + (map.get(c) % MOD)) % MOD;
        }
        
        System.out.println(res);
		
	}
}
