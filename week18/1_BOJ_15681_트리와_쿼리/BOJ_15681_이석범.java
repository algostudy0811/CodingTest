import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
그래프에서 dp
77308KB 596ms
 */
public class BOJ_15681_이석범 {

    static int N;

    //그래프 생성
    static class Graph {
        int N;
        int R;
        Node[] nodeList;
        int[] dp;

        Graph(int N, int R) {
            this.N = N;
            this.R = R;

            nodeList = new Node[N+1];
            dp = new int[N+1];

            for(int i=1; i<N+1;i++) nodeList[i] = new Node(i);
        }

        //양방향이므로 두개 노드에 추가
        void addEdge(int a, int b) {
            Node aNode = nodeList[a];
            Node bNode = nodeList[b];

            aNode.adj.add(bNode);
            bNode.adj.add(aNode);
        }

        //서브트리로 부터 개수 파악
        void makeSubTree(int root) {
            dp[root] = 1;
            Node n = nodeList[root];

            for(Node adj:n.adj) {
                if(dp[adj.idx]==0) {
                    makeSubTree(adj.idx);
                    dp[root] += dp[adj.idx];
                }
            }
        }

        int getQuery(int n) {
            return dp[n];
        }

    }

    static class Node {
        int idx;
        List<Node> adj = new ArrayList<>();

        Node(int idx) {
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N, R);

        for(int i=0; i<N-1;i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.addEdge(a, b);
        }

        graph.makeSubTree(R);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            sb.append(graph.getQuery(start)).append("\n");
        }

        System.out.println(sb);

    }
}
