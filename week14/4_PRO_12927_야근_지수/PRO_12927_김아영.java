/*
문제
1. N 시간 동안 야근 피로도를 최소화하도록 일함
2. 1시간 동안 작업량 1을 처리
3. 야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱해서 더한 값

아이디어
숫자가 큰 것부터 줄여야함
*/
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i = 0 ; i < works.length; i ++){
            queue.add(works[i]);
        }
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            
            if(node != 1) queue.add(node - 1);
            
             n --;
            if(n == 0) break;
        }
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            
            answer += node * node;
        }
        
        return answer;
    }
}
