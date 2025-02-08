/*
문제
1. 지원서 작성시 4가지 항목을 반드시 선택해야 합니다. 
1.1 코딩 테스트 항복, cpp, java, python 중 하나를 선택 
1.2 지원 직군 항목, backend, frontend 중 하나 선택
1.3 지원 경력 junior ,senior, 하나 선택
1.4 선호 음식, chicken, pizza 하나 선택
2. 지원자들의 지원 조검을 선택하면 해당 조건에 맞는 지원자가 몇명인지 알 수 있는 도구 만들기
3. 궁금해하는 내용은 [조건] 을 만족하는 사람 중 코딩테스트 점수를 X점 이상 받은 사람은 모두 몇 명인가

제한 사항
info 배열은 5만 이하
코딩 테스트 점수는 10만 이하
query 크기는 10만 이하

아이디어
조건이 4개 이므로 4자리 숫자로 만들어서 hashmap에 넣기
dp 써야 겠다..

풀이과정
1. 문자열로 int[2] 배열 생성
2. int[1] 가 작은 순으로 정렬
3. HashMap<int, int[]> map 생성
4. query도 int[2]로 표기
5. queue돌면서 출력

1234 => 1034으로 하려면 1234 % 1000 / 100 * 100 이다.=> 234 / 100 => 2 =? 200
1234 - 200
*/
import java.util.*;
class Solution {
    public HashMap<Integer, int[]> map;
    public final int MAX_NUM = 100_001;
    public HashSet<Integer> set;
    public int[] solution(String[] info, String[] query) {
        
        map = new HashMap<>();
        for(int one = 0; one <= 3; one++){
            
            for(int second = 0; second <= 2; second ++){
               
                for(int third = 0; third <= 2; third ++){
                    
                    for(int fourth = 0; fourth <= 2; fourth ++){
                      
                        int num = one + 10 * second + 100 * third + 1000 * fourth;
                        int[] arr = new int[MAX_NUM];
                        map.put(num, arr);
                    }
                }
            }
        }
        set = new HashSet<>();
        for(String input : info){
            String[] inputArr = input.split(" ");
            int num = 0;
            for(int idx = 0; idx < inputArr.length - 1; idx ++){
                num += convert(inputArr[idx]);
            }
            
            int idx = Integer.parseInt(inputArr[inputArr.length - 1]);
            dfs(num, idx, 0);
            
            set.clear();
        }
        
        for(int key : map.keySet()){
            int[] mapArr = map.get(key);
            
            for(int idx = mapArr.length - 2; idx >= 0; idx --){
                mapArr[idx] += mapArr[idx + 1];
            }
        }
        
        int[] answer = new int[query.length];
        int idx = 0;
        for(String q : query){
            String[] qArr = q.replace("and", " ").split(" ");
            
            int num = 0;
            for(int i = 0; i < qArr.length - 1; i ++){
                String word = qArr[i].trim();
                num += convert(word);                
            }
            
            int value = Integer.parseInt(qArr[qArr.length - 1]);
                
            answer[idx ++] = map.get(num)[value];
        }
        
 
        return answer;
    }
    
    public void dfs(int key, int value, int idx){
        
        if(idx == 5) return;
        
        if(!set.contains(key)){
              int[] mapArr = map.get(key);
              mapArr[value] ++;
        }
        
        set.add(key);
        dfs(key, value, idx + 1);
        dfs(removeNum(idx, key), value, idx + 1);

        
    }
    
    public int convert(String word){
        
        switch(word){
            case "cpp": return 1;
            case "java": return 2;
            case "python": return 3;
                
            case "backend": return 10;
            case "frontend": return 20;
                
            case "junior": return 100;
            case "senior": return 200;
                
            case "chicken": return 1000;
            case "pizza": return 2000;
            default :  return 0;
        }
    }
    
    public int removeNum(int i, int num){
       // 1234 => 1034으로 하려면 1234 % 1000 / 100 * 100 이다.=> 234 / 100 => 2 =? 200
        return num - num % (int) Math.pow(10, i + 1) / (int)Math.pow(10, i) *  (int)Math.pow(10, i);
    }
}
