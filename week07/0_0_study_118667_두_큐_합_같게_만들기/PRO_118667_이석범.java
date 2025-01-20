package algorithm;
/*
����� ��
2����
1�� ť���� ����
2�� ť���� ����

�ϴ� �� ���� ����
�����⸦ ��
35��

*/
import java.util.*;
import java.io.*;
class PRO_118667_�̼��� {
    public int solution(int[] queue1, int[] queue2) {
        
        long sum = 0;
        long sum1 = 0;
        long sum2 = 0;
        
        int length = queue1.length;
        
        Queue<Integer> list1 = new ArrayDeque<>();
        Queue<Integer> list2 = new ArrayDeque<>();
        for(int i=0; i<length;i++) {
            sum1 += queue1[i];
            list1.offer(queue1[i]);
        }
        
        for(int i=0; i<length;i++) {
            sum2 += queue2[i];
            list2.offer(queue2[i]);
        }
        
        sum = (sum1 + sum2) / 2;
        int cnt = 0;
        while(sum1 != sum && sum2 != sum && (cnt < length*4)) {
            
            if(sum1 > sum2) {
                int add = list1.poll();
                list2.offer(add);
                sum1 -= add;
                sum2 += add;
            }
            else {
                int add = list2.poll();
                list1.offer(add);
                sum1 += add;
                sum2 -= add;
            }
            cnt++;
        }
        
        System.out.println(cnt);
        
        int answer = cnt;
        //�ִ� 4������ 
        if(cnt == queue1.length*4) {
            if(sum1!=sum2) {
                answer = -1;
            }
        }
        return answer;
    }
}