package algorithm;
/*
부분집합
2^26 -> 약 7천만
코스별로 가장 많은 사람이 선택한 것들을 리스트에 넣음
*/
import java.io.*;
import java.util.*;
class PRO_72411_이석범 {
    
    private final int alphabet = 26;
    
    private boolean[][] orderList;
    
    private class Node implements Comparable<Node> {
        String str;
        int cnt;
        
        Node(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
        //선택한 사람 순으로 내림차순
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt) * -1;
        }
    }
    
    //가장 많은 사람이 선택한 배열을 찾기 위해 사용
    private PriorityQueue<Node> pq = new PriorityQueue<>();
    
    //부분집합에 사용
    private boolean[] selected = new boolean[alphabet]; 
    
    //부분집합
    private void back(int idx, int cnt, int max) {
        //알파벳 z까지 가거나 현재 선택한 개수가 코스의 수랑 같으면 멈춤
        if(idx==alphabet || cnt==max) {
            //알파벳을 코스의 수만큼 세지 않으면 바로 반환
            if(idx==alphabet) {
                if(cnt < max) return;
            }
            //사람들이 몇명 시켰는지 확인
            int flag = check();
            //2명이상일 경우 pq에 넣기
            if(flag >=2) {
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<alphabet;i++) {
                    if(selected[i]) {
                        String tmp = Character.toString('A'+i);
                        sb.append(tmp);
                    }
                }
                pq.offer(new Node(sb.toString(), flag));
            }
            return;
        }
        //거꾸로 할 경우 안됨
        selected[idx] = true;
        back(idx+1, cnt+1, max);

        selected[idx] = false;
        back(idx+1, cnt, max);
    }
    
    private int check() {
        int len = orderList.length;
        int cnt = 0;
        //모든 메뉴를 선택한 경우만 cnt+1
        A: for(int i=0; i<len;i++) {
            for(int j=0; j<alphabet;j++) {
                if(selected[j]) {
                    if(!orderList[i][j]) continue A;
                } 
            }
            cnt++;
        }
        return cnt;
    }
    
    public String[] solution(String[] orders, int[] course) {
        
        orderList = new boolean[orders.length][alphabet];
        
        for(int i=0; i<orders.length;i++) {
            String order = orders[i];
            for(int j=0; j<order.length();j++) {
                int idx = order.charAt(j) - 'A';
                orderList[i][idx] = true;
            }
        }
        
        List<String> list = new ArrayList<>();
        
        for(int i:course) {
            back(0, 0, i);
            int cnt = 0;
            //가장 많이 선택한 것만 체크
            while(!pq.isEmpty()) {
                Node n = pq.poll();
                if(n.cnt >=cnt) {
                    list.add(n.str);
                    cnt = n.cnt;
                }
            }
        }
        
        //정렬
        list.sort(String::compareTo);
        
        String[] answer = new String[list.size()];
        int idx = 0;
        for(String str: list) {
            answer[idx++] = str;
        }

        return answer;
    }
}