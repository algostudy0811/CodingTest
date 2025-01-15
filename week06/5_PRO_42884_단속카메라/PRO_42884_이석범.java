package algorithm;
/*
그냥 완탐할 경우 3억으로 되지 않음
end를 기준으로 정렬 -> 한번은 단속용 카메라를 만나야함
맨처음 차량의 end를 기준으로
1. end보다 작거나 같은 경우 -> 현재 카메라에 만나서 만족
2. end보다 큰 경우 -> 현재 카메라를 만나지 못함 따라서 이 부분 부터 다시 카메라를 세팅
-20     -15
     -18         -13
           -14            -5  
                          -5 -3
위의 경우 -15를 기준으로 다음 차량은 단속카메라에 만나고
그다음은 만나지 못함 -> 다시 차량을 기준으로 카메라를 만남 -5
*/
import java.util.*;
import java.io.*;

class PRO_42884_이석범 {
    
    private class Car implements Comparable<Car> {
        int start;
        int end;
        
        Car(int[] car) {
            this.start = car[0];
            this.end = car[1];
        }
        
        //우선순위큐를 사용하기위해 compareTo메서드 오버라이드
        public int compareTo(Car c) {
        	//오름차순
        	//내림차순의 경우 -1을 곱함
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
        //idx의 최소값인 -30000보다 작은 값 사용
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