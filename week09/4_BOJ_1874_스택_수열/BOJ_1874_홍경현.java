package STUDY.week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 25,040KB 204ms
[문제 해석]
스택
push: 자료 넣기
pop: 제일 나중에 들어간 자료 빼기

1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓기
push 순서는 오름차순

임의의 수열. 스택을 이용해 그 수열을 만들 수 있는지 없는지
있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지

[입력]
1. n (1<=n<=100,000)
2. 1이상 n 이하의 정수

[출력]
push + pop - 불가능 NO

[문제 해결 프로세스]
수열을 담는 배열 arr[]
1. arr[0]까지 push
2. i == arr[0] : push & pop, idx++
3. flag == true
    if arr[idx-1] > arr[idx] : pop, idx++
4. if i == arr[idx] : push
 */
public class BOJ_1874_홍경현 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;
        int top = -1;
        int[] stack = new int[N];

        for(int i=1; i<=N; i++){
            stack[++top] = i;
            sb.append("+\n");

            while(stack[top] == arr[idx]){
                sb.append("-\n");
                top--;
                idx++;
                if(top<0 || idx >= N) break;
            }
        }

        if(top == -1 && idx == N) System.out.println(sb);
        else System.out.println("NO");
    }
}
