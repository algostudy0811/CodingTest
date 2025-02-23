package STUDY.week11;

import java.io.*;
import java.util.*;

/* 439,148KB 2332ms
[문제 해석]
이중 우선순위 큐
데이터를 삭제할 때 연산 명령에 따라 우선순위가 가장 높은 데이터 또는 낮은 데이터 중 하나 삭제

<연산>
1. 데이터 삽입 연산
2. 데이터 삭제 연산
    1. 우선 순위가 높은 것 삭제
    2. 우선 순위가 낮은 것 삭제

정수만 저장하는 이중 우선순위 큐 Q
Q에 저장된 각 정수의 값 자체를 우선순위라고 간주
Q에 적용될 일련의 연산 -> 최종적으로 Q에 저장된 데이터 중 최댓값 최솟값

[입력]
1. T
2. 연산 개수 K (1<=K<=1,000,000)
K. 연산을 나타내는 문자 'D' or 'I'와 정수 n
    I n은 정수 n을 삽입하는 연산
    D 1는 Q에서 최댓값을 삭제하는 연산
    D -1는 최솟값을 삭제하는 연산
    만약 Q가 비어있다면, D 연산 무시

[출력]
최댓값 최솟값
Q가 비어있다면 EMPTY

[문제 해결 프로세스]
TreeMap 사용 (log N)
: firstKey - Key가 가장 작은 값 반환
: lastKey - Key가 가장 큰 값 반환
 */
public class BOJ_7662_홍경현 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            int K = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> que = new TreeMap<>();

            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if(c == 'I') que.put(num, que.getOrDefault(num, 0) + 1);
                else{
                    if(que.isEmpty()) continue;

                    int idx = num == 1 ? que.lastKey() : que.firstKey();
                    if(que.put(idx, que.get(idx)-1) == 1) que.remove(idx);
                }
            }

            if(que.isEmpty()) sb.append("EMPTY\n");
            else sb.append(que.lastKey()).append(' ').append(que.firstKey()).append('\n');
        }

        System.out.println(sb);
    }
}
