import java.io.*;
import java.util.*;

/*
메모리 : 16776
시간 : 112

문제
1. N * M 모둔종이
2. 치즈가 공기에 노출되었을 떄 녹음
3. 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간
 */

public class BOJ_2638_김아영 {
    static boolean[][][] visited;
    static int N, M;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] map, countMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Queue<int[]> startQueue = new ArrayDeque<>();

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j ++){
                map[i][j] =  Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[N][M][4];
        countMap = new int[N][M];

        for(int i = 0; i < N; i ++){
            countMap[i][0] = 4;
            countMap[i][M - 1] = 4;
            queue.addAll(findCheese(i, 0));
            queue.addAll(findCheese(i, M - 1));
        }

        for(int i = 0; i < M; i ++){
            countMap[0][i] = 4;
            countMap[N - 1][i] = 4;
            queue.addAll(findCheese(0, i));
            queue.addAll(findCheese(N - 1, i));
        }

        int size = queue.size();
        int answer = 1;

        while(!queue.isEmpty()){
            if(size == 0){
                size = queue.size();
                answer++;
            }

            int[] node = queue.poll();
            size --;

            for(int i = 0; i < 4; i ++){
                int nr = node[0] + dy[i];
                int nc = node[1] + dx[i];

                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= M) continue;
                if(visited[nr][nc][i]) continue;

                visited[nr][nc][i] = true;
                countMap[nr][nc] ++;

                if(map[nr][nc] == 0) queue.addAll(findCheese(nr, nc));
                else if(countMap[nr][nc] == 2){
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        System.out.println(answer);
    }

    public static List<int[]> findCheese(int row, int col){
        List<int[]> result = new ArrayList<>();

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});

        while(!queue.isEmpty()){
            int[] node = queue.poll();

            for(int i = 0; i < 4; i ++){
                int nr = node[0] + dy[i];
                int nc = node[1] + dx[i];

                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= M) continue;
                if(visited[nr][nc][i]) continue;
                visited[nr][nc][i] = true;
                countMap[nr][nc] ++;

                if(map[nr][nc] == 0) queue.add(new int[]{nr, nc});
                else if(countMap[nr][nc] == 2) {
                    result.add(new int[]{nr, nc});
                }
            }
        }

        return result;
    }
}
