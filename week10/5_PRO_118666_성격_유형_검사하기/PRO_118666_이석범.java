package algorithm;
import java.io.*;
import java.util.*;
class PRO_118666_이석범 {
    
    private Map<Character, Integer> map = new HashMap<>();
    //초기 세팅
    private void init() {
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
    }
    
    //뭐가 더 큰지 체크
    private char check(char a, char b) {
        int aCnt = map.get(a);
        int bCnt = map.get(b);
        
        if(aCnt > bCnt) return a;
        else if(aCnt < bCnt) return b;
        //아스키 코드 상에서 알파벳이 뒤일 수록 숫자 높아짐
        else {
            return a > b ? b : a;
        }
        
    }
    
    public String solution(String[] survey, int[] choices) {
        
        init();
        
        String answer = "";
        int len = survey.length;
        
        for(int i=0; i<len;i++) {
            String q = survey[i];
            int a = choices[i] - 4;
            
            if(a > 0) {
                char c = q.charAt(1);
                
                map.put(c, map.get(c)+a);
            }
            else if(a < 0) {
                char c = q.charAt(0);
                map.put(c, map.get(c)-a);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(check('R', 'T'));
        sb.append(check('C', 'F'));
        sb.append(check('J', 'M'));
        sb.append(check('A', 'N'));
        answer = sb.toString();
        
        return answer;
    }
}