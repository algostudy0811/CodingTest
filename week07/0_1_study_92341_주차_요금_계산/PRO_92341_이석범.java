package algorithm;
import java.util.*;
import java.io.*;
/*
���� ��ȣ���� ����
map�� Ȱ��
40��
*/
import java.util.*;
import java.io.*;
class PRO_92341_�̼��� {
    
    //�����ϱ� ���� ���
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
        
        //�Է°� �ޱ� ���� ���
        Map<String, Integer> map = new HashMap<>();
        
        //���� �� �����ϱ� ���� ���
        Map<String, Integer> result = new HashMap<>();
        
        for(int i=0; i<records.length;i++) {
            String[] tmp = records[i].split(" ");
            // System.out.println(tmp[0]);
            //���� ��ȣ
            String num = tmp[1];
            //�ð�
            String[] timeList = tmp[0].split(":");
            int time = Integer.parseInt(timeList[0]) * 60 + Integer.parseInt(timeList[1]);
            //System.out.println(time);
            
            if(tmp[2].equals("OUT")) {
                int timeCost = time - map.get(num);
                //���� ���
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
        
        //�ȳ��� ��� 23�� 59������ �ð� �߰�
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
        
        //��� ���
        Set<String> key = result.keySet();
        for(String k: key) {

            int time = result.get(k);
            //�⺻ ���
            int cost = fees[1];
            
            System.out.println(time);
            
            if(time<=fees[0]) {
                pq.offer(new Node(k, cost));
            }
            else {
                //���� ��� ���ϱ�
                cost += Math.ceil(1.0*(time - fees[0]) / fees[2]) * fees[3];
                pq.offer(new Node(k, cost));
            }
            
        }
        
        //����
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll().cost;
        }
        
        
        return answer;
    }
}