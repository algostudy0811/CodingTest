import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
실패 -> 메모리 초과

문제
1. N분 동작
2. 버튼을 누르면 5가지 동작을 한다 -> 문제 참조
3. 피자 오븐 첫 시간은 0분
4. 시간 감소 버튼으로 인해 0보다 작아질 경우 0분으로 설정
5. 설정해야 할 시간이 주어졌을 때, 그 시간을 만들기 위해 눌러야 하는 버튼의
    최소 횟수와 그 방법

제한 시간
0.25초 -> 시간 복잡도 고려

입력
1. T 개 테스트 케이스
2. 설정해야하는 시간 N

출력
각각 케이스마다 5개의 정수를 한 줄에 공백으로 출력
각 정수는 버튼을 누르는 횟수를 출력
최소 횟수가 여러가지 경우 사전 순으로 가장 앞서는 방법 출력

제한
T는 100이하
N은 천만 이하
 */
import java.util.*;
public class BOJ_19940_김아영 {
    public static int[][] dp;
    public static int[] cal = {60, 10, -10, 1, -1};
    public static int maxNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int[] arr = new int[T];
        maxNum = 0;
        for(int test_case = 0; test_case < T; test_case ++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            arr[test_case] = N;
            maxNum = Math.max(maxNum, N);
        }

        dp = new int[maxNum + 61][6];
        dp[1] = new int[]{1, 0, 0, 0, 1, 0};
        dp[10] = new int[]{1, 0, 1, 0, 0, 0};
        dp[60] = new int[]{1, 1, 0, 0, 0, 0};

        for(int current = 1; current < maxNum + 61; current ++){
            f(current);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++){
            for(int j = 1; j < 6; j++){
                sb.append(dp[arr[i]][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void f(int current){
        for(int i = 4; i >= 0; i--){
            int next = current + cal[i];

            if(next <= 0 || next >= maxNum + 61) continue;
            if(dp[next][0] != 0 && dp[next][0] < dp[current][0] + 1) continue;

            for(int j = 0; j < 6; j++) dp[next][j] = dp[current][j];
            dp[next][0] ++;
            dp[next][i + 1] ++;
            f(next);
        }
    }

}
