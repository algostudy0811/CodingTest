/*
메모리 : 18360
시간 : 188

문제
1. 도시, 비용, 고객 정보
2. 고객을 적어도 C명 늘이기 위해 투자해야하는 돈의 최소값을 구하라
3. 돈은 비용의 정수배 만큼 투자할 수 있다.

입력
1. C N : C는 1000 이하 자연수, N은 20 이하 자연수
2. 도시 홍보 비용, 비용으로 얻을 수 있는 고객 수

아이디어
dp

풀이과정
해당 인구를 가지는 최소값 저장
 */
import java.util.*;
import java.io.*;
public class BOJ_1106_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] prices;

        int[][] inputArr = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            inputArr[i][0] = Integer.parseInt(st.nextToken());
            inputArr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputArr, (o1, o2)  -> (o2[1] - o1[1])); // 내림차순 정렬
        prices = new int[C + inputArr[0][1]];

        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[0] = 0;

        int answer = Integer.MAX_VALUE;
        for(int i = 1; i < prices.length; i++){
            for(int[] arr : inputArr){
                int idx = i - arr[1];
                if(idx < 0) continue;
                if(prices[idx] == Integer.MAX_VALUE) continue;

                prices[i] = Math.min(prices[i], prices[idx] + arr[0]);
            }

            if(i >= C) answer = Math.min(answer, prices[i]);
        }

        System.out.println(answer);
    }

}
