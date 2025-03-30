/*
메모리 : 129448
시간 : 616

문제
1. 전화번호 목릭이 일관성이 있는지 없는지를구하시오
2. 일관성을 유지하려면 한 번호가 다른 번호의 접두어인 경우가 없어야 한다

입력
1. 테스트케이서 t개
2. 전화번호 수 n
3. 전화번호가 하나씩 주어짐, 최대 10글자

출렵
일관성이 있으면 YES 없으면 NO

아이디어
1. 정렬
 */
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;
public class BOJ_5052_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(st.nextToken());
        for(int t = 0; t < testcase; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            Node[] nodes = new Node[11];
            String[] phones = new String[N];
            for(int i = 0; i < N; i ++){
                st = new StringTokenizer(br.readLine());
                phones[i] = st.nextToken();
            }

            Arrays.sort(phones, (o1, o2) -> o2.length() - o1.length());

            boolean flag = false;
            for(int i = 0; i < N; i ++){
                String phone = phones[i];

                if(flag) continue;

                Node[] subNodes = nodes;
                boolean flag2 = false;
                for(char c : phone.toCharArray()){
                    int idx = c - '0';

                    if(subNodes[idx] == null){
                        subNodes[idx] = new Node();
                        subNodes = subNodes[idx].child;
                        flag2 = false;
                    }

                    else {
                        subNodes = subNodes[idx].child;
                        flag2 = true;
                    }
                }

                if(flag2) flag = true;
            }

            if(flag) System.out.println("NO");
            else System.out.println("YES");

        }
    }

    private static class Node{
        Node[] child;

        public Node(){
            child = new Node[11];
        }

        public String toString(){
            return " child : " + Arrays.toString(child);
        }
    }
}
