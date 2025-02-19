/*
메모리 : 403780
시간 : 2348

문제
1. 이중 우선순위 큐
2. 삭제 또는 삽입할 때 우선순위가 높은 것 혹은 낮은 것 중 선택
3. 정수만 저장
4. 최종적으로 큐에 저장된 데이터의 최대값 최소값 출력

입력
1. 테스트 데이터 개수 T
2. 연산 개수 K
3. 연산
3.1 D 1 => 최대값 삭제
3.2 D -1 => 최소 값 삭제
3.3 I N => N 삽입
3.4 비어 있을 경우 무시

아이디어
우선순위 큐를 두개 만들고 각 값에 idx를 확인해서 삭제되었는지 판단
O(N)

풀이과정
1. int T 입력
2. int N 입력
3. PQ ascendingQueue, PQ descendingQueue 생성
4. for i in range(0, N):
4.1. String[] input = 입력
4.2. switch(input[0])
4.2.1. "I" 이면 두 QUeue에 삽입
4.2.2 "D" 1이면 dQ에 -1이면 aQ에

시간 복잡도
K가 백만 이하
 */
import java.util.*;
import java.io.*;
public class BOJ_7662_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int test_case = 0; test_case < T; test_case++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            PriorityQueue<int[]> ascendingQueue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1] > o2[1]) return 1;
                    else if(o1[1] == o2[1]) return 0;
                    return -1;
                }
            });
            PriorityQueue<int[]> descendingQueue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1] > o2[1]) return -1;
                    else if(o1[1] == o2[1]) return 0;
                    return 1;
                }
            });

            boolean[] visited = new boolean[N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                switch (cal){
                    case "I":
                        ascendingQueue.add(new int[]{i, num});
                        descendingQueue.add(new int[]{i, num});
                        break;
                    case "D":
                        if(num == 1){
                            delete(descendingQueue, visited);
                        }

                        else{
                            delete(ascendingQueue, visited);
                        }
                        break;
                }
            }

            setQueue(descendingQueue, visited);
            setQueue(ascendingQueue, visited);

            if(descendingQueue.isEmpty() && ascendingQueue.isEmpty()){
                sb.append("EMPTY\n");
            }
            else{
                sb.append(descendingQueue.poll()[1]);
                sb.append(" ");
                sb.append(ascendingQueue.poll()[1]);
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static void setQueue(PriorityQueue<int[]> queue, boolean[] visited) {
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            if(visited[node[0]]) continue;

            queue.add(node);
            break;
        }
    }
    public static void delete(PriorityQueue<int[]> queue, boolean[] visited){
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            if(visited[node[0]]) continue;

            visited[node[0]] = true;
            break;
        }
    }
}
