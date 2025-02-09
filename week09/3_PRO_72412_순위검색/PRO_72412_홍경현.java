package STUDY.week09;

import java.util.*;

/*
[문제해석]
지원서 작성
- cpp, java, python 중 택1
- backend, fronted 중 택1
- junior, senior 중 택1
- chicken, pizza 중 택1

지원 조건 선택 시 해당 조건에 맞는 지원자가 몇 명인지 알 수 있는 도구
ex) java, backend, junior, pizza, 점수>=50 지원자

[조건]을 만족하는 사람 중 코딩테스트 점수 x점 이상을 받은 사람은 모두 몇 명인가?

[문제]
지원서 정보와 코딩 테스트 점수를 하나의 문자열로 구성한 값의 배열info
개발팀이 궁금해하는 문의 조건이 문자열 형태로 담긴 배열 query
매개변수로 주어질 때 각 문의 조건에 해당하는 사람들의 숫자를 순서대로 배열에 담아 return

[제한 사항]
1. 1<=info[]<=50,000
    공백 구분
2. 1<=score<=100,000
3. 1<=query[]<=100,000
    and 구분
    '-' : 해당 조건을 고려하지 않음

[문제 해결 프로세스]
cpp: 1, java: 2, python: 3
backend: 1, frontend: 2
junior: 1, senior: 2
chicken: 1, pizza: 2
-> Map에 Score 오름차순 정렬

'-' : 각 경우에 대해 모두 재귀함수 탐색
*/
class PRO_72412_홍경현 {
    static Queue<Integer> queue = new ArrayDeque<>();
    static Map<String, String> map = new HashMap<>();
    static Map<String, ArrayList<Integer>> score = new HashMap<>();
    static String[] str;
    static int res;

    private static void init(String[] info){
        map.put("cpp", "1");
        map.put("java", "2");
        map.put("python", "3");
        map.put("backend", "1");
        map.put("frontend", "2");
        map.put("junior", "1");
        map.put("senior", "2");
        map.put("chicken", "1");
        map.put("pizza", "2");
    }

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        init(info);
        infoParse(info);

        for(int i=0; i<query.length; i++){
            res = 0;
            str = query[i].split(" ");
            binarySearch(0, "");
            answer[i] = res;
        }
        return answer;
    }

    private static void infoParse(String[] info){
        for(String string : info){
            String[] temp = string.split(" ");
            String key = "";
            for(int i=0; i<4; i++){
                key += map.get(temp[i]);
            }
            if(!score.containsKey(key)){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(temp[4]));
                score.put(key, list);
            }else{
                score.get(key).add(Integer.parseInt(temp[4]));
            }
        }

        for(String key : score.keySet()){
            Collections.sort(score.get(key));
        }
    }

    private static void binarySearch(int idx, String key){
        if(idx == 8){
            if(!score.containsKey(key)) {
                return;
            }
            ArrayList<Integer> list = score.get(key);
            int num = Integer.parseInt(str[7]);
            int left = 0;
            int right = list.size();
            int mid = 0;

            while(left < right){
                mid = (left+right)/2;
                if(list.get(mid) < num) left = mid+1;
                else right = mid;
            }
            res += list.size()-left;
            return;
        }

        if(!str[idx].equals("-")){
            binarySearch(idx+2, key+map.get(str[idx]));
        }else{
            binarySearch(idx+2, key+"1");
            binarySearch(idx+2, key+"2");
            if(idx == 0) binarySearch(idx+2, key+"3");
        }
    }
}