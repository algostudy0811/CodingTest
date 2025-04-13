package STUDY.week18;

import java.io.*;
import java.util.*;

/* 51904KB 392ms
[문제 해석]
상사가 직속 부하 칭찬 -> 부하의 직속 부하 연쇄 칭찬
직속 상사와 직속 부하관계, 칭찬에 대한 정보가 주어질 때
각자 얼마의 칭찬을 받았는지 출력

[입력]
1. 직원수 N, 칭찬 횟수 M
    (2<=N,M<=100,000)
2. 직원 n명의 직속 상사 번호
    - 직속 상사의 번호는 자신의 번호보다 작음
3. 직원 번호 i, 칭찬 수치 w (2<=i<=n, 1<=w<=1,000)

[출력]
칭찬 받은 정도

[문제 해결 프로세스]
1. 상속 상사의 칭찬을 더하기
 */
public class BOJ_14267_홍경현 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] count = new int[N+1];
        int[] parents = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            int k = Integer.parseInt(st.nextToken());
            parents[i] = k;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            count[n] += w;
        }

        for(int i=2; i<=N; i++) count[i] += count[parents[i]];

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) sb.append(count[i]).append(' ');
        System.out.println(sb);
    }

}
