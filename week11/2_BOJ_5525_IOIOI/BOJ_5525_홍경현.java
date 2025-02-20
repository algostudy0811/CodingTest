package STUDY.week11;

import java.io.*;
import java.util.*;

/* 22,460KB  164ms
[문제 해석]
N+1개의 I와 N개의 O로 이루어져 있으면, I와O가 교대로 나오는 문자열 Pn
P1 = IOI
P2 = IOIOI
P3 = IOIOIOI...
PN = O가 N개

I와 O로 이루어진 문자열 S와 정수 N이 주어졌을 때,
S 안에 Pn이 몇 군데 포함되어 있는지 구하는 프로그램

[입력]
1. N (1<=N<=1,000,000)
2. S의 길이 M (2N+1<=M<=1,000,000)
3. S

[출려]
S에 Pn이 몇 군데 포함되어 있는지 출력

[문제 해결 프로세스]
1. str(0)부터 str(M)까지 탐색
1.1 str(i) == 'I'가 아니면 continue
1.2 I인 곳부터 2N+1+i 까지 탐색
1.2.1 IO 순서로 반복되지 않을 경우 i = idx+1
1.2.2 cnt = 0이면 res++하고 뒤에 2칸 확인 (OI)

[시간 복잡도]
O(M)
 */
public class BOJ_5525_홍경현 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        int res = 0;
        int cnt = 2*N+1;
        char[] IO = {'O', 'I'};

        for(int i=0; i<M; i++){
            if(arr[i] != IO[cnt%2]) {
                cnt = arr[i] == 'I' ? 2*N : 2*N+1;
                continue;
            }

            if(--cnt == 0) {
                cnt = 2;
                res++;
            }
        }

        System.out.println(res);
    }
}
