/*
메모리 : 11608
시간 : 68
문제
1. S를 T로 바꾸는 게임
2. 두 가지 연산만 가능
2.1 문자열 뒤에 A를 추가
2.2 문자열 뒤에 B를 추가하고 문자열을 뒤집는다.
3. 주어진 조건을 이용해서 S를 T로 바꿀 수 있는지

입력
1. S와 T, 각각 50 이하

출력
바꿀 수 있으면 1 없으면 0

아이디어
완전탐색 => dfs 시초
투포인터

시간 복잡도
최대 2의 50승 ??? => 완탐 말고는 모르겠는데...

 */
import java.util.*;
import java.io.*;
public class BOJ_12919_김아영 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String T = st.nextToken();
        String reverseS = reverseString(S);

        int answer = 0;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, T.length() - 1, true));

        while(!queue.isEmpty()){

            Node node = queue.poll();

            if(node.end - node.start + 1 == S.length()){
                String word = T.substring(node.start, node.end +1);

                if(node.flag && word.equals(S)){
                    answer = 1;
                    break;
                }
                if (!node.flag && word.equals(reverseS)) {
                    answer = 1;
                    break;
                }

                continue;
            }

            if(node.flag){
                if(T.charAt(node.end) == 'A'){
                    queue.add(new Node(node.start, node.end - 1, true));
                }

                if(T.charAt(node.start) == 'B'){
                    queue.add(new Node(node.start + 1, node.end, false));
                }
            }
            else{
                if(T.charAt(node.start) == 'A'){
                    queue.add(new Node(node.start + 1, node.end, false));
                }

                if(T.charAt(node.end) == 'B'){
                    queue.add(new Node(node.start, node.end - 1, true));
                }

            }
        }

        System.out.println(answer);
    }

    public static class Node{
        int start;
        int end;
        boolean flag;

        public Node(int start, int end, boolean flag){
            this.start = start;
            this.end = end;
            this.flag = flag;
        }
    }

    public static String reverseString(String input){
        StringBuilder reverseInput = new StringBuilder();
        for(int i = input.length() - 1; i >= 0; i--){
            reverseInput.append(input.charAt(i));
        }
        return reverseInput.toString();
    }
}
