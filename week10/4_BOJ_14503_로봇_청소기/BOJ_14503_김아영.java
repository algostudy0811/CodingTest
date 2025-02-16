/*
메모리 : 11852
시간 : 72

문제
1. 청소하는 영역의 개수를 구하라
2. N * M 직사각형
3. 각각의 칸은 벽 또는 빈 칸
4. 청소기는 동 서 남 북 중 하나를 바라본다
5. 처음에 빈칸은 청소되지 않은 상태
6. 청소기는 다음과 같이 작동
6.1 현재 칸 청소
6.2 주변 4칸에 청소되지 않은 빈칸 없을 경우
6.2.1 후진
6.2.2 후진 불가 시 작동 중기
6.3 청소되지 않은 빈칸이 있을 경우 90 회전, 젖진

입력
1. N과 M 각각 50이하
2. 로봇 청소기 존재 칸 및 장향
2.1 북 동 남 서 0 1 2 3
3. 각 장소의 상태 1일 경우 별, 0일경우 빈칸

출력
청소하는 칸의 개수

아이디어
bfs

시간 복잡도
50 * 50
 */
import java.util.*;
import java.io.*;
public class BOJ_14503_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        // 북 동 남 서 0 1 2 3
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

       int answer = 0;
        while(true){

            if(map[row][col] == 0){
                answer ++;
                map[row][col] = 2;
            }

            int idx = 0;
            for(;idx < 4; idx ++){
                int nd = (d - idx - 1 + 4) % 4;
                int nr = row + dy[nd];
                int nc = col + dx[nd];

                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= M) continue;
                if(map[nr][nc] != 0) continue;

                row = nr;
                col = nc;
                d = nd;
                break;
            }

            if(idx == 4){
                int nd = (d + 2) % 4;
                int nr = row + dy[nd];
                int nc = col + dx[nd];

                if(nr < 0 || nr >= N) break;
                if(nc < 0 || nc >= M) break;
                if(map[nr][nc] == 1) break;

                row = nr;
                col = nc;
            }
        }
        System.out.println(answer);
    }

    public static void print(int[][] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(Arrays.toString(array[i]));
        }
        System.out.println();
    }
}
