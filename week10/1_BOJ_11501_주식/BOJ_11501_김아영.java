/*
메모리 326704
시간 : 1176

문제
1. 날 별로 주가 예상하고 이 예상이 맞다
2. 매일 세 가지 행동 중 하나를 한다.
2.1 주식 하나를 산다
2.2 원하는 만큼 주식을 판다.
2.3 안한다
3. 최대 이익이 얼마나 되는지 계산

입력
1. 테스트 케이스 수
2. 날의 수 N 백만 이하
3. 날 별 주가 만

출력
최대 이익 정수 62bit 정수형으로 표한 가능

아이디어
long 타입으로 계산
dp

풀이과정
1. int T 입력
2. int N 입력
3. int[] price 입력
4. PQ[] priceQueue 입력
5. int max = priceQueue.poll();
6. int answer = 0;
 */
import java.util.*;
import java.io.*;
public class BOJ_11501_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 0; test_case < T; test_case ++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] price = new int[N];
            int[] maxPrice = new int[N];


            for(int i = 0; i < N; i++){
                price[i] = Integer.parseInt(st.nextToken());
            }

            maxPrice[N - 1] = price[N - 1];
            for(int i = N - 1; i >= 1;i --){
                maxPrice[i - 1] = Math.max(price[i - 1], maxPrice[i]);
            }
            long answer = 0;

            for(int i = 0; i < N; i++){
                answer += maxPrice[i] - price[i];
            }

            sb.append(answer);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
