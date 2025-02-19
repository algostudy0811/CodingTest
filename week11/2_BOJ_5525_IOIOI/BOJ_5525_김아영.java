/*
메모리 : 48604
시간 : 212

문제
1. N + 1개의 I와 N개의 O로 이루어져 있으면서 교대로 나오는 문자열 PN
2. 문자열 S와 정수 N이 주어질 때 S에 PN이 몇 군대 포함되어 있는지 구하기로

입력
1. N
2. S의 길이 M
3. 문자열 S

출력
몇 군데 포함되어 있는지

제한 사항
N은 백만이하
M 백만이하

아이디어
투포인터,
가장 긴 구간 찾기

풀이과정
1. int N 입력
2. int M 입력
3. String S 입력
4. int start = 0, int end = 0 초기값 설정
5. while(true)
5.1 if(end + 2 가 범위를 벗어날 때)
5.1.1 end - start < 2 * N + 1 break;
5.1.2 answer += end - start - 2 * N + 1 를 2로 나눔
5.2 if(S.charAt(end + 1) = "I" && S.charAt(end + 2) == "O")
5.2.1 end = end + 2
5.3. end - start < 2 * N + 1 break;
5.3.1 answer += end - start - 2 * N + 1 를 2로 나눔
4.3.2 start = end + 2, end = end + 2;
 */
import java.util.*;
import java.io.*;
public class BOJ_5525_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String S = st.nextToken();

        int start = 0;
        int end = 0;

        int answer = 0;

        while(true){

            if(end + 2 >= M) {
                answer += count(start, end, N);
                start = 0;
                end = 0;
                break;
            }

            if(start == end){
                if(start + 3 > M) break;

                String word = S.substring(start, start + 3);

                if(!word.equals("IOI")){
                    start ++;
                    end = start;
                    continue;
                }

                end = start + 2;
                continue;
            }

            if(S.charAt(end + 1) == 'O' && S.charAt(end + 2) == 'I'){
                end = end + 2;
                continue;
            }

            answer += count(start, end, N);
            start = end + 1;
            end = end + 1;
        }

        answer += count(start, end, N);
        System.out.println(answer);
    }
    public static int count(int start, int end, int N){
        int length = 2 * N + 1;
        int num = end - start + 1;
        
        int result = 0;
        while(num >= length){
            num -= 2;
            result++;
        }

        return result;
    }
}

/*
1이면 IOIOIOI 에서 3개 7에서 3개, 9에서 4개
2이면 IOIOIOI 에서 2개 7에서 2개 9에서 3개
 */
