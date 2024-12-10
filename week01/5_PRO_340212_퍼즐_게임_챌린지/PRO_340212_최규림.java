import java.io.*;
import java.util.*;
/*
특정 level에 따라서 제한 시간 내의 가능한 케이스인지 검증 필요
하지만 level의 최대 경우의 수가 100,000 이기 때문에, +1만큼 순차적으로 확인하면 비효율 (O(log N))
이분 탐색 활용
시간 복잡도 : O(log N)
*/
class Solution {    
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int maxLevel = Arrays.stream(diffs).max().getAsInt();                
        return binarySearch(1, maxLevel, limit, diffs, times);
    }
    
    static int binarySearch(int left, int right, long limit, int[] diffs, int[] times) {
        int level = -1;
        while(left <= right) {
            int newLevel = (left + right) / 2;
            long totalTime = calculate(newLevel, diffs, times);
            if(totalTime <= limit) {
                level = newLevel;
                right = newLevel - 1;
            } else {
                left = newLevel + 1;
            }
        }
        return level;
    }
    
    static long calculate(int level, int[] diffs, int[] times) {
        long totalTime = times[0];
        for(int i=1; i<diffs.length; i++) {
            int diff = diffs[i];
            int timePrev = times[i-1];
            int timeCur = times[i];
            
            if(diff <= level) {
                totalTime += timeCur;
            } else {
                totalTime += (timeCur + timePrev) * (diff - level) + timeCur;
            }
        }
        
        return totalTime;
    }
}

