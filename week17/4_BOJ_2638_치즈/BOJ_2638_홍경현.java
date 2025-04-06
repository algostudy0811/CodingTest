package STUDY.week17;

import java.io.*;
import java.util.*;

/* 14756KB 128ms
[문제 해석]
NxM 모눈종이 얇은 치즈
4변 중 2변 이상이 공기와 접촉하면 한시간 만에 녹음
치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정
치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정
주어진 모든 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간

[입력]
1. N M (5<=N, M<=100)
2. 치즈가 있는 부분 1, 없는 부분 0

[출력]
치즈가 모두 녹아 없어지는데 걸리는 정확한 시간

[문제 해결 프로세스]
1. 치즈의 위치를 q에 담기
2. DFS 탐색 -> 바깥 부분을 3으로 변경
3. q 탐색
3.1 2변 이상이 3이면, 3으로 변경 후 0이 있으면 DFS 탐색하여 3으로 변경
3.2 녹지 않는 치즈면 q에 담기
 */
public class BOJ_2638_홍경현 {

    static int N, M, res;
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    static int[][] arr, cntArr;
    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr= new int[N][M];
        cntArr = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) q.add(new int[]{i, j});
            }
        }

        //외부 공기 파악
        dfs(0, 0);

        //치즈 녹이기
        cheese();

        System.out.println(res);
    }

    static void cheese(){
        while(!q.isEmpty()){
            int size = q.size();
            Queue<int[]> temp = new ArrayDeque<>();
            while(--size >= 0){
                int[] rc = q.poll();
                if(cntArr[rc[0]][rc[1]] < 2) q.add(rc);
                else {
                    temp.add(rc);
                }
            }

            while(!temp.isEmpty()){
                int[] rc = temp.poll();
                arr[rc[0]][rc[1]] = 3;
                dfs(rc[0], rc[1]);
            }
            res++;
        }
    }

    static void dfs(int r, int c){
        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(!check(nr, nc) || arr[nr][nc] == 3) continue;
            if(arr[nr][nc] == 1) {
                cntArr[nr][nc]++;
                continue;
            }
            arr[nr][nc] = 3;
            dfs(nr, nc);
        }
    }

    static boolean check(int r, int c){
        return r >=0 && c>=0 && r<N && c<M;
    }
}
