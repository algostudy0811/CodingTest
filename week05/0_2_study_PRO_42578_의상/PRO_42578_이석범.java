package algorithm;
import java.util.*;
import java.io.*;

/*
�ؽ� ���� ����� ������ ������ ���� ������ ���
���� + 1�� ������ �� �����Ƿ�(���� �͵� ���)
�� ������ ������ ���ϰ� -1(�� ���� ���� ��)
�� �ϸ� ��

*/
class PRO_42578_�̼��� {
    
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