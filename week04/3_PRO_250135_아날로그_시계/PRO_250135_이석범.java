package algorithm;
import java.util.*;
import java.io.*;
/*
1초에 분은 1/2도, 시는 1/120도씩 올라감
*/
class PRO_250135_이석범 {
    
    double h, m, s;
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        int answer = 0;
        //시작 부분 각도 계산
        cal(h1, m1, s1);
        
        int start = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;
        
        //시작+1 부분부터 end까지 계산
        for(int t=start+1; t <= end;t++) {
            answer += check();
        }
        
        //시작부분 겹치는지 확인
        if(cal(h1, m1, s1)) answer++;

        //끝부분 겹치는지 확인
        if(cal(h2, m2, s2)) answer++;
        return answer;
    }
    
    public boolean cal(int h,int m,int s) {
        //시작 부분과 끝부분 계산
        this.h = (h % 12) * 30 + (m * (5.0 / 10)) + s * (1.0 / 120);
        this.m = (m * 6) + (s * (1.0 / 10));
        this.s = (s * 6);
        
        //겹치는 곳 있으면 true
        if(this.h == this.s || this.m == this.s) return true;
        
        return false;
    }
    
    public int check() {
        int cnt = 0;
        //이전 시간과 현재시간을 비교해서 중간에 초침이
        //이전이었다가 다음으로 넘어갈 경우 +1
        //분침이 시침보다 이전이다가 다음일 경우는 넘어감
        double preH = this.h;
        double preM = this.m;
        double preS = this.s;
        
        this.h += 1.0 / 120;
        this.m += 1.0 / 10;
        this.s += 6;
        
        if(preH > preS && this.h <= this.s) {
            if(!(preH > preM && this.h <=this.m)) cnt++;
        }
        if(preM > preS && this.m <= this.s) cnt++;
        
        if(this.h >= 360) this.h %= 360;
        if(this.m >= 360) this.m %= 360;
        if(this.s >= 360) this.s %= 360;
        
        return cnt;
    }
}