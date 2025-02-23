package algorithm;
/*
1/1 -> 0, 0
3/3 -> 2, 2
그냥 백트래킹을 할 경우 2^40가지의 경우의 수
주어진 흔적의 개수는 1~3까지이므로
1/1 ~ 3/3까지 카운팅을 함
[3][3]
최대 40개 이므로 하나당 약 5라고 가정할 경우
5^9 -> 1,953,125으로 시간 단축 가능
*/
import java.io.*;
import java.util.*;
class PRO_389480_이석범 {
    
    private int[][] graph = new int[4][4];
    
    private int result = 121;
    private int A;
    
    private void back(int idx, int n, int m) {
        
        if(idx==9) {
            
            result = Math.min(result, A-n);
            
            return;
        }
        
        int r = idx / 3;
        int c = idx % 3;
        
        int cnt = graph[r][c];
        for(int i=0; i<=cnt;i++) {
            int a = i*(r+1);
            int b = (cnt - i)*(c+1);
            
            if(a >= n || b >= m) continue;
            
            // System.out.println(a+" "+b);
            
            back(idx+1, n-a, m-b);
            
        }
        
    }
    
    public int solution(int[][] info, int n, int m) {
        
        for(int i=0; i<info.length;i++) {
            int r = info[i][0] - 1;
            int c = info[i][1] - 1;
            graph[r][c] += 1;
        }
        A = n;
        back(0, n, m);
        
        int answer = result == 121 ? -1 : result;
        return answer;
    }
}