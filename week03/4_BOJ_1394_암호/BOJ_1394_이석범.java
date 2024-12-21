package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * ���ڸ��� ���鼭 ����� �ϴ°��� ���� ��
 * a b c�� ���
 * a -> 1
 * aa -> 4
 * aaa -> 13
 * b -> 2
 * bb -> 8
 * �̹Ƿ� �������ڿ��� * len + ���繮�� ���°� �ȴ�
 * 
 * 19928KB 192ms	
 */
public class BOJ_1394_�̼��� {
	
	public static final int MOD = 900528;

	private static Map<Character, Integer> map;
	
	private static String word, password;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine();
		password = br.readLine();
		
		int wordLen = word.length();
		int pwdLen = password.length();
		
		//Ư�� ���ڰ� ���°���� üũ�ϱ� ���� �ʻ��
		map = new HashMap<>();
		for(int i=0; i<wordLen;i++) {
			map.put(word.charAt(i), i+1);
		}

		//���� ���ڸ� ���̸� �ٷ� ���
		if(pwdLen==1) {
			System.out.println(map.get(password.charAt(0)));
			return;
		}
		
		int res = 0;

		//���� ���ڿ����� �������鼭 ���
		//��ⷯ ������ Ư¡
		//(a + b) % mod N -> ( (a mod N) + (b mod N) ) mod N �� ���·� ��� ����
        for (int i = 0; i < pwdLen; i++) {
            char c = password.charAt(i);
            res = (res * wordLen + (map.get(c) % MOD)) % MOD;
        }
        
        System.out.println(res);
		
	}
}
