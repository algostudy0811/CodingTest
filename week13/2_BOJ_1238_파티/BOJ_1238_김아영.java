/*
메모리 : 21620
시간 : 296

문제
1. N개의 마을
2. 마을 사이에는 M개의 단방향 도로
3. 도로를 지나는대 Ti 시간 소비
4. 최단 시간으로 이동할 때 가장 많은 시간을 소비하는 학생을 구하시오
5. 학생들은 X번 마을에 모여서 파티

입력
1. N 1000이하, M 1000이하, X
2. 도로 시작점 끝점 소요시간

출력
가장 오래 걸리는 학생의 소요 시간 출력

아이디어
최단 거리 구하기 => 다익스트라
bfs
 */
import java.util.*;
import java.io.*;
public class BOJ_1238_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());


        List<List<int[]>> edgesFirst = new ArrayList<>();
        List<List<int[]>> edgesSecond = new ArrayList<>();
        for(int i = 0; i < N + 1; i++){
            edgesFirst.add(new ArrayList<>());
            edgesSecond.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgesFirst.get(start).add(new int[]{end, weight});
            edgesSecond.get(end).add(new int[]{start, weight});
        }

        // X로
        int[] moveX = new int[N + 1];
        Arrays.fill(moveX, Integer.MAX_VALUE);
        moveX[X] = 0;
        bfs(moveX, X, edgesFirst);


        // 집으로
        int[] moveHome = new int[N + 1];
        Arrays.fill(moveHome, Integer.MAX_VALUE);
        moveHome[X] = 0;
        bfs(moveHome, X, edgesSecond);


        int answer = 0;
        for(int i = 1; i < N + 1; i ++){
            answer = Math.max(answer, moveX[i] + moveHome[i]);
        }
        System.out.println(answer);
    }

    public static void bfs(int[] move, int X, List<List<int[]>> edges){
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));

        queue.add(new int[]{X, 0});
        while(!queue.isEmpty()){
            int[] node = queue.poll();

            List<int[]> list = edges.get(node[0]);
            for(int[] next : list){
                int num = node[1] + next[1];
                if(move[next[0]] < num) continue;
                move[next[0]] = num;
                queue.add(new int[]{next[0], num});
            }
        }
    }
}
