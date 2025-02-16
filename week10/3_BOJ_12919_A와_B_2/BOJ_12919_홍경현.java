package STUDY.week10;

import java.util.*;
import java.io.*;

/* 11,504KB 64ms
[문제 해석]
A와 B로만 이루어진 영단어
두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임
- 문자열 뒤에 A를 추가
- 문자열의 뒤에 B를 추가하고 뒤집기

S를 T로 만들 수 있는지 없는지 알아내는 프로그램

[입력]
1. S (1<=S.length()<=49)
2. T (2<=T.length()<=50)
S < T

[출력]
바꿀 수 있으면 1
없으면 0

[문제 해결 프로세스]
T의 마지막 index 부터 탐색
1. charAt(0) == 'B'
    뒤집기 + B 제거
2. charAt(idx) == 'A'
    A 제거
 */
public class BOJ_12919_홍경현 {

    static String str1, str2;
    static int res;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();

        game(str2.length()-1, str2);

        System.out.println(res);
    }

    static void game(int len, String str){
        if(len == str1.length()-1){
            if(str1.equals(str)) res = 1;
            return;
        }

        char c = str.charAt(len);
        if(c=='A') game(len-1, str.substring(0, len));
        if(str.charAt(0) == 'B') game(len-1, reverse(str));
    }

    static String reverse(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(1));
        return sb.reverse().toString();
    }
}
