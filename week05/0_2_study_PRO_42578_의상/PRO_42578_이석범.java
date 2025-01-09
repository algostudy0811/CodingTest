package algorithm;
import java.util.*;
import java.io.*;

/*
해시 맵을 만들어 공통의 종류를 묶고 개수를 계산
개수 + 1을 선택할 수 있으므로(없는 것도 계산)
각 종류별 개수를 곱하고 -1(다 선택 안한 것)
을 하면 됨

*/
class PRO_42578_이석범 {
    
    // private HashMap<String, List<String>> map = new HashMap<>();
    private HashMap<String, Integer>map = new HashMap<>();
    
    public int solution(String[][] clothes) {
        int len = clothes.length;
        
        for(int i=0; i<len;i++) {
            String key = clothes[i][1];
            String value = clothes[i][0];
            
            map.merge(key, 1, Integer::sum);
            
//             if(map.containsKey(key)) {
//                 // map.get(key).add(value);
                
//             }
//             else {
//                 // List<String> list = new ArrayList<>();
//                 // list.add(value);
//                 // map.put(key, list);
//             }
            
        }
        
        Set<String> keyList = map.keySet();
    
        int answer = 1;
        
        for(String k: keyList) {
            answer *= map.get(k)+1;
        }
        answer--;
        return answer;
    }
}