package algorithm;
import java.io.*;
import java.util.*;

/*
이진 탐색으로 최소값 구하면 될듯
이진탐색 범위는 1부터 레벨의 최댓값
diff[0] 은 1로 고정 

조심할 것
limit 가 long으로 더하는 것도 long으로 해야함
*/
class PRO_340212_이석범 {
    static int[] dp, time;
    static int max;
    static int n;
    static int level = 0;
    
    private void binarySearch(long limit) {
        int start = 1;
        int end = max;
        
        while(start<=end) {
            int mid = (start + end) / 2;
            
            long cnt = check(mid);
            if(limit >= cnt) {
                level = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
    
    private long check(int level) {
        long cnt = 0;
        
        for(int i=0; i<n;i++) {
            if(dp[i] <= level) cnt += time[i];
            else {
                cnt += (time[i-1]+time[i]) * (dp[i] - level) + time[i];
            }
        }
        
        return cnt;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        n = diffs.length;
        time = times;
        
        if(n==1) {
            return 1;
        }
        
        dp = new int[n];
        //최댓값 구하기
        for(int i=0; i<n;i++) {
            dp[i] = diffs[i];
            max = Math.max(max, diffs[i]);
        }
        
        binarySearch(limit);
        
        // System.out.println(level);
        
        
        int answer = level;
        return answer;
    }
}