package STUDY.week16;

import java.io.*;
import java.util.*;

/* 81064KB 532ms
[문제 해석]
전화번호 목록의 일관성 확인
한 번호가 다른 번호의 접두어인 경우가 없어야 함

[입력]
1. T (1<=T<=50)
2. 전화번호의 수 N (1<=N<=10,000)
3. 전화번호 (최대 10자리)

[출력]
YES or NO

[문제 해결 프로세스]
투포인터
 */
public class BOJ_5052_홍경현 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        A: for(int t=0; t<T; t++){
            Set<String> set = new HashSet<>();
            Set<Integer> size = new HashSet<>();
            List<String> list = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());

            for(int i=0; i<N; i++){
                String str = br.readLine();
                list.add(str);
                set.add(str);
                size.add(str.length());
            }

            for(String str : list){
                for(int j : size){
                    if(str.length() <= j || !set.contains(str.substring(0, j))) continue;
                    sb.append("NO\n");
                    continue A;
                }
            }

            sb.append("YES\n");
        }

        System.out.println(sb);
    }
}
