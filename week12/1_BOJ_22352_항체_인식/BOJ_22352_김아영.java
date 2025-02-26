/*
메모리 15396
시간 104

문제
1. 백신을 놓는다
2. 백신을 놓으면 항체가 상하좌우로 퍼져나간다.
3. 항체가 더 이상 퍼져나갈 수 없을 때, 모든 칸은 동일한 값이된다.
4. 두 장의 촬영 결과가 주어질 때, 백신일 가능성이 있는지 판단

입력
1. N과 M 각각 30이 이하
2. 백신 놓기 전 촬영 결과
3. 배신 놓은 후 촬영 결과

아이디어
bfs

풀이과정
1. int N, M 입력
2. int[][] before 입력
3. int[][] after 입력
4. for i in range(0, N)
4.1 for j in range(0, N)
4.1.1 boolean[][] visited = new boolean[][]
4.1.2 queue.add(new int[]{i, j});
4.1.3 while(!queue.isEmthy)
4.1.4 int[] node = queue.poll()
4.1.5 for k in range(0, 4)
4.1.5.1 int nr, nc
4.1.5.2 범위 확인
4.1.5.3 if(visited)
4.1.5.4 if(after[nr][nc] != before[i][j])

시간 복잡도
O(N * N * N * N) 900 * 900 => 810000
 */
import java.util.*;
import java.io.*;
public class BOJ_22352_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] before = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] after = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        int row = 0;
        int col = 0;
        first : for(; row < N; row ++){
            col = 0;
            for(; col < M; col ++){
                if(visited[row][col]) continue;

                boolean[][] visited2 = new boolean[N][M];
                visited[row][col] = true;
                visited2[row][col] = true;

                queue.add(new int[]{row, col});
                while(!queue.isEmpty()){
                    int[] node = queue.poll();

                    for(int i = 0; i < 4; i++){
                        int nr = node[0] + dy[i];
                        int nc = node[1] + dx[i];

                        if(nr < 0 || nr >= N) continue;
                        if(nc < 0 || nc >= M) continue;
                        if(visited2[nr][nc]) continue;
                        if(before[nr][nc] != before[row][col]) continue;

                        queue.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                        visited2[nr][nc] = true;
                    }
                }

                int i = 0;
                int j = 0;
                third: for(; i < N; i++){
                    j = 0;
                    for(; j < M; j++){
                        if(visited2[i][j]){
                            if(after[row][col] == after[i][j]) continue;
                        }

                        else{
                            if(after[i][j] == before[i][j]) continue;
                        }
                        break third;
                    }
                }

                if(i == N && j == M) break first;
            }
        }

        if(row == N && col == M) System.out.println("NO");
        else System.out.println("YES");
    }

    public static void print(boolean[][] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }
}
