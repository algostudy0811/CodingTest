import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
메모리 : 15772
시간 : 92

[문제]
1. 현재 점 => 십만 이하
2. 도착 점으로 이동한다.
3. 현재 위치가 X일 때 X + 1, X - 1로 1초 후 이동한다.
4. 현재 위치가 X일 때 2 * X 위치로 0초 후 이동한다.

입력
1. 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다.

출력
가장 빠른 시간을 출력한다

아이디어
queue + visited배열
 */
import java.util.*;
public class BOJ_13549_김아영 {
    static final int MAX_VALUE = 200_002;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[MAX_VALUE];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{N, 0});
        int answer = 0;

        while(!queue.isEmpty()){

            int[] node = queue.poll();
            int num = node[0];

            if(num >= MAX_VALUE / 2) continue;
            if(num == K) {
                answer = node[1];
                break;
            }
            if(!visited[num * 2]){
                visited[num * 2] = true;
                queue.add(new int[]{num * 2, node[1]});
            }
            if(num > 0 && !visited[num - 1]) {
                visited[num - 1] = true;
                queue.add(new int[]{num - 1, node[1] + 1});
            }

            if(!visited[num + 1]){
                visited[num + 1] = true;
                queue.add(new int[]{num + 1, node[1] + 1});
            }
        }

        System.out.println(answer);
    }
}
