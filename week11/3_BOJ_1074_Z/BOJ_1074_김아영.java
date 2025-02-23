import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
메모리 : 11528
시간 : 64

[문제]
1. 한 변의 크기가 2차원 배열을 Z모양으로 탐색
2. N이 1 초과 일 경우 2의 N - 1로 4등분한 후 재귀적으로 순서대로 방문
3. N이 주어졌을 때 r행 c행을 몇 번째로 방문하는지 출력하는 프로그램

입력
1. N, r, c

출력
r행 c열을 몇번째로 방문했는지 출력

제한
N은 15이하 => 완탐 불가

아이디어
왼쪽 상단 오른쪽 하단 좌표안에 rc가 존재하는 지 확인
재귀

풀이
1. N, r, c, 입력
2. dfs((0, 0), (N, N), 0, N)
3. 출력

def dfs(int[] start, int[] end, 0, N)
1. answer != 0 return
2. if(N == 1)
2.1 4칸 이므로 어느 곳인지 확인 후 return
3. start end 사이에 있는지 확인
 */
public class BOJ_1074_김아영 {
    public static int[] dy = {0, 0, 1, 1};
    public static int[] dx = {0, 1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long answer = dfs(new long[]{0, 0}, new long[]{r, c}, (long)Math.pow(2, N), 0);
        System.out.println(answer);
    }

    public static long dfs(long[] start, long[] dest, long N, long result){

        for(int i = 0; i < 4; i++){
            long nr = start[0] + dy[i] * (N / 2);
            long nc = start[1] + dx[i] * (N / 2);

            if(nr > dest[0] || nr + N / 2 - 1 < dest[0]) continue;
            if(nc > dest[1] || nc + N / 2 - 1 < dest[1]) continue;
            if(nr == dest[0] && nc == dest[1]) {
                return result + N * N / 4 * i;
            }

            if(N != 2) return dfs(new long[]{nr, nc}, dest, N / 2, result + N * N / 4 * i);

        }
        return result;
    }
}
