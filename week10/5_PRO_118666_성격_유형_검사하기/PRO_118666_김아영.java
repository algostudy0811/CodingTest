
import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < survey.length; i ++){
            if(choices[i] == 4) continue;
            
            if(choices[i] < 4){
                char c = survey[i].charAt(0);
                map.put(c, map.getOrDefault(c, 0) + 4 - choices[i] );
            }
            else{
                char c = survey[i].charAt(1);
                map.put(c, map.getOrDefault(c, 0) + choices[i]- 4);
            }
        }
        
        String[] array = {"RT", "CF", "JM", "AN"};
        for(String arr : array){
            int first = map.getOrDefault(arr.charAt(0), 0);
            int second = map.getOrDefault(arr.charAt(1), 0);
            
            if(first >= second) answer += arr.charAt(0);
            else answer += arr.charAt(1);
        }
        return answer;
    }
}
