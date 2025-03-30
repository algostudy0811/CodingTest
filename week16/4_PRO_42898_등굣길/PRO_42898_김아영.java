/*
문제
1. M * N의 격자모향
2. 왼쪽 위는(1, 1) 오른쪽 아래는 (m, N)
3. 최단 경로의 개수를 1_000_000_007로 나눈 나머지 출력

아이디어 
bfs dp => 시간 초과
*/
import java.util.*;
class Solution {
    static int[] dy = {1, 0};
    static int[] dx = {0, 1};
    static long divide = 1_000_000_007;
    
    public int solution(int M, int N, int[][] puddles) {
        int answer = 0;
    
        long[][] dp = new long[N][M];
        
        for(int[] puddle : puddles){
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
        for(int i = 0 ;i < N; i ++){
            if(dp[i][0] == -1) break;
            dp[i][0] = 1;
        }
        
        for(int j = 0; j < M; j ++){
            if(dp[0][j] == -1) break;
            dp[0][j] = 1;
        }
        
        
        for(int i = 1; i < N; i ++){
            for(int j = 1; j < M; j ++){
                if(dp[i][j] == -1) continue;
                
                if(dp[i - 1][j] == -1 && dp[i][j - 1] == -1) continue;
                else if(dp[i - 1][j] == -1) dp[i][j] = dp[i][j - 1];
                else if(dp[i][j - 1] == -1) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                
                dp[i][j] = dp[i][j] % divide;
            }
        }
        

        return (int)((dp[N - 1][M - 1]) % divide);
    }
}
