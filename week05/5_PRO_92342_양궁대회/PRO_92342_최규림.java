/**
1. 문제 풀이 헷갈리지 않게 하기 위해서 과녁순서 0~10으로 바꾸고 최종 return 시점에 돌려두기
2. 백트래킹 방식으로 진행
3. 과녁 점수 10->0점 순서로 해당 점수를 획득할지 여부를 화살의 여부에 맞춰 진행
3-1. 화살을 소비하지 않고 해당 점수를 포기하고 다음 점수로 진행
3-2. 해당 점수를 이길 수 있을 만큼의 화살을 소지하고 있다면, 그만큼 소비하고 다음 점수로 진행
4. 모든 과녁을 진행했다면, 어피치와 비교
4-1. 어피치와의 점수에서 졌으면 갱신하지 않음
4-2. 어피치와의 점수를 이겼다면, 격차 확인
4-2-1. 최초로 이긴 경우라면(격차값이 0이라면) 해당 과녁으로 answer 갱신
4-2-2. 격차값이 이전의 격차보다 작다면, 갱신 X
4-2-3. 격차값이 이전의 격차보다 크다면, 갱신 O
4-2-4. 격차값이 똑같다면, 우선순위 확인 후, 갱신 여부 결정 (작은 점수 더 많인 경우를 우선)
**/
class Solution {
        
    static int[] answer = new int[11];
    static int resultDiff = 0;
    static int[] apeachArr = new int[11];
    
    public int[] solution(int n, int[] info) {                                
        // 어피치 과녁 0~10 순서로 진행
        for(int i=0; i<=10; i++) {
            apeachArr[i] = info[10-i];
        }
        
        // 라이언의 임시 과녁
        int[] result = new int[11];
        dfs(10, n, result);
        
        // 라이언의 맞힌 화살 개수 확인
        int flag = 0;
        for(int a:answer) {
            flag += a;
        }
        
        // 맞힌 결과가 0이라면, 라이언 우승 방법 없는 경우
        if(flag == 0) return new int[]{-1};
        int[] temp = new int[11];
        for(int i=0; i<=10; i++) {
            temp[i] = answer[10-i];
        }
        return temp;
    }
    
    static void dfs(int target, int arrow, int[] result) {
        // 모든 과녁을 다 돌았다면
        if(target == -1) {                 
            // 화살이 남았다면, 0점에 전부 넣기
            if(arrow != 0) result[0] += arrow;        

            // 업데이트 가능 여부 확인 후, 갱신 결정
            if(isPossible(result)) updateAnswer(result);
            
            // 화살이 남았었다면, 원상복구
            if(arrow != 0) result[0] -= arrow;            
            return;
        }
                
        // 어피치의 target 번째 화살 개수
        int apeachCnt = apeachArr[target];
    
        // 이길 수 있는지 여부 확인
        boolean winPossible = arrow >= apeachCnt + 1;
        
        // 지는 경우, 화살 소비하지 않고 다음 점수로 이동        
        dfs(target - 1, arrow, result);
        
        // 이길 수 있다면, 이기는 경우
        if(winPossible) {                           
            result[target] += (apeachCnt + 1);            
            dfs(target - 1, arrow - result[target], result);
            result[target] -= (apeachCnt + 1);
        }        
    }
    
    static void updateAnswer(int[] result) {             
        for(int i=0; i<=10; i++) {
            answer[i] = result[i];
        }
    }
    
    static boolean isPossible(int[] result) {                                
        // 라이언과 어피치 점수 비교
        int lion = 0;
        int apeach = 0;
        for(int i=0; i<=10; i++) {
            if(apeachArr[i] == 0 && result[i] == 0) continue;
            if(apeachArr[i] >= result[i]) apeach += i;
            else lion += i;
        }

        // 어피치가 점수 더 높으면 우승 불가
        if(apeach >= lion) return false;                
                
        // 라이언 첫 우승 케이스인 경우
        if(resultDiff == 0) {            
            resultDiff = lion - apeach;
            return true;
        }
        
        int diff = lion - apeach;        
        
        // 현재 과녁이 이전 과녁보다 점수가 낮으면 갱신, 높으면 갱신하지 않기        
        if(diff > resultDiff) {
            resultDiff = diff;
            return true;
        }
        else if(diff < resultDiff) return false;
        
        // 현재 과녁과 이전 과녁의 점수가 동일하다면, 우선순위 검사 후, 갱신 여부 판별
        if(checkPriority(result)) return true;
        return false;        
    }
    
    static boolean checkPriority(int[] result) {                
        // 낮은 점수를 쏜 화살 수가 많으면 우선순위 높음
        for(int i=0; i<=10; i++) {            
            if(result[i] > answer[i]) return true;            
            else if(result[i] < answer[i]) return false;            
        }        
        return false;
    }    
 }
