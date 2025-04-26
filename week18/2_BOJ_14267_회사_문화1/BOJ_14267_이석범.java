import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
77192KB 516ms
 */
public class BOJ_14267_이석범 {

    //그래프 생성
    static class Graph {
        int N;
        Node[] nodeList;
        int[] dp;

        Graph(int n) {
            N = n;
            nodeList = new Node[N+1];
            for(int i=1;i<N+1;i++) nodeList[i] = new Node(i);
            dp = new int[N+1];
        }

        //엣지 추가 -> 상사와 직원 관계 고려 단방향
        void addEdge(int parent, int child) {

            Node childNode = nodeList[child];

            nodeList[parent].adjList.add(childNode);
        }


        //dp에 칭찬 지수 추가
        void addScore(int parent, int score) {
            dp[parent] += score;
        }

        //dfs돌면서 칭찬 지수 추가
        void DFS(int i) {
            for(Node n : nodeList[i].adjList) {
                dp[n.idx] += dp[i];
                DFS(n.idx);
            }
        }

    }

    static class Node {
        int idx;
        List<Node> adjList = new ArrayList<>();

        Node(int idx) {
            this.idx = idx;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(n);

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n;i++) {
            int idx = Integer.parseInt(st.nextToken());

            if(idx==-1) continue;

            graph.addEdge(idx, i+1);
        }

        for(int i=0; i<m;i++) {
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            graph.addScore(idx, score);
        }

        graph.DFS(1);

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=n;i++) {
            sb.append(graph.dp[i]).append(" ");
        }

        System.out.println(sb);

    }
}
