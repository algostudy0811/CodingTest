package STUDY.week10;

import java.util.*;
import java.io.*;

/* 322,856KB 1,036ms
[문제 해석]
매일 세 가지 중 한 행동을 함
1. 주식을 하나 산다
2. 원하는 만큼 가지고 있는 주식을 판다
3. 아무것도 안 한다

매일 주식 가격을 알려주었을 때, 최대 이익이 얼마나 되는지 계산

[입력]
1. T
2. 날의 수 N (2<=N<=1,000,000)
3. 날 별 주가 N개의 자연수  (<=10,000)

[출력]
최대 이익

[문제 해결 프로세스]
역방향으로 최대 이익 갱신하면서 계산
int max = arr[N-1]
for i in [N-2:0]
    if arr[i] < max :
        res += (max - arr[i])
    else
        max = arr[i]

[시간 복잡도]
O(N)
 */
public class BOJ_11501_홍경현 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for(int i=0; i<N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = arr[N-1];
            long res = 0;
            for(int i=N-1; i>=0; i--){
                if(arr[i] < max) res += (max-arr[i]);
                else max = arr[i];
            }

            sb.append(res).append('\n');
        }

        System.out.println(sb);
    }
}
