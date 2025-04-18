import java.util.*;
class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes){
           if(map.containsKey(cloth[1])) map.put(cloth[1], map.get(cloth[1]) + 1);
            else map.put(cloth[1], 1);
        }
        
        
        for(String key : map.keySet()){
            answer = answer * (map.get(key) + 1);
        }
        
        answer = answer - 1;
        return answer;
    }
}
