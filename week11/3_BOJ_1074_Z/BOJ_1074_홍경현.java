package STUDY.week11;

import java.io.*;
import java.util.*;

/* 11,576KB 64ms
[문제 해석]
2^N x 2^N인 2차원 배열을 Z 모양으로 탐색
N>1인 경우 배열의 크기를 4등분한 후에 재귀적으로 방문
N행이 주어졌을 때, r행 c열을 몇번째로 방문하는지 출력

[입력]
N r c

[출력]
몇 번째로 방문했는지 출력

[제한]
1 <= N <= 15
0 <= r, c <= 2^N

[문제 해결 프로세스]
int M = Math.pow(2, M);
1. (r,c)가 1분면이면 res += M*M;
    r = r, c -= M/2
2. (r,c)가 2분면이면 res += 0;
    r = r, c = c
3. (r,c)가 3분면이면 res += 2*M*M;
    r -= M/2, c = c
4. (r,c)가 4분면이면 res += 3*M*M
    r -= M/2, c -= M/2

r과 c가 0이  될 때까지 반복
 */
public class BOJ_1074_홍경현 {

    static int res;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = (int) Math.pow(2, N);
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        check(r, c, M/2);
    }

    static void check(int r, int c, int n){
        if(n == 0){
            if(r==0 && c==0) System.out.println(res);
            return;
        }

        if(r<n && c>=n){
            res += n*n;
            check(r, c-n, n/2);
        }else if(r>=n && c<n){
            res += 2*n*n;
            check(r-n, c, n/2);
        }else if(r>=n){
            res += 3*n*n;
            check(r-n, c-n, n/2);
        }else{
            check(r, c, n/2);
        }
    }
}
