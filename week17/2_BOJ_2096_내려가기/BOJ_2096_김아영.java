import java.io.*;
import java.util.*;
/*

메모리 : 53992
시간 : 336

문제
1. N줄에 0부터 9이하의 숫자가 3개씩
2. 첫 줄에서 시작해서 마지막 줄에 끝나게 되는 놀이
3. 세 개의 숫자 중 하나를 골라서 시작
3.1 바로 아래 수나 아래에 붙어있는 수로만 이동 가능
4. 얻을 수 있는 최대 점수, 최소 점수를 구하라

아이디어
bfs dp
 */
public class BOJ_2096_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][3];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];

        for(int i = 0; i < N; i ++){
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < 3; i ++){
            maxDp[0][i] = map[0][i];
            minDp[0][i] = map[0][i];
        }

        int[] dy = {0, 1, -1};
        for(int row = 1; row < N; row ++){
            for(int col = 0; col < 3; col ++){
                for(int k = 0; k < 3; k ++){
                    if(col + dy[k] < 0 || col + dy[k] >= 3) continue;

                    maxDp[row][col] = Math.max(maxDp[row][col], maxDp[row - 1][col + dy[k]] + map[row][col]);
                    minDp[row][col] = Math.min(minDp[row][col], minDp[row - 1][col + dy[k]] + map[row][col]);

                }
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i ++){
            max = Math.max(max, maxDp[N - 1][i]);
            min = Math.min(min, minDp[N - 1][i]);
        }

        System.out.println(max + " " + min);
    }
}
