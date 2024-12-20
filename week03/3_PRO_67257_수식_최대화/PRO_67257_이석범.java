package algorithm;
/*
연산자 순서를 순열로 구함
스택 2개를 사용하여 각 순서일때의 연산자만 사용
*/
import java.util.*;
import java.io.*;
class Solution {
    
    private List<String> inputList = new ArrayList<>();
    
    //+는 1 -는 2 *는 3으로 취급
    private static final int PLUS = 1, MINUS = 2, MULTIPLE = 3;
    
    //경우의 수
    private int[][] permList = {
        {1, 2, 3},
        {1, 3, 2},
        {2, 1, 3},
        {2, 3, 1},
        {3, 1, 2},
        {3, 2, 1}
    };
    
    //최대값 구하기
    private static Long res = 0L;
    
    public void calculate(int idx) {
        
        //스택으로 위에 것은 계산을 위한 용도
        //밑에 스택은 임시 저장하기 위해 사용
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> tmpStack = new ArrayDeque<>();
        
        //맨처음 임시스택에 입력한 숫자 및 연산자들을 저장
        for(int i=inputList.size()-1; i>=0; i--) {
            tmpStack.push(inputList.get(i));
        }
        
        //각 배열에서 각 연산자의 순서일때만 계산
        //즉 예를 들어 0번째가 1이면 +만 계산
        for(int i=0; i<3;i++) {
            while(!tmpStack.isEmpty()) {
                String tmp = tmpStack.pop();
                
                switch(tmp) {
                    case "-":
                        if(permList[idx][i]==MINUS) {
                            String first = stack.pop();
                            String second = tmpStack.pop();
                            
                            stack.push(String.valueOf(Long.valueOf(first)-Long.valueOf(second)));
                        }
                        else {
                            stack.push(tmp);
                        }
                        break;
                    case "+":
                                                if(permList[idx][i]==PLUS) {
                            String first = stack.pop();
                            String second = tmpStack.pop();
                            
                            stack.push(String.valueOf(Long.valueOf(first)+Long.valueOf(second)));
                        }
                        else {
                            stack.push(tmp);
                        }
                        break;
                    case "*":
                                                if(permList[idx][i]==MULTIPLE) {
                            String first = stack.pop();
                            String second = tmpStack.pop();
                            
                            stack.push(String.valueOf(Long.valueOf(first)*Long.valueOf(second)));
                        }
                        else {
                            stack.push(tmp);
                        }
                        break;
                    default:
                        stack.push(tmp);
                        break;
                }
                
            }
            while(!stack.isEmpty()) {
                tmpStack.push(stack.pop());
            }
        }
        
        //마지막 임시 스택에 저장된것이 최종
        Long tmp = Long.parseLong(tmpStack.pop()); 
        
        //절대값 취하기
        res = Math.max(res, Math.abs(tmp));
        
        
    }
    
    
    public long solution(String expression) {
        
        
        long answer = 0;
        
        long tmp = 0;
        //따옴표 없애기 위해 1부터 길이 -1 까지
        for(int i=0; i<expression.length();i++) {
            if(expression.charAt(i) >= '0' && expression.charAt(i) <='9') {
                tmp = tmp * 10 + Long.parseLong(String.valueOf(expression.charAt(i)));
            }
            else {
                inputList.add(String.valueOf(tmp));
                tmp = 0;
                inputList.add(String.valueOf(expression.charAt(i)));
            }
        }
        inputList.add(String.valueOf(tmp));
        
        // for(String s: inputList) {
        //     System.out.println(s);
        // }
        
        //각 순열을 계산
        for(int i=0; i<permList.length;i++) {
            calculate(i);
        }
        
        answer = res;
        
        return answer;
    }
}