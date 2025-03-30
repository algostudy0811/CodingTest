
/*
메모리 : 98836
시간 : 320

문제
1. 모든 학생은 자신과 반 등수의 차이가 K를 넘으면 친구가 아니다
2. 좋은 친구는 이름의 길이가 같아야 된다
3. N명의 학생들의 이름이 성적순을 주어질 때 좋은 친구가 몇쌍이 있는지

입력
1. N K N은 30만 이하
2. 이름은 알파벳 대문자 20이하

출력
좋은 친구가 몇 쌍있는지

아이디어
이중 포문 다음부터 시간 => 시간 초과
dp

시간복잡도
nlogn

풀이과정
1. N K 입력
2. int anwer = 0;
3. for i in range(0, N);
3.1 for j in rnage(i + 1, i + k + 1)
 */
import java.util.*;
import java.io.*;
public class BOJ_3078_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] names = new int[N];
        long[][] dp = new long[21][N];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            dp[name.length()][i]++;
            names[i] = name.length();
        }

        for(int i = 1; i < 21; i ++){
            for(int j = 1; j < N; j ++){
                dp[i][j] += dp[i][j - 1];
            }
        }

        for(int i = 0; i < 21; i ++){
            System.out.println(Arrays.toString(dp[i]));
        }

        long answer = 0;
        for(int i = 0; i < N; i ++){
            if(i + K < N) answer += dp[names[i]][i + K] - dp[names[i]][i];
            else answer += dp[names[i]][N - 1] - dp[names[i]][i];
        }

        System.out.println(answer);
    }
}
