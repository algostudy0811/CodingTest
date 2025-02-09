import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.StringTokenizer;

/*
메모리 32764
시간280

문제
1. 1부터 n까지의 수를 스택에 넣다가 뽑아 늘어놓음으로써 하나의 수열을 만들 수 있다.
2. 스택에서 push하는 순서는 오름차순
3. 임의의 수열이 주어졌을 떄 스택을 이용해 수열을 만들자
4. 이를 계산하자

입력
1. N 십만 이하
2. 수열

출력
push는 + pop - 로 불가능한 경우에는 NO 출력

아이디어
구현
 */
import java.util.*;
public class BOJ_1874_김아영 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N + 1; i++){

            stack.add(i);
            sb.append("+\n");
            while(!stack.isEmpty()){
                int num = stack.pollLast();

                if(num == arr[idx]){
                    idx ++;
                    sb.append("-\n");
                    continue;
                }

                stack.add(num);
                break;
            }
        }

        while(!stack.isEmpty()){
            int num = stack.pollLast();

            if(num == arr[idx]){
                idx ++;
                sb.append("-\n");
                continue;
            }

            stack.addLast(num);
            break;
        }

        if(stack.size() == 0) System.out.println(sb.toString());
        else System.out.println("NO");
    }
}
