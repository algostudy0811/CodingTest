package STUDY.week18;

import java.io.*;
import java.util.*;

/* 79356KB 480ms
[문제 해석]
간선에 가중치와 방향성이 없는 임의의 루트 있는 트리가 주어졌을 때,
정점 U를 루트로 하는 서브트리에 속한 정점의 수 출력

[입력]
1. 정점의 수 N, 루트의 번호 R, 쿼리의 수 Q
    (2<=N<=10^5, 1<=R<=N, 1<=Q<=10^5)
2. U V 형태로 트리에 속한 간선의 정보 (1<=U, V<=N)
    U와 V를 양 끝점으로 하는 간선이 트리에 속함
3. U (1<=U<=N)

[출력]
쿼리의 답을 정수 하나로 출력

[문제 해결 프로세스]
1. dfs로 서브트리의 개수 count
 */
public class BOJ_15681_홍경현 {

    static int[] size;
    static boolean[] visit;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        size = new int[N+1];
        list = new List[N+1];
        visit = new boolean[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
            size[i] = 1;
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }

        //서브트리 count
        countTree(R);

        for(int i=0; i<Q; i++){
            int q = Integer.parseInt(br.readLine());
            sb.append(size[q]).append('\n');
        }

        System.out.println(sb);
    }

    static void countTree(int v){
        visit[v] = true;
        for(int n : list[v]){
            if(visit[n]) continue;
            countTree(n);
            size[v] += size[n];
        }

    }
}
