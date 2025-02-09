package STUDY.week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 41724kb 380ms
[문제 해석]
1개의 회의실 N개의 회의 사용
회의 I에 대한 시작 시간, 끝나는 시간
각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수
한 회의가 끝나는 것과 동시에 다음 회의 시작 가능
회의 시작 시간과 끝나는 시간이 같을 수 있음

[입력]
1. 1<=N<=100,000
2. 시작시간 끝나는 시간
    2^32-1보다 작거나 같은 자연수 또는 0

[출력]
최대 회의 개수

[문제 해결 프로세스]
시작 시간 s, 끝나는 시간 e
1. 끝나는 시간 기준으로 정렬
2. 현재 회의 기준으로 e<s인 회의 탐색 (그리디)
3. 반복해서 탐색

[시간 복잡도]
O(N)
 */
public class BOJ_1931_홍경현 {

    static class Node implements Comparable<Node> {
        int s, e;

        public Node(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            if (this.e == o.e) return this.s - o.s;
            return this.e - o.e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Node(s, e));
        }

        int res = 1;
        Node now = pq.poll();
        while(!pq.isEmpty()){
            Node next = pq.poll();
            if(now.e > next.s) continue;
            now = next;
            res++;
        }

        System.out.println(res);
    }
}
