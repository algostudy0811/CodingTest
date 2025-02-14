package algorithm;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * output -> input으로 가는 거꾸로 처리
 * 11536KB 64ms
 */
public class BOJ_12919_이석범 {
	
	static String input, output;
	
	static int result = 0;
	
	static void back(String tmp) {
		if(tmp.length()==input.length()) {
			if(tmp.equals(input)) {
				result = 1;
				return;
			}
			else return;
		}
		
		int len = tmp.length();
		
		//문자열 reverse하는 방법 sb.reverse
		if(tmp.charAt(0)=='B') {
			String next = tmp.substring(1, len);
			 StringBuilder sb = new StringBuilder();
			 sb.append(next).reverse();
			 back(sb.toString());
		}
		
		
		
		if(tmp.charAt(len-1)=='A') {
			String next = tmp.substring(0, len-1);
			back(next);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine();
		output = br.readLine();
		
		back(output);
		
		System.out.println(result);
		
		
	}
}
