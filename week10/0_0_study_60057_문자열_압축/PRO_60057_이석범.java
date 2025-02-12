package algorithm;

import java.io.*;
import java.util.*;
/*
덱을 사용해서 구현
맨 위에 있는 문자열과 같으면 덱에 추가하지 않고 문자열의 개수를 업데이트
문자열이 다르면 덱에 추가
*/
class PRO_60057_이석범 {
    
    private class Node {
        String input;
        int num;
        
        //처음에는 num을 1로 초기화
        Node(String input) {
            this.input = input;
            this.num = 1;
        }
        
        //num의 개수를 업데이트 하는 이유는 10개 이상 일때 10은 문자열에서 2글자 이므로 
        void plus() {
            this.num += 1;
        }
        
        public String toString() {
            return this.input+num;
        }
        
    }
    
    public int solution(String s) {
        
        //최대 길이/2 까지만 해도 됨
        int len = s.length() / 2;
        
        int answer = Integer.MAX_VALUE;
        
        //길이가 1일 경우 밑에 실행이 안됨
        if(s.length()==1) return 1;
        
        for(int i=1; i<=len;i++) {
            Deque<Node> stack = new ArrayDeque<>();
            
            int length = 0;
            
            for(int j=0; j<s.length();j+= i) {
                String tmp;
                
                //길이 대로 substring
                if(j+i <= s.length()) {
                    tmp = s.substring(j, j+i);
                }
                
                //마지막은 나머지 다 하기
                else tmp = s.substring(j, s.length());
                
                
                // System.out.println(tmp);
                
                //덱을 비교하면서 조건에 맞는 경우를 함
                if(stack.isEmpty()) stack.push(new Node(tmp));
                else {
                    if(stack.peek().input.equals(tmp)) stack.peek().plus();
                    else stack.push(new Node(tmp));
                }
                
                // System.out.println(tmp);
            }
            
            //스택이 빌때까지 반복
            while(!stack.isEmpty()) {
                Node n = stack.pop();
                String tmp;
                if(n.num==1) tmp = n.input;
                else tmp = n.input+n.num;
                
                length += tmp.length();
                
               // System.out.println("num "+ n.num);
            }
            
            // System.out.println(length);
            
            answer = Math.min(length, answer);
            
            System.out.println();
        }
        
        
        return answer;
    }
}