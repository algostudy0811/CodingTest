/*
메모리 : 12500
시간 : 84

문제
1. 수열 S가 Sk를 기준으로 왼쪽은 오름차순 수열, 오른쪽은 내림차순 수열일 때 바이토닉 수열
2. 수열 A가 주어졌을 때 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이

입력
1. 수열 A의 크기 N
2. 수열 A

제한사항
N은 1000이하

출력
가장 긴 바이토닉 수열의 길이 출력

아이디어
LIS 인가

풀이과정
1. for 문으로 k선정
2. k 기준으로 구하기

시간복잡도
O(N * N)
 */
import java.io.*;
import java.util.*;
public class BOJ_11054_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] accending = new int[N];
        int[] descending = new int[N];
        Arrays.fill(accending, Integer.MAX_VALUE);
        Arrays.fill(descending, Integer.MAX_VALUE);
        int[] accendingDp = new int[N];
        int[] descendingDp = new int[N];

        for(int k = 0; k < N; k++){
            for(int idx = 0; idx < N; idx ++){
                // 오름차순
                if(arr[k] == accending[idx]) break;
                if(arr[k] > accending[idx]) continue;

                accendingDp[k] += idx + 1;
                accending[idx] = arr[k];
                break;
            }

            if(k - 1 < 0) continue;
            accendingDp[k] = Math.max(accendingDp[k], accendingDp[k - 1]);
        }

        for(int k = 0; k < N; k++){
            for(int idx = 0; idx < N; idx ++){
                if(arr[N - 1 - k] == descending[idx]) break;
                if(arr[N - 1 - k] > descending[idx]) continue;

                descendingDp[N - 1 - k] += idx + 1;
                descending[idx] = arr[N - 1 - k];
                break;
            }

            if(N - k == N) continue;
            descendingDp[N - 1 - k] = Math.max(descendingDp[N - 1 - k], descendingDp[N - k]);
        }

        int answer = 0;
        for(int i = 0; i < N; i++){
            answer = Math.max(accendingDp[i] + descendingDp[i] - 1, answer);
        }
        System.out.println(answer);
    }

    public static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
}
