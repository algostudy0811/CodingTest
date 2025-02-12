import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
메모리 : 11876
시간 : 76

문제
후위표기식을 계산하라

입력
1. N 26 이하
2. 영대문자만 길이 100이하
3. 값은 100 이하

출력
소수점 둘째짜리 까지

아이디어
stack
stack.size()가 2 이상이고 연산자를 만나면 계산
 */
public class BOJ_1935_김아영 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayDeque<String> stack = new ArrayDeque<>();
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        String input = st.nextToken();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(stack.size() < 2) {
                stack.add(String.valueOf(c));
                continue;
            }

            if(c >= 'A' && c <= 'Z'){
                stack.add(String.valueOf(c));
                continue;
            }

            String second = stack.pollLast();
            double num2 = check(second);
            String first = stack.pollLast();
            double num1 = check(first);

            switch (c){
                case '*':
                    stack.add(Double.toString(num1 * num2));
                    break;
                case '+':
                    stack.add(Double.toString(num1 + num2));
                    break;
                case '/':
                    stack.add(Double.toString(num1 /(double) num2));
                    break;
                case '-':
                    stack.add(Double.toString(num1 - (double) num2));
            }

        }
        Double answer = Double.parseDouble(stack.poll());
        System.out.println(String.format( "%.2f", answer));

    }

    public static double check(String input){
        if(input.length() != 1) return Double.parseDouble(input);

        if(input.charAt(0) >= 'A' && input.charAt(0) <= 'Z'){
            return arr[input.charAt(0) - 'A'];
        }

        return Double.parseDouble(input);
    }
}
