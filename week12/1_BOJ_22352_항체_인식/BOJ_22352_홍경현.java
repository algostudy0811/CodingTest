package STUDY.week12;

import java.io.*;
import java.util.*;

/* 12,064KB 76ms
[문제 해석]
조직에 CPCU-1202 배식을 놓으면 격자의 칸 중 하나에 항체 생성
이 항체는 현재 속해 있는 칸과 같은 데이터 값을 가지며,
상하좌우로 같은 데이터 값을 가진 인접한 칸이 있을 경우 그 칸으로 퍼져 나감
-> 이 과정을 반복하여 항체가 더이상 퍼져나갈 수 없게 되면 항체는 조직에 완전히 스며듬
-> 항체가 퍼졌던 칸들의 데이터 값은 모두 어떤 동일한 값으로 새로 업데이트됨
-> 원래의 데이터 값과 동일할 수도

VUNO의 연구 데이터는 하나의 조직에 백신을 놓기 전과 후의 결과가 주어질 때,
이 조직에 놓은 백신이 CPCU-1202 백신일 가능성이 있는지 판단하는 프로그램

[입력]
1. N M (1<=N,M<=30) 세로 가로
2. 백신을 놓기 전 촬영 결과
    1 이상 1000이하의 정수 M개가 공백으로 구분
3. 백신을 놓은 뒤 촬영 결과

[출력]
백신일 수 있다면 YES, 없다면 NO 출력

[문제 해결 프로세스]
1. 백신 전을 그룹화
   : BFS 탐색을 통해 같은 값을 가지고, 사방으로 인접한 구역을 List에 기록
2. List에 기록된 구역이 같은 값을 가지는지 확인
 */
public class BOJ_22352_홍경현 {

    static int N, M, cnt;
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    static int[][] before, after;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        before = new int[N][M];
        after = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = false;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                after[i][j] = Integer.parseInt(st.nextToken());
                if(after[i][j] != before[i][j]) flag = true;
            }
        }

        if(!flag){
            System.out.println("YES");
            return;
        }

        //before 그룹화
        visit = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visit[i][j]) continue;
                if(!bfs(i, j)){
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }

    static boolean bfs(int r, int c){
        Queue<int[]> q = new ArrayDeque<>();
        visit[r][c] = true;
        q.add(new int[]{r, c});
        int numBefore = before[r][c];
        int numAfter = after[r][c];
        if((cnt = numAfter == numBefore ? cnt : cnt+1) > 1) return false;

        while(!q.isEmpty()){
            int[] rc = q.poll();

            for(int i=0; i<4; i++){
                int nr = rc[0] + dr[i];
                int nc = rc[1] + dc[i];
                if(!check(nr, nc) || visit[nr][nc] || before[nr][nc] != numBefore) continue;
                if(numAfter != after[nr][nc]) return false;
                q.add(new int[]{nr, nc});
                visit[nr][nc] = true;
            }
        }

        return true;
    }

    static boolean check(int r, int c){
        return r >=0 && c>=0 && r<N && c<M;
    }
}
