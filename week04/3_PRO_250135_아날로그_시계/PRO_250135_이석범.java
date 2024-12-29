package algorithm;
import java.util.*;
import java.io.*;
/*
1�ʿ� ���� 1/2��, �ô� 1/120���� �ö�
*/
class PRO_250135_�̼��� {
    
    double h, m, s;
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        int answer = 0;
        //���� �κ� ���� ���
        cal(h1, m1, s1);
        
        int start = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;
        
        //����+1 �κк��� end���� ���
        for(int t=start+1; t <= end;t++) {
            answer += check();
        }
        
        //���ۺκ� ��ġ���� Ȯ��
        if(cal(h1, m1, s1)) answer++;

        //���κ� ��ġ���� Ȯ��
        if(cal(h2, m2, s2)) answer++;
        return answer;
    }
    
    public boolean cal(int h,int m,int s) {
        //���� �κа� ���κ� ���
        this.h = (h % 12) * 30 + (m * (5.0 / 10)) + s * (1.0 / 120);
        this.m = (m * 6) + (s * (1.0 / 10));
        this.s = (s * 6);
        
        //��ġ�� �� ������ true
        if(this.h == this.s || this.m == this.s) return true;
        
        return false;
    }
    
    public int check() {
        int cnt = 0;
        //���� �ð��� ����ð��� ���ؼ� �߰��� ��ħ��
        //�����̾��ٰ� �������� �Ѿ ��� +1
        //��ħ�� ��ħ���� �����̴ٰ� ������ ���� �Ѿ
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