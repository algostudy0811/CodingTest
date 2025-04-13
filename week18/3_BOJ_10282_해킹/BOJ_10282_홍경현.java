package STUDY.week18;

import java.io.*;
import java.util.*;

/* 160628KB 764ms
[문제 해석]
어떤 컴퓨터 a가 다른 컴퓨터 b에 의존한다면,
b가 감염되면 그로부터 일정 시간 뒤 a도 감염

촣 몇대의 컴퓨터가 감염되고, 얼마나 시간이 걸리는지

[입력]
1. TC (최대 100개)
2. 컴퓨터 개수 n, 의존성 개수 d, 해킹당한 컴퓨터 번호 c
    (1<=n<=100,000 , 1<=d<=100,000 , 1<=c<=n)
3. d개의 줄에 의존성을 나타내는 a b s

[출력]
1. 총 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간

[문제 해결 프로세스]
pq로 시간이 낮은 순대로 감염된 컴퓨터 cnt
 */
public class BOJ_10282_홍경현 {

    static List<Node>[] list;
    static int cnt, res;

    static class Node implements Comparable<Node>{
        int a, s;

        public Node(int a, int s){
            this.a = a;
            this.s = s;
        }

        @Override
        public int compareTo(Node o){
            return this.s - o.s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list = new List[N+1];
            for(int i=1; i<=N; i++) list[i] = new ArrayList<>();

            for(int i=0; i<D; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                list[b].add(new Node(a, s));
            }

            cnt = 0; res = 0;
            findHacking(C, N);

            sb.append(cnt).append(" ").append(res).append("\n");
        }

        System.out.println(sb);
    }

    static void findHacking(int c, int N){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N+1];
        pq.add(new Node(c, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(check[node.a]) continue;
            check[node.a] = true;
            res = node.s;
            cnt++;

            for(Node next : list[node.a]){
                pq.add(new Node(next.a, next.s + node.s));
            }
        }
    }
}
