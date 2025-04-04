import java.io.*;
import java.util.*;
public class BOJ_2448_김아영 {
    static char[][] stars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        stars = new char[N][N * 2 - 1];

        for(int i = 0; i < N; i ++){
            Arrays.fill(stars[i], ' ');
        }

        makeStar(0, N - 1, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(stars[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void makeStar(int r, int c, int N){
        if(N == 3){
            stars[r][c] = '*';
            stars[r + 1][c - 1] = stars[r + 1][c + 1] = '*';
            stars[r + 2][c - 2] = stars[r + 2][c - 1] = stars[r + 2][c] = stars[r + 2][c + 1] = stars[r + 2][c + 2] = '*';
            return;
        }

        int cut = N / 2;
        makeStar(r, c, cut);
        makeStar(r + cut, c - cut, cut);
        makeStar(r + cut, c + cut, cut);
    }
}
