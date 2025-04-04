package STUDY.week17;

import java.io.*;
import java.util.*;

/* 54752KB 304ms
[문제 해석]
N줄에 0이상 9이하의 숫자가 세 개씩
첫줄에서 시작 -> 마지막 줄
먼저 처음에 적혀있는 세 개의 숫자 중 하나를 골라서 시작
다음 줄로 내려갈 때 제약 조건
- 바로 아래의 수
- 바로 아래의 수와 붙어있는 수로 이동
숫자표가 주어져 있을 때 얻을 수 있는 최대 점수, 최소 점수

[입력]
1. N (1<=N<=100,000)
2. 3개의 숫자

[출력]
최대 점수 최소 점수

[문제 해결 프로세스]
mim[i][0] = Math.min(min[i-1][0], min[i-1][1]) + arr[i][0]
max[i][0] = Math.max(max[i-1][0], max[i-1][1]) + arr[i][0]

min[i][1] = Math.min(min[i-1][0], min[i-1][1], min[i-1][2]) + arr[i][1]
max[i][1] = Math.max(max[i-1][0], max[i-1][1], max[i-1][2]) + arr[i][1]

mim[i][2] = Math.min(min[i-1][2], min[i-1][1]) + arr[i][2]
max[i][2] = Math.max(max[i-1][2], max[i-1][1]) + arr[i][2]
 */
public class BOJ_2096_홍경현 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][3];
        int[][] min = new int[N+1][3];
        int[][] max = new int[N+1][3];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<3; i++){
            min[1][i] = arr[1][i];
            max[1][i] = arr[1][i];
        }

        for(int i=2; i<=N; i++){
            min[i][0] = Math.min(min[i-1][0], min[i-1][1]) + arr[i][0];
            max[i][0] = Math.max(max[i-1][0], max[i-1][1]) + arr[i][0];

            min[i][1] = Math.min(min[i-1][0], Math.min(min[i-1][1], min[i-1][2])) + arr[i][1];
            max[i][1] = Math.max(max[i-1][0], Math.max(max[i-1][1], max[i-1][2])) + arr[i][1];

            min[i][2] = Math.min(min[i-1][2], min[i-1][1]) + arr[i][2];
            max[i][2] = Math.max(max[i-1][2], max[i-1][1]) + arr[i][2];
        }

        int maxRes = Math.max(Math.max(max[N][0], max[N][1]), max[N][2]);
        int minRes = Math.min(Math.min(min[N][0], min[N][1]), min[N][2]);

        System.out.println(maxRes+" "+minRes);
    }
}
