package algorithm;
import java.util.*;
import java.io.*;

/*
백트래킹으로 n번돌면서 0~10점 중 하나를 고름
고른 후에 점수 비교
*/
class PRO_92342_이석범 {
    
    static final int LENGTH = 11;
    
    private int maxScore = Integer.MIN_VALUE;
    private int[] maxScoreList = new int[LENGTH];
    
    //백트레킹으로 점수 구하기
    private void back(int n, int idx, int[] list, int[] info) {
        
        if(n==idx) {
            check(list, info);
            return;
        }
        
        for(int i=0; i<LENGTH;i++) {
            //이미 점수를 +1점 높을 경우 더할 필요 없으므로 break
            if(list[i]>info[i]) break;
            
            list[i] += 1;
            back(n, idx+1, list, info);
            list[i] -= 1;
        }
    }
    
    private void check(int[] RList, int[] AList) {
        int score = 0;
        for(int i=0; i<LENGTH;i++) {
            //밑에 주석은 안되고 이거는 됨
            if(AList[i]==0 && RList[i]==0) continue;
            else if(RList[i] > AList[i]) score += LENGTH - 1 - i;
            //if(RList[i]==0 && AList[i]==0) continue;
            else score -= LENGTH - 1 - i;
        }
        
        if(score <= 0) return;
        
        //스코어가 크거나 같은경우 배열 복사
        //같아도 할 수 있는 이유는 0번이 10번이어서 높은것에서 낮은 것으로 갈수 밖에 없음
        if(score >= maxScore) {
            maxScore = score;
            System.arraycopy(RList, 0, maxScoreList, 0, LENGTH);
        }
    }
    
    public int[] solution(int n, int[] info) {
        back(n, 0, new int[LENGTH], info);
        
        int[] answer;
        if(maxScore == Integer.MIN_VALUE) {
            
            answer = new int[1];
            answer[0] = -1;
            
            return answer;
        }
        

        return maxScoreList;
    }
}