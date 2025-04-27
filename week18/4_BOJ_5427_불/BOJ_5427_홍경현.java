package STUDY.week18;

import java.io.*;
import java.util.*;

/* 69432KB 464ms
[문제 해석]
불은 동서남북 방향으로 인접한 빈공간으로 퍼짐
불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동 X
얼마나 빨리 빌딩을 탈출할 수 있는지

[입력]
1. TC (1<=TC<=100)
2. w h (1<=w,h<=1000)
3. 빌딩의 지도
    . : 빈공간
    # : 벽
    @ : 시작 위치
    * : 불

[출력]
가장 빠른 탈출 시간
불가능하면 IMPOSSIBLE

[문제 해결 프로세스]
1. 불 위치를 저장하는 큐 fire 기준으로 4방 탐색
2. 현재 위치를 담는 큐 location 기준으로 4방 탐색
 */
public class BOJ_5427_홍경현 {

    static int R, C;
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    static char[][] arr;
    static Node cur;
    static Queue<Node> fire;

    static class Node {
        int r, c;

        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(--T >= 0){
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            arr = new char[R][C];
            fire = new ArrayDeque<>();

            for(int i=0; i<R; i++){
                String str = br.readLine();
                for(int j=0; j<C; j++){
                    arr[i][j] = str.charAt(j);
                    switch (arr[i][j]){
                        case '*':
                            fire.add(new Node(i, j));
                            break;
                        case '@':
                            cur = new Node(i, j);
                            break;
                    }
                }
            }

            int res = escape();
            sb.append(res == 0 ? "IMPOSSIBLE" : res).append('\n');
        }
        System.out.println(sb);
    }

    private static int escape(){
        int res = 0;

        Queue<Node> location = new ArrayDeque<>();
        boolean[][] visit = new boolean[R][C];
        location.add(cur);
        visit[cur.r][cur.c] = true;

        while(!location.isEmpty()){
            res++;
            int fSize = fire.size();
            for(int i=0; i<fSize; i++){
                Node node = fire.poll();

                for(int k=0; k<4; k++){
                    int nr = node.r + dr[k];
                    int nc = node.c + dc[k];

                    if(!check(nr, nc) || arr[nr][nc] != '.') continue;
                    arr[nr][nc] = '#';
                    fire.add(new Node(nr, nc));
                }
            }

            int lSize = location.size();
            for(int i=0; i<lSize; i++){
                Node node = location.poll();

                for(int k=0; k<4; k++){
                    int nr = node.r + dr[k];
                    int nc = node.c + dc[k];

                    if(!check(nr, nc)) return res;
                    if(arr[nr][nc] != '.' || visit[nr][nc]) continue;
                    visit[nr][nc] = true;
                    location.add(new Node(nr, nc));
                }
            }
        }

        return 0;
    }

    private static boolean check(int r, int c){
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
