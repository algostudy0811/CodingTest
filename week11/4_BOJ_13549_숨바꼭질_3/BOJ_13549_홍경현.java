package STUDY.week11;

import java.io.*;
import java.util.*;

/* 13,360KB 80ms
[문제 해석]
 숨바꼭질
 수빈이 현재 점 N(0<=N<=100,00)
 동생 점 K(0<=K<=100,000)

 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동
 순간이동을 하는 경우 0초 후에 2*X 위치로 이동
 수빈이가 동생을 찾을 수 있는 가장 빠른 시간

 [입력]
 1. N K

[출력]
가장 빠른 시간

[문제 해결 프로세스]
bfs
 */
public class BOJ_13549_홍경현 {

    static final int MAX_VALUE = 100001;
    static int N, K, res = MAX_VALUE;

    static class Node{
        int N, time;

        public Node(int N, int time){
            this.N = N;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N<K) bfs();
        System.out.println(res == MAX_VALUE ? N-K: res);
    }

    private static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(N, 0));
        boolean[] visit = new boolean[MAX_VALUE];
        visit[N] = true;

        while(!q.isEmpty()){
            Node n = q.poll();
            if(n.N == K) {
                res = n.time;
                return;
            }

            if(n.N*2<MAX_VALUE && !visit[n.N*2]) {
                visit[n.N*2] = true;
                q.add(new Node(n.N*2, n.time));
            }
            if(n.N-1>=0 && !visit[n.N-1]) {
                visit[n.N-1] = true;
                q.add(new Node(n.N-1, n.time+1));
            }
            if(n.N+1<MAX_VALUE && !visit[n.N+1]) {
                visit[n.N+1] = true;
                q.add(new Node(n.N+1, n.time+1));
            }
        }
    }
}
