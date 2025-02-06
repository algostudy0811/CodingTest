package algorithm;
import java.io.*;
import java.util.*;
class PRO_64065_ÀÌ¼®¹ü {
    
    private static final int NUM = 100_001;
    
    private class Node implements Comparable<Node> {
        int num;
        int degree;
        
        Node(int num, int degree) {
            this.num = num;
            this.degree = degree;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.degree, o.degree) * -1;
        }
    }
    
    public int[] solution(String s) {
        // for(int i=0; i<s.length();i++) {
        //     System.out.println(s.charAt(i));
        // }
        int num = 0;
        int cnt = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=1; i<s.length()-1;i++) {
            if(s.charAt(i) == '}' && num != 0) {
                if(map.containsKey(num)) {
                    map.put(num, map.get(num)+1);
                }
                else map.put(num, 1);
                num = 0;
            }
            else if(s.charAt(i) == ',' && num != 0) {
                if(map.containsKey(num)) {
                    map.put(num, map.get(num)+1);
                }
                else map.put(num, 1);
                num = 0;
            }
            else if(s.charAt(i) >= '0' && s.charAt(i) <='9') {
                num = num*10 + s.charAt(i) - '0';
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Integer> key = map.keySet();
        for(int i: key) {
            System.out.println(i+" "+map.get(i));
            pq.offer(new Node(i, map.get(i)));
        }
        
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            Node n = pq.poll();
            answer[idx++] = n.num;
        }
        return answer;
    }
}