package algorithm;
import java.io.*;
import java.util.*;
/*
광물들의 개수가 50개 이고 곡갱이 하나당 5개씩 캘 수 있음
곡갱이 10개가 필요하다는 의미
즉 곡갱이 리스트에서 최대 10개를 선택하면 되는데 개수를 보고 하면 되므로
백트래킹으로 계산 가능
그 후 광물을 캘 수 있는 곡갱이만 가지고 광물을 캘 수 있을때까지만 계산
*/
class PRO_172927_이석범 {
    
    //곡갱이 최대 개수
    static int maxNum;
    
    //곡갱이 선택한 리스트
    //광물을 숫자로 변한한 리스트
    static int[] selected, mineralList;
    
    //주어진 피로도 리스트
    static int[][] scores = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    //곡갱이 선택
    public void back(int[] picks, int idx) {
        
        if(idx==maxNum) {
            check();
            return;
        }
        
        for(int i=0; i<3;i++) {
            //곡갱이가 0개가 아니면 선택
            if(picks[i]==0) continue;
            
            selected[idx] = i;
            picks[i]--;
            back(picks, idx+1);
            picks[i]++;
        }
    }
    
    static int res = Integer.MAX_VALUE;
    
    //피로도 계산 메서드
    public void check() {
        // System.out.println(Arrays.toString(selected));
        
        //마지막인지 확인
        boolean flag = false;
        int cnt = 0;
        
        for(int i=0; i<selected.length;i++) {
            //5개를 선택하는데 5개가 아닌경우 마지막까지만 구함
            int start = i * 5;
            int end = (i+1) * 5;
            if(end >= mineralList.length) {
                flag = true;
                end = mineralList.length;
            }
            // System.out.println(end);
            //표를 토대로 계산
            for(int s=start;s<end;s++) {
                cnt += scores[selected[i]][mineralList[s]];
            }
            
            if(flag) break;
        }
        
        //최솟값 구하기
        res = Math.min(res, cnt);
        
    }
    
    public int solution(int[] picks, String[] minerals) {
        
        maxNum = minerals.length / 5;
        
        if(minerals.length % 5 != 0) {
            maxNum++;
        }
        
        int pickNum = 0;
        for(int i=0; i<3;i++) {
            pickNum += picks[i];
        }
        
        maxNum = Math.min(maxNum, pickNum);
        
        selected = new int[maxNum];
        
        mineralList = new int[minerals.length];
        for(int i=0; i<minerals.length;i++) {
            
            //피로도 표 구하기
            switch(minerals[i]) {
                case "diamond":
                    mineralList[i] = 0;
                    break;
                case "iron":
                    mineralList[i] = 1;
                    break;
                case "stone":
                    mineralList[i] = 2;
                    break;
            }
        }
        
        back(picks, 0);
        
        System.out.println(res);
        
        
        int answer = res;
        return answer;
    }
}