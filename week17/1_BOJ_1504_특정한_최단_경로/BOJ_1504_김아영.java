import java.io.*;
import java.util.*;
/*
메모리 : 75676
시간 : 920

문제
1. 무방향 그래프
2. 1번부터 N번 정점으로 최단 거리 이동
3. 다음 2가지 조건 만족
3.1 임의로 주어진 두 정점을 반드시 통과
3.2 정점과 간선은 중복 가능 단 최단 경로여야 함

입력
1. 정점의 개수 N 간선의 개수 E
2. a b c => 양방향
3. 반드시 거쳐야하는 두개의 서로 다른 정점 번호 v1, v2

출력
최단경로
없으면 -1

아이디어
bfs에 dp
 */

public class BOJ_1504_김아영{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<int[]>[] edges = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i ++){
            edges[i] = new ArrayList<int[]>();
        }

        for(int i = 0; i < E; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new int[]{b, c});
            edges[b].add(new int[]{a, c});
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long[][] visited = new long[4][N + 1];
        for(int i = 0; i < 4; i ++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        Queue<Node> queue = new ArrayDeque<>();

        if(v1 == 1){
            queue.add(new Node(1, 0, 1));
            visited[2][0] = 0;
        }
        else {
            queue.add(new Node(1, 0, 0));
        }
        visited[1][0] = 0;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int[] arr : edges[node.idx]){
                int next = convert(node.num, arr[0], v1, v2);

                if(visited[next][arr[0]] <= node.weight + arr[1]) continue;

                visited[next][arr[0]] = node.weight + arr[1];
                queue.add(new Node(arr[0], visited[next][arr[0]], next));
            }
        }

        int idx = 3;
        if(v1 != 1 && v2 == N) idx = 2;

        if(visited[idx][N] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(visited[idx][N]);
    }

    private static int convert(int num, int v, int v1, int v2){
        switch (num){
            case 0:
                if(v == v1) return 1;
                if(v == v2) return 2;
                break;
            case 1:
                if(v == v2) return 3;
                break;
            case 2:
                if(v == v1) return 3;
                break;
        }
        return num;
    }

    private static class Node{
        int idx;
        long weight;
        int num; // 1 : v1, 2: v2

        public Node(int idx, long weight, int num){
            this.idx = idx;
            this.weight = weight;
            this.num = num;
        }
    }
}
