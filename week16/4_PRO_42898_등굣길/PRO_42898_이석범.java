package algorithm;
/*
dp 사용 n, m 좌표 확인
*/
class PRO_42898_이석범 {
    
    final int DIV = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] graph = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        
        int len = puddles.length;
        
        for(int i=0; i<len;i++) {
            int r = puddles[i][1];
            int c = puddles[i][0];
            
            graph[r][c] = 1;
        }
        
        dp[1][1] = 1;
        
        for(int r=1; r<n+1;r++) {
            for(int c=1; c<m+1;c++) {
                
                if(graph[r][c]==1 || (r==1 && c==1)) continue;
                
                dp[r][c] = (dp[r-1][c] + dp[r][c-1]) % DIV;
            }
        }
        
        answer = dp[n][m];
        // print(dp);
        
        return answer;
    }
    
    private void print(int[][] graph) {
        for(int[] r: graph) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}