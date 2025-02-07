package algorithm;
/*
map을 사용하면 key가 겹칠 수도있는데 value의 값을 List로 만들어 해결
*/
import java.io.*;
import java.util.*;
class PRO_72412_이석범 {
    
    Map<String, String> convertMap = new HashMap<>();
    
    Map<String, ArrayList<Integer>> map = new HashMap<>();
    
    //문자열을 쉽게 처리하기 위해 숫자 문자열로 바꿈
    private void init() {
        convertMap.put("cpp", "0");
        convertMap.put("java", "1");
        convertMap.put("python", "2");
        convertMap.put("backend", "0");
        convertMap.put("frontend", "1");
        convertMap.put("junior", "0");
        convertMap.put("senior", "1");
        convertMap.put("chicken", "0");
        convertMap.put("pizza", "1");        
    }
    //각 조건별로 종류의 수를 나타냄
    //-일 경우 사용하기 위함
    private int[] rotate = {2, 1, 1, 1};
    private int[] result;
    //-일 경우 모든 경우를 봐야하므로 sum에 더해 놓음
    private int sum;
    
    private void back(int idx, String q, String answer, int score) {
        
        //키를 완성한 경우 그 키에 존재하는 점수들을 비교
        if(idx==4) {
            // System.out.println(answer);
            int cnt = 0;
            //이진탐색을 해야지 효율성 높아짐
            binarySearch(answer, score);
            return;
        }
        
        //-일 경우 모든 경우의 수를 백트랙킹
        if(q.charAt(idx) == '9') {
            for(int i=0; i<=rotate[idx];i++) {
                back(idx+1, q, answer+String.valueOf(i), score);
            }
        }
        
        else {
            back(idx+1, q, answer+q.charAt(idx), score);
        }
    }
    
    private void binarySearch(String answer, int score) {
        //존재하지 않으면 반환
        if(!map.containsKey(answer)) return;
        //조건에 맞는 것들을 모두 조회
        ArrayList<Integer> list = map.get(answer);
        
        int start = 0;
        int end = list.size();
        
        //이진 탐색으로 조건에 맞는 개수 찾기
        while(start < end) {
            int mid = (start+end) / 2;
            if(list.get(mid) >= score) {
                end = mid;
            }
            else start = mid+1;
        }
        
        sum += list.size() - start;
        // System.out.println(sum);
        
        return;
    }
    
    public int[] solution(String[] info, String[] query) {
        
        map = new HashMap<>();
        
        init();
         //키 만드는 과정
        for(int i=0; i<info.length;i++) {
            String[] tmp = info[i].split(" ");
            
            StringBuilder sb = new StringBuilder();
            
            for(int j=0; j<4;j++) {
                sb.append(convertMap.get(tmp[j]));
            }
            
            String key = sb.toString();
            
            if(map.containsKey(key)) {
                map.get(key).add(Integer.parseInt(tmp[4]));
            }
            else {
                ArrayList<Integer> list = new ArrayList<>();
                
                list.add(Integer.parseInt(tmp[4]));
                
                map.put(key, list);
            }
        }
        
        //각 리스트 정렬
        Set<String> key = map.keySet();
        for(String k:key) {
            Collections.sort(map.get(k));
        }

        int[] answer = new int[query.length];
        
        for(int i=0; i<query.length;i++) {
            sum = 0;
            String[] tmp = query[i].split(" ");
            
            StringBuilder sb = new StringBuilder();

            
            for(int j=0; j<=6;j += 2) {
                if(tmp[j].equals("-")) sb.append("9");
                else sb.append(convertMap.get(tmp[j]));
            }
            // System.out.println(sb.toString());
            
            String q = sb.toString();
            
            back(0, q, "", Integer.parseInt(tmp[7]));
            
            answer[i] = sum;
        }
        
        return answer;
    }
}