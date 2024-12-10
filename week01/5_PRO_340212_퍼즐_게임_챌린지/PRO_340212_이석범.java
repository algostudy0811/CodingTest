package algorithm;
import java.io.*;
import java.util.*;

/*
���� Ž������ �ּҰ� ���ϸ� �ɵ�
����Ž�� ������ 1���� ������ �ִ�
diff[0] �� 1�� ���� 

������ ��
limit �� long���� ���ϴ� �͵� long���� �ؾ���
*/
class PRO_340212_�̼��� {
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
        //�ִ� ���ϱ�
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