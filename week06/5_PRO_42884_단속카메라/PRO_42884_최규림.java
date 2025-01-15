import java.io.*;
import java.util.*;


/**
1. 최소의 카메라 설치를 위해서는 전출 지점의 경계지점을 선택해야 함
2. 가장 빨리 전출되는 지점이 기준점(첫번째 카메라 설치)
3. 전출 지점을 기준으로 우선순위 큐에 삽입 후, 구간을 pop 하면서 순차적으로 확인
3-1. 해당 구간이 위의 기준점에 포함되어 있다면 PASS -> 다음 구간도 포함되어 있는지 반복
3-2. 헤딩 구간이 위의 기준점에 포함되어 있지 않다면, 해당 카메라는 이전 구간까지 단속 가능 -> 해당 구간의 전출지점을 새로운 기준점으로 설정(카메라 설치)

etc. 백준의 회의실 배정? 문제와 유사한 느낌
**/
class Solution {
    
    static class Point implements Comparable<Point>{
        int start, end;
        Point(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Point p) {
            return this.end - p.end;
        }
    }
    
    static PriorityQueue<Point> pq = new PriorityQueue<Point>();
    
    public int solution(int[][] routes) {        
        for(int[] route:routes) {
            pq.add(new Point(route[0], route[1]));
        }
        
        int answer = 0;
        while(!pq.isEmpty()) {
            Point now = pq.poll();
            
            while(!pq.isEmpty()) {
                Point next = pq.peek();
                if(next.start <= now.end && now.end <= next.end) {
                    pq.poll();
                } else {
                    break;
                }
            }
            answer += 1;
        }                
        
        return answer;
    }
}
