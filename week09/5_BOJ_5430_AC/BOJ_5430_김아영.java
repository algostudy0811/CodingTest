/*
메모리 114432
시간 760

문제
1. AC는 정수 배열에 연산을 하기위한 언어
2. R -> 뒤집기와 D -> 버리기가 존재
3. R은 수의 순서를 뒤집는 함수, D는 첫번째 수를 버리는 함수
4. 배열이 비었는데 D를 하면 에러
5. 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램 작성

입력
1. 테스트케이스 개수 T 최대 100개
2. 수행할 함 수 P 십만 이하
3. 배열의 수 n

출력
에러면 error
수행 결과

아이디어
단순 구현 => 시초
투 포인터 사용
 */
import java.util.*;
import java.io.*;
public class BOJ_5430_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 0; test_case < T; test_case++){

            st = new StringTokenizer(br.readLine());
            String P = st.nextToken();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            String inputs = st.nextToken();
            String[] inputArr = new String[0];

            if(inputs.length() > 2){
                inputs = inputs.substring(1, inputs.length() - 1);
                inputArr = inputs.split(",");
            }

            int i = 0;
            int start = 0;
            int end = inputArr.length - 1;
            int size = inputArr.length;

            stop : for(; i < P.length(); i++){
                switch (P.charAt(i)){
                    case 'R':
                        if(start == end) continue;
                        int save = start;
                        start = end;
                        end = save;
                        break;

                    case 'D':

                        if(size == 0) {
                            sb.append("error\n");
                            break stop;
                        }

                        if(start  < end) start ++;
                        else start--;

                        size--;

                        break;
                }
            }


            if(i == P.length()) {
                int[] answer = new int[Math.abs(end - start) + 1];
                //System.out.println(start + " " + end);
                int idx = 0;

                if(size == 0){
                    sb.append("[]\n");
                    continue;
                }

                else if(end >= start){
                    for(int j = start; j <= end; j++){
                        answer[idx++] = Integer.parseInt(inputArr[j]);
                    }
                }
                else if(start > end){
                    for(int j = start; j >= end; j--){
                        answer[idx++] = Integer.parseInt(inputArr[j]);
                    }
                }

                sb.append(Arrays.toString(answer).replace(" ", ""));
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
