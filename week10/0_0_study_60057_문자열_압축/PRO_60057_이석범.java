package algorithm;

import java.io.*;
import java.util.*;
/*
���� ����ؼ� ����
�� ���� �ִ� ���ڿ��� ������ ���� �߰����� �ʰ� ���ڿ��� ������ ������Ʈ
���ڿ��� �ٸ��� ���� �߰�
*/
class PRO_60057_�̼��� {
    
    private class Node {
        String input;
        int num;
        
        //ó������ num�� 1�� �ʱ�ȭ
        Node(String input) {
            this.input = input;
            this.num = 1;
        }
        
        //num�� ������ ������Ʈ �ϴ� ������ 10�� �̻� �϶� 10�� ���ڿ����� 2���� �̹Ƿ� 
        void plus() {
            this.num += 1;
        }
        
        public String toString() {
            return this.input+num;
        }
        
    }
    
    public int solution(String s) {
        
        //�ִ� ����/2 ������ �ص� ��
        int len = s.length() / 2;
        
        int answer = Integer.MAX_VALUE;
        
        //���̰� 1�� ��� �ؿ� ������ �ȵ�
        if(s.length()==1) return 1;
        
        for(int i=1; i<=len;i++) {
            Deque<Node> stack = new ArrayDeque<>();
            
            int length = 0;
            
            for(int j=0; j<s.length();j+= i) {
                String tmp;
                
                //���� ��� substring
                if(j+i <= s.length()) {
                    tmp = s.substring(j, j+i);
                }
                
                //�������� ������ �� �ϱ�
                else tmp = s.substring(j, s.length());
                
                
                // System.out.println(tmp);
                
                //���� ���ϸ鼭 ���ǿ� �´� ��츦 ��
                if(stack.isEmpty()) stack.push(new Node(tmp));
                else {
                    if(stack.peek().input.equals(tmp)) stack.peek().plus();
                    else stack.push(new Node(tmp));
                }
                
                // System.out.println(tmp);
            }
            
            //������ �������� �ݺ�
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