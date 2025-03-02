package algorithm;
import java.io.*;
import java.util.*;

/*
1~N까지의 숫자 중 5개를 선택
선택한 5개를 set으로 만들고 ans와 정확하게 일치하는 것들만 cnt+1
*/
class PRO_388352_이석범 {
    
    private int[] selected = new int[5];
    private int result = 0;
    
    private void comb(int n, int idx, int next) {
        
        if(idx==5) {
            // System.out.println(Arrays.toString(selected));
            boolean flag = check();
            
            if(flag) result++;
            
            return;
        }
        
        for(int i=next; i<=n ;i++) {
            selected[idx] = i;
            
            comb(n, idx+1, i+1);
            
        } 
    }
    
    private boolean check() {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<5;i++) {
            set.add(selected[i]);
        }
        
        for(int q=0; q<question.length;q++) {
            int cnt = 0;
            for(int i=0; i<5;i++) {
                int num = question[q][i];
                if(set.contains(num)) cnt++;
            }
            
            if(cnt!=answer[q]) return false;
        }
        
        return true;
    }
    
    private int[][] question;
    private int[] answer;
    
    public int solution(int n, int[][] q, int[] ans) {
        
        answer = ans;
        question = q;
        
        comb(n, 0, 1);
        
        int answer = result;
        return answer;
    }
}