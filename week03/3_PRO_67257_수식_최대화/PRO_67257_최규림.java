import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
0. 연산자를 ('+', '*', '-') -> ('+', '*', '!') 로 교체
    - 연산자와 음수(연산 후 결과값)를 구분할 수 없음
1. 순열을 통해 연산자 우선순위의 경우의 수 확인
2. 연산자 우선순위별로 연산 후에 최댓값 return
3. 연산 과정
- 우선순위 연산자를 계산하면서 새로운 expression 갱신을 반복
- 숫자(Queue), 연산자(Queue), 중간과정(Deque) -> 별도로 관리
- 3-1) 우선순위 연산자를 만난 경우 -> 계산하고 중간과정(Deque)에 삽입
- 3-2) 우선순위 연산자를 만나지 않은 경우 -> 계산하지 않고 숫자와 연산자 그대로 중간과정(Deque)에 삽입

!! 중요 !!
- 최종 결과값의 절댓값 2^63 - 1 이하 -> 중간 절댓값도 얼마나 클지 알 수 없음
- Long 으로 해결해야 함
**/
class Solution {
    
    static char[] opArr;
    static boolean[] used;
    static char[] result;
    static String copyExpression;
    static long answer = -1;
    
    public long solution(String expression) {
        copyExpression = new String(expression.replace("-", "!"));        
        char[] expArr = copyExpression.toCharArray();
        List<Character> opList = new ArrayList<Character>();
        // 실제로 사용되는 연산자만 다루기 위해 필터
        for(char exp:expArr) {
            if(isOperator(exp) && !opList.contains(exp)) opList.add(exp);
        }
        
        // opList 그대로 사용해도 되지만 그냥 배열이 편해서..
        opArr = new char[opList.size()];
        for(int i=0; i<opList.size(); i++) {
            opArr[i] = opList.get(i);
        }
        
        used = new boolean[opList.size()];
        result = new char[opList.size()];
        
        perm(0);                
        return answer;
    }
    
    // 1. 순열을 통해 연산자 우선순위의 경우의 수 확인
    static void perm(int idx) {    
        // 2. 연산자 우선순위별로 연산 후에 최댓값 return
        if(idx == opArr.length) {            
            String expression = new String(copyExpression);
            
            for(char op:result) {                                  
                expression = makeNewExpressions(op, expression);                                
            }                           
            answer = Math.max(answer, Math.abs(Long.parseLong(expression)));
            return;
        }
        
        for(int i=0; i<opArr.length; i++) {
            if(used[i]) continue;
            result[idx] = opArr[i];
            used[i] = true;
            perm(idx + 1);
            used[i] = false;
        }
    }
    
    // 3. 연산 과정
    static String makeNewExpressions(char priorOp, String expression) {
        StringBuilder sb = new StringBuilder();
        Deque<String> dq = new ArrayDeque<String>();
        
        // 숫자, 연산자 추출        
        Queue<Long> numberQ = new ArrayDeque<Long>();        
        Queue<Character> operatorQ = new ArrayDeque<Character>();
        // 정규 표현식을 사용하여 숫자와 연산자 추출
        Pattern pattern = Pattern.compile("-?\\d+|[+!*]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String token = matcher.group();

            // 숫자인 경우
            if (token.matches("-?\\d+")) {
                numberQ.add(Long.parseLong(token));  // 음수와 양수 구분하여 추가
            }
            // 연산자인 경우
            else {
                operatorQ.add(token.charAt(0));
            }
        }
        
        // 우선순위 연산자 확인하면서 deque에 삽입하며 새로운 expression 생성
        dq.addLast(numberQ.poll() + "");
        while(!operatorQ.isEmpty()) {
            long a = Long.parseLong(dq.removeLast());
            char op = operatorQ.poll();
            long b = numberQ.poll();            
            
            // 3-1) 우선순위 연산자를 만난 경우 -> 계산하고 중간과정(Deque)에 삽입
            if(op == priorOp) {                
                dq.addLast(calculate(a, b, op) + "");
            }
            // 3-2) 우선순위 연산자를 만나지 않은 경우 -> 계산하지 않고 숫자와 연산자 그대로 중간과정(Deque)에 삽입
            else {
                dq.addLast(a + "");
                dq.addLast(op + "");
                dq.addLast(b + "");                         
            }
        }
        
        // 연산 결과 return
        while(!dq.isEmpty()) {
            sb.append(dq.removeFirst());
        }
        
        return sb.toString();
    }
    
    static long calculate(long a, long b, long op) {
        if(op == '+') return a+b;
        else if(op == '!') return a-b;
        else return a*b;
    }
               
    static boolean isOperator(char c) {
        return c == '+' || c == '!' || c == '*';
    }
}
