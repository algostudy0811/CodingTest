package algorithm;
import java.io.*;
import java.util.*;

/*
map, 투 포인터를 활용
*/
class PRO_17684_이석범 {
    
    Map<String, Integer> map = new HashMap<>();
    
    //초기 대문자를 넣는 장면
    private void init() {
        for(char c = 'A'; c<='Z';c++) {
            map.put(String.valueOf(c), map.size()+1);
        }
    }
    
    public int[] solution(String msg) {
        
        init();
        
        // System.out.println(map.get("Z"));
        
        //투포인터 이용
        int start = 0;
        int end = 0;
        
        List<Integer> list = new ArrayList<>();
        //임시 문자열
        String sub = "";
        while(end < msg.length()) {
            
            String tmp = msg.substring(start, end+1);
            //만약 맵에 포함되어있으면 end를 +1하고 지금 문자열을 임시로 넣음
            if(map.containsKey(tmp)) {
                sub = tmp;
                end++;
            }
            //없으면 즉 사전에 넣어야되는 경우
            else {
                //사전에 넣고
                map.put(tmp, map.size()+1);
                //end를 기점으로 다시 하기
                start = end;
                //리스트에 위에 저장한 가장긴 문자열을 리스트에 추가
                list.add(map.get(sub));
            }
            
        }
        //마지막 남은 것 넣기
        list.add(map.get(sub));
        
        //리스트 배열로 변환
        int[] answer = list.stream().mapToInt(Integer::valueOf).toArray();
        // int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}