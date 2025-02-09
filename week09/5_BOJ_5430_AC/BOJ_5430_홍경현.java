package STUDY.week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*76952KB 464ms
[문제 해석]
새로운 언어 AC
R: 순서 뒤집기, D: 첫 번째 수 버리기
배열이 비어있는데 D를 사용한 경우 에러
최종 결과를 구하는 프로그램

[입력]
1. T (최대 100)
2. 수행 함수 p (1<=p의 길이<=100,000)
3. 배열에 들어있는 수의 개수 n (0<=n<=100,000)
4. 배열에 들어있는 정수 (1<=x<=100)
* 전체  테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만 이하

[출력]
수행 결과
에러가 발생한 경우 error

[문제 해결 프로세스]
flag - 0: 정방향, 1: 역방향
R: flag ^= 1;
D: 0이면 start++, 1이면 end--;
if start > end : error
 */
public class BOJ_5430_홍경현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        A: for(int t=0; t<T; t++){
            String ac = br.readLine();
            int N = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().split(",");
            arr[0] = arr[0].substring(1);
            if(N>0) arr[N-1] = arr[N-1].substring(0, arr[N-1].length()-1);

            int start = 0, end = N, flag = 0;
            for(int i=0; i<ac.length(); i++){
                char c = ac.charAt(i);
                if(c=='R'){
                    flag ^= 1;
                }else{
                    if(flag==0) start++;
                    else end--;
                }

                if(start > end) {
                    sb.append("error\n");
                    continue A;
                }
            }

            sb.append('[');
            if(start==end){
                sb.append("]\n");
            }else if(flag == 0){
                for(int i=start; i<end-1; i++) sb.append(arr[i]).append(',');
                sb.append(arr[end-1]).append("]\n");
            }else{
                for(int i=end-1; i>start; i--) sb.append(arr[i]).append(',');
                sb.append(arr[start]).append("]\n");
            }

        }

        System.out.println(sb);
    }
}