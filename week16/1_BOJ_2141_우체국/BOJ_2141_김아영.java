/*
메모리 : 50000
시간 : 524

문제
1. N개의 마을
2. i번째 마을은 X[i]에 위치, A[i]명이 살고 있다
3. 우체국을 세운다
4. 우체국은 각 사람들까지의 거리의 합이 최소가 되는 위치에 세운다
5. 사람까지의 거리의 합임을 유의

입력
1. N 10만이하

출력
우체국의 위치

아이디어
이진탐색 => 안되는 군
그리디라네요
각 사람들의 거리의 합이 되려면 중간값을 구해야한다
중간값을 구한 뒤 위치가 작은 값부터 탐색해서
처음으로 중간값보다 크거나 같아지는 마을 찾음

 */
import java.util.*;
import java.io.*;
public class BOJ_2141_김아영 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][2];
        long sumNum = 0;
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            arr[i][0] = idx;
            arr[i][1] = num;
            sumNum += num;
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        long result = 0;
        int answer = 0;
        for(int i = 0; i < N; i ++){
            result += arr[i][1];

            if(result >= (sumNum + 1) / 2){
                answer = arr[i][0];
                break;
            }
        }

        System.out.println(answer);
    }
}
