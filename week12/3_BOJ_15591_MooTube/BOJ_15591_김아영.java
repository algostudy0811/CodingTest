/*
메모리 308244
시간 1440

문제
1. 소들을 1부터 N까지 번호
2. 두 동영상 사이에 연관 동영상 리스트 생성
3. 연관 리스트는 두 동영상 사이의 모든 연결들의 최솟값
4. 특정 값 K 이상인 동영상을 모두 추천
5. 연결은 N - 1개의 동영상 쌍으로 나타남

입력
1. N과 Q

제한사항
N은 5000이하
Q은 5000이하

출력
Q개의 줄 출력



 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BOJ_15591_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new Node(q, r));
            graph[q].add(new Node(p, r));
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int count = 0;

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];

            visited[v] = true;
            queue.add(v);

            while(!queue.isEmpty()){
                int now = queue.poll();

                List<Node> list = graph[now];

                for(int j = 0; j < list.size() ; j ++){
                    if(!visited[list.get(j).idx] && list.get(j).val >= k){
                        count ++;
                        queue.add(list.get(j).idx);
                        visited[list.get(j).idx] = true;
                    }
                }
            }

            sb.append(count);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static class Node{
        int idx, val;

        public Node(int idx, int val){
            this.idx = idx;
            this.val = val;
        }
    }
}
