package STUDY.week09;

import java.io.*;
import java.util.*;

/* 11,724KB 76ms
[문제 해석]
MxN 크기의 보드
검은색 or 흰색으로 칠해짐
이 보드를 8x8 크기의 체스판으로 만들려고 함
체스판은 검은색과 흰색이 번갈아 칠해져 있어야 함
다시 칠해야하는 정사각형의 최소 개수

[입력]
1. N M (8<=N, M<=50)
2. 각 행의 상태
    B: 검은색 W: 흰색

[출력]
최솟값

[문제 해결 프로세스]
완전 탐색
1. 시작 지점: (0, 0) ~ (N-8, N-8)
2. 시작 지점이 B인 경우, W인 경우
    if now >= res : return;

[시간 복잡도]
O(NxM)
 */
public class BOJ_1018_홍경현 {

    static char[][] arr;
    static int N, M, res = Integer.MAX_VALUE;
    static char[] color = {'B', 'W'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        for(int i=0; i<N; i++){
            arr[i] = br.readLine().toCharArray();
        }

        //시작 지점
        for(int i=0; i<=N-8; i++){
            for(int j=0; j<=M-8; j++){
                check(i, j, 1);
                check(i, j, 0);
            }
        }

        System.out.println(res);
    }

    private static void check(int x, int y, int idx){
        int now = 0;
        for(int i=x; i<x+8; i++){
            for(int j=y; j<y+8; j++){
                idx = (idx+1)%2;
                if(arr[i][j] == color[idx]) continue;
                if(++now >= res) return;
            }
            idx = (idx+1)%2;
        }
        res = Math.min(res, now);
    }
}
