package algorithm;
import java.util.*;
import java.io.*;
/*
차량 번호별로 정렬
map을 활용
40분
*/
import java.util.*;
import java.io.*;
class PRO_92341_이석범 {
    
    //정렬하기 위해 사용
    public class Node implements Comparable<Node> {
        String num;
        int cost;
        
        Node(String num, int cost) {
            this.num = num;
            this.cost = cost;
        }
        
        public int compareTo(Node o) {
            return this.num.compareTo(o.num);
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        
        //입력값 받기 위해 사용
        Map<String, Integer> map = new HashMap<>();
        
        //누적 값 저장하기 위해 사용
        Map<String, Integer> result = new HashMap<>();
        
        for(int i=0; i<records.length;i++) {
            String[] tmp = records[i].split(" ");
            // System.out.println(tmp[0]);
            //차량 번호
            String num = tmp[1];
            //시간
            String[] timeList = tmp[0].split(":");
            int time = Integer.parseInt(timeList[0]) * 60 + Integer.parseInt(timeList[1]);
            //System.out.println(time);
            
            if(tmp[2].equals("OUT")) {
                int timeCost = time - map.get(num);
                //있을 경우
                if(result.containsKey(num)) {
                    result.put(num, result.get(num)+timeCost);
                }
                else {
                    result.put(num, timeCost);
                }
                map.remove(num);
            }
            else {
                map.put(num, time);
            }
            
        }
        
        //안나간 경우 23시 59분으로 시간 추가
        Set<String> mapKey = map.keySet();
        for(String k: mapKey) {
            if(result.containsKey(k)) {
                int time = 23 * 60 + 59;
                int timeCost = time - map.get(k);
                result.put(k, result.get(k)+timeCost);
            }
            else {
                int time = 23 * 60 + 59;
                int timeCost = time - map.get(k);
                result.put(k, timeCost);
            }
        }
        
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        //요금 계산
        Set<String> key = result.keySet();
        for(String k: key) {

            int time = result.get(k);
            //기본 요금
            int cost = fees[1];
            
            System.out.println(time);
            
            if(time<=fees[0]) {
                pq.offer(new Node(k, cost));
            }
            else {
                //누적 요금 더하기
                cost += Math.ceil(1.0*(time - fees[0]) / fees[2]) * fees[3];
                pq.offer(new Node(k, cost));
            }
            
        }
        
        //정렬
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll().cost;
        }
        
        
        return answer;
    }
}