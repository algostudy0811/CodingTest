/*
메모리 : 58532
시간 : 576

문제
1. V개 마을, E개 일방 통행 도로
2. 마을을 1번부터 V번
3. 사이클을 이루는 도로의 길이의 합
4. 도로의 정보가 주어졌을 때, 도로의 길이의 합이 가장 작은 시이클

입력
1. V E V는 400 이하
2. a b c a에서 b로 출발 거리 c

출력
최소 사이클의 도로 길이의 합
운동 경로 찾는 것이 불가능한 경우 -1

아이디어
플로이드 워샬
 */
import java.util.*;
import java.io.*;
public class BOJ_1956_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] map = new int[V + 1][V + 1];
        for(int i = 0; i < V + 1; i ++){
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < E; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[start][end] = weight;
        }

        for(int mid = 1; mid < V + 1; mid ++){
            for(int start = 1; start < V + 1; start ++){
                for(int end = 1; end < V  + 1; end ++){
                    if(map[start][mid] == Integer.MAX_VALUE || map[mid][end] == Integer.MAX_VALUE) continue;
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int start = 1; start < V + 1; start ++){
            for(int end = 1; end < V + 1; end ++){
                if(start == end) continue;
                if(map[start][end] == Integer.MAX_VALUE || map[end][start] == Integer.MAX_VALUE) continue;
                answer = Math.min(answer, map[start][end] + map[end][start]);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
