import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
그리디
34128KB 508ms
 */
public class BOJ_2036_이석범 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        //0인 것 카운트
        int zeroCnt = 0;
        //양수만 추기
        PriorityQueue<Integer> plus = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1, o2) * -1
        );
        //음수만 추가
        PriorityQueue<Integer> minus = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1, o2) * -1
        );

        for(int i=0; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num==0) zeroCnt++;
            else if(num > 0) plus.offer(num);
            else minus.offer(num*-1);
        }

        long result = 0;

        while(!minus.isEmpty()) {
            //음수는 2개 이상일 경우 곱해서 양수로 만드는 게 이득
            //1개일 경우 만약 0이 존재하면 0으로 만들고 아니면 result에서 뺌
            if(minus.size()==1) {
                if(zeroCnt!=0) {
                    break;
                }
                else {
                    result -= minus.poll();
                }
            }
            else {
                long n1 = minus.poll();
                long n2 = minus.poll();

                result += n1*n2;
            }
        }

        //양수는 곱하는게 항상 이득이 아닐 수도 있으므로 곱한거랑 더한거랑 비교해서 구하기
        while(!plus.isEmpty()) {
            if(plus.size()==1) {
                result += plus.poll();
            }
            else {
                long n1 = plus.poll();
                long n2 = plus.poll();

                result += Math.max(n1*n2, n1+n2);
            }
        }

        System.out.println(result);
    }
}
