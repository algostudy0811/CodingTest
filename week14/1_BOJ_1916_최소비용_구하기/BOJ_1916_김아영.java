import java.sql.Array;
import java.util.*;
import java.io.*;
/*
메모리 : 56496
시간 : 552

문제
1. N개의 도시
2. M개의 버스
3. A에서 B번째 도시까지 가는데 드는 버스 비용 최소화

입력
1. 도시의 개수 N 1000이하
2. 버스의 개수 M 십만이하
3. 버스의 정보,
4. 출발점 도시, 도착점 도시

출력
최소 비용

아이디어
다익스트라
 */
public class BOJ_1916_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        List[] buses = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i ++){
            buses[i] = new ArrayList<int[]>();
        }

        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            buses[start].add(new int[]{end, weight});
        }

        int[] weights = new int[N + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.add(new int[]{start, 0});
        weights[start] = 0;

        while(!queue.isEmpty()){
            int[] node = queue.poll();

            if(node[1] > weights[node[0]]) continue; // 시간
            List<int[]> nodeBus = buses[node[0]];

            for(int[] next : nodeBus){
                int weight = node[1] + next[1];
                if(weight >= weights[next[0]]) continue; // 메모리

                weights[next[0]] = weight;
                queue.add(new int[]{next[0], weight});
            }
        }

        System.out.println(weights[end]);
    }
}
