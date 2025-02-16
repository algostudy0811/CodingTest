package STUDY.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/* 11944KB 68ms
[문제 해석]
후위 표기식과 각 피연산자에 대응하는 값
그 식을 계산하는 프로그램

[입력]
1. 피연산자의 개수 (1<=N<=26)
2. 후위 표기식
    피연산자는 A~Z의 영대문자
    A부터 순서대로 N개의 영대문자만 사용
    길이는 100을 넘지 않음
3. 피연산자에 대응하는 값 (<=100)

값들은 항상 -20억보다 크거나 같고 20억보다 작거나 같음

[출력]
계산 결과를 소숫점 둘째 자리까지 출력

[문제 해결 프로세스]
map에 피연산자와 대응하는 값 저장
후위표기식
-> charAt(i)의 값이 피연산자이면 map에서 get한 값을 deque에 push
-> charAt(i)의 값이 피연산자가 아니면 deque에서 2개 pop해서 연산 후 push
 */
public class BOJ_1935_홍경현 {

    static Map<Integer, Double> map = new HashMap<>();
    static Deque<Double> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        for(int i=0; i<N; i++){
            map.put(i, Double.parseDouble(br.readLine()));
        }

        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c-'A')) deque.push(map.get(c-'A'));
            else{
                Double b = deque.pop();
                Double a = deque.pop();
                calc(a, b, c);
            }
        }

        Double res = deque.pop();
        System.out.printf("%.2f", res);
    }

    static void calc(Double a, Double b, char c){
        switch (c){
            case '+':
                deque.push(a+b);
                break;
            case '-':
                deque.push(a-b);
                break;
            case '*':
                deque.push(a*b);
                break;
            case '/':
                deque.push(a/b);
                break;
        }
    }
}
