package algorithm;
/*
�׳� ��Ž�� ��� 3������ ���� ����
end�� �������� ���� -> �ѹ��� �ܼӿ� ī�޶� ��������
��ó�� ������ end�� ��������
1. end���� �۰ų� ���� ��� -> ���� ī�޶� ������ ����
2. end���� ū ��� -> ���� ī�޶� ������ ���� ���� �� �κ� ���� �ٽ� ī�޶� ����
-20     -15
     -18         -13
           -14            -5  
                          -5 -3
���� ��� -15�� �������� ���� ������ �ܼ�ī�޶� ������
�״����� ������ ���� -> �ٽ� ������ �������� ī�޶� ���� -5
*/
import java.util.*;
import java.io.*;

class PRO_42884_�̼��� {
    
    private class Car implements Comparable<Car> {
        int start;
        int end;
        
        Car(int[] car) {
            this.start = car[0];
            this.end = car[1];
        }
        
        //�켱����ť�� ����ϱ����� compareTo�޼��� �������̵�
        public int compareTo(Car c) {
        	//��������
        	//���������� ��� -1�� ����
            return Integer.compare(this.end, c.end);
        }
        
        public String toString() {
            return this.start+" "+this.end;
        }
        
    }
    
    public int solution(int[][] routes) {
        
        PriorityQueue<Car> pq = new PriorityQueue<>();
        
        for(int[] route: routes) {
            pq.offer(new Car(route));
        }
        
        int answer = 0;
        //idx�� �ּҰ��� -30000���� ���� �� ���
        int idx = -30_001;
        
        while(!pq.isEmpty()) {
            Car c = pq.poll();
            // System.out.println(c.toString());
            if(c.start<=idx) continue;
            idx = c.end;
            answer++;
        }
        
        return answer;
    }
}