/*
시간 : 108
메모리 : 12708

문제
1. n가지 종류 동전, 중복 사용 가능
2. 동전을 적당히 사용하여 합을 k원
3. 동전의 개수 최소

입력
1. N, K 각각 100, 10000이하
2. 동전의 가치, 가치가 같은 동전이 여러개 있을 숭 ㅣㅆ다

출력
동전의 최소 개수 출력
불가능할 경우 -1

아이디어
dp
 */
import java.util.*;
import java.io.*;
public class BOJ_2294_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[K + 1];

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < K + 1; j ++){

                if(j - coins[i] < 0) continue;
                else if(j - coins[i] == 0) dp[j] = 1;
                else if(dp[j - coins[i]] != 0) {
                    if(dp[j] == 0) dp[j] = dp[j - coins[i]] + 1;
                    else dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }

            }
        }

        if(dp[K] == 0) System.out.println(-1);
        else System.out.println(dp[K]);
    }
}
