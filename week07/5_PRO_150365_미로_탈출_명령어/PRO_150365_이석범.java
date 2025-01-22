package algorithm;
/*
DFS로 도착지에 가장 먼저 도착한 경우 바로 끝냄
check할 것
1. 결과가 정해진 경우
2. 도착지와의 거리가 짝수인데 남은 거리가 홀수면 안됨
3. 도착지와의 거리보다 남은 거리가 작으면 안됨
*/
import java.util.*;
import java.io.*;
class PRO_150365_이석범 {
    
    private int[][] graph;
    private int R, C;
    private int endR, endC;
    private String result = "";
    
    // d > l > r > u
    private int[] dr = {1, 0, 0, -1};
    private int[] dc = {0, -1, 1, 0};
    
    private void DFS(int r, int c, int idx, int[] array, int k) {
        
        if(!check(r, c, idx, k)) return;
        
        if(idx==k-1) {
            //도착지 딱맞게
            if(r!=endR ||c !=endC) return;
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<k;i++) {
                switch(array[i]) {
                    case 0:
                        sb.append("d");
                        break;
                    case 1:
                        sb.append("l");
                        break;
                    case 2:
                        sb.append("r");
                        break;
                    case 3:
                        sb.append("u");
                        break;
                }
            }
            result = sb.toString();
            
            return;
        }
        
        for(int d=0; d<4;d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if(nr < 0 || nr >= R || nc < 0 || nc>=C) continue;
            
            array[idx+1] = d;
            DFS(nr, nc, idx+1, array, k);
        }
    }
    
    public boolean check(int r, int c, int idx, int k) {
        //결과가 정해진 경우
        if(!result.equals("")) {
            return false;
        }
        //현재 위치로부터 목적지까지 거리
        int length = Math.abs(r-endR) + Math.abs(c-endC);
        //남은 idx
        int remainedIdx = k - (idx+1);
        
        //짝수일때 홀수이고 홀수일때 짝수면 안됨
        if((length % 2) != (remainedIdx % 2)) return false;
        
        if(length > remainedIdx) return false;
        
        return true;
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        R = n;
        C = m;
        graph = new int[R][C];
        endR = r-1;
        endC = c-1;
        
        DFS(x-1, y-1, -1, new int[k], k);
        
        String answer = result;
        if(answer.equals("")) answer = "impossible";
        return answer;
    }
}