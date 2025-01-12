import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
메모리 : 15824
시간 : 248
 */
public class BOJ_6064_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int test_case = 0; test_case < T; test_case ++){
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(N == y) y = 0;
            if(M == x) x = 0;

            int answer = x;
            while(answer <= M * N){

                if(answer % N == y) {
                    break;
                }
                answer += M;
            }

            if(answer > M * N) answer = -1;
            System.out.println(answer);
        }
    }
}
