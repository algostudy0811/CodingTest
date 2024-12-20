import java.io.*;
import java.util.*;

/**
- 재귀함수 사용
- 곡괭이 개수 최대 15개이고 광물 개수 최대 50개이기 떄문에 부담없이 재귀 함수 사용
**/
class Solution {
    
    // 최대 피로도 -> 돌 곡괭이로 다이아 광물을 50번 캐는 경우
    static int answer = 25 * 50 + 1;
    
    public int solution(int[] picks, String[] minerals) {        
        recursion(picks[0], picks[1], picks[2], 0, minerals, 0);
        return answer;
    }
    
    // 각 곡괭이 개수를 줄여가면서 연산
    static void recursion(int d, int i, int s, int idx, String[] minerals, int result) {                
         if(isFinish(d, i, s, idx, minerals)) {
             answer = Math.min(answer, result);
             return;
         }
                
        if(d != 0) {
            int temp = result;            
            for(int x=idx; x<idx+5; x++) {
                if(x == minerals.length) break;
                temp += useDia(minerals[x]);
            }
            recursion(d-1, i, s, idx+5, minerals, temp);
        }
        
        if(i != 0) {          
            int temp = result;
            for(int x=idx; x<idx+5; x++) {
                if(x == minerals.length) break;
                temp += useIron(minerals[x]);
            }                                
            recursion(d, i-1, s, idx+5, minerals, temp);
        }
        
        if(s != 0) {
            int temp = result;            
            for(int x=idx; x<idx+5; x++) {
                if(x == minerals.length) break;
                temp += useStone(minerals[x]);
            }
            recursion(d, i, s-1, idx+5, minerals, temp);
        }
    }
    
    static boolean isFinish(int d, int i, int s, int idx, String[] minerals) {
        return d + i + s == 0 || idx >= minerals.length;
    }
    
    // 다이아 곡괭이
    static int useDia(String mineral) {
        int result = 0;
        if(mineral.equals("diamond")) result += 1;
        else if(mineral.equals("iron")) result += 1;
        else result += 1;
        return result;        
    }
    
    // 철 곡괭이
    static int useIron(String mineral) {
        int result = 0;
        if(mineral.equals("diamond")) result += 5;
        else if(mineral.equals("iron")) result += 1;
        else result += 1;
        return result;        
    }
    
    // 돌 곡괭이
    static int useStone(String mineral) {
        int result = 0;
        if(mineral.equals("diamond")) result += 25;
        else if(mineral.equals("iron")) result += 5;
        else result += 1;
        return result;        
    }
}
