package STUDY.week10;

import java.util.*;
import java.io.*;

/* 11,856KB 72ms
[문제 해석]
로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수 구하기
방의 크기 NxM
각 칸은 벽 또는 빈 칸
청소기가 바라보는 방향 : 동 서 남 북 중 하나

1. 현재 칸이 아직 청소되지 않은 경우 현재 칸 청소
2. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
    2.1 바라보는 방향을 유지한 채 한칸 후진할 수 있다면 한칸 후진
    2.2 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동 종료
3. 4칸 중 청소되지 않은 빈칸이 있는 경우
    3.1 반시계 방향으로 90도 회전
    3.2 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 한칸 전진

[입력]
1. N M (3<=N,M<=50)
2. 처음 로봇청소기의 좌표 r, c 바라보는 방향 d
    0: 북, 1: 동, 2: 남, 3: 서
3. NxM의 값
    0: 청소되지 않은 빈칸
    1: 벽

[출력]
청소하는 칸의 개수

[문제 해결 프로세스]
현재 위치 cur[], 현재 방향 dir
반복문 돌면서 청소 arr[r][c] = 2
 */
public class BOJ_14503_홍경현 {

    static int[][] arr;
    static int N, M, res;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(r, c, dir);
        System.out.println(res);
    }

    static void clean(int r, int c, int d){
        //1. 현재 칸 청소
        if(arr[r][c] == 0) {
            arr[r][c] = 2;
            res++;
        }

        //2. 반시계 회전
        for(int i=0; i<4; i++){
            d = d-1<0 ? 3 : d-1;
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(arr[nr][nc] != 0) continue;
            clean(nr, nc, d);
            return;
        }

        //3. 청소되지 않은 빈 칸이 없을 경우
        int nr = r - dr[d];
        int nc = c - dc[d];
        if(arr[nr][nc] == 1) return;
        clean(nr, nc, d);
    }
}
