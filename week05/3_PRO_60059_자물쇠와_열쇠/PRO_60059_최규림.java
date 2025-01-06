/**
1) 열쇠 4방향 회전 배열 만들어두기
2) 4가지의 열쇠를 좌물쇠에 insert 하면서 좌물쇠 열 수 있는지 여부 확인
2-1) 좌물쇠의 범위를 상하좌우로 좌물쇠의 길이(N) 만큼 확대
2-2) 확대한 범위를 기준으로 (-N, -N) ~ (N, N) key를 insert 하면서 값 확인
    ex) (-N, -N) 좌표를 시작점으로 key 의 크기만큼 2중 for문을 돌기 
    -> key값과 lock값의 돌기, 홈 여부를 확인 -> (좌몰쇠의 범위를 넓혀서 계산하고 있기 때문에)lock의 범위를 벗어난 경우는 돌기, 홈 판별 불가능이기 때문에 비교 연산 생략
    -> 검사한 좌표들 중 열쇠 좌표는 돌기이면서, 좌물쇠 좌표는 홈인 경우의 횟수를 누적해서 좌물쇠의 모든 홈 개수와 일치하면 좌물쇠 열 수 있는 경우로 판단해서 return true
    -> 단, 검사한 좌표 중 열쇠와 좌물쇠 모두 돌기라면 해당 케이스는 생략 -> 좌물쇠 열 수 없는 케이스로 판단
**/

class Solution {
    
    static int keyR, keyC, lockR, lockC; // 열쇠와 좌물쇠 길이
    static int lockHoleCnt; // 좌물쇠의 홈 개수
    
    public boolean solution(int[][] key, int[][] lock) {
        keyR = key.length;
        keyC = key[0].length;
        lockR = lock.length; 
        lockC = lock[0].length;
        
        // 좌물쇠 홈 개수 찾기
        for(int r=0; r<lockR; r++) {
            for(int c=0; c<lockC; c++) {
                if(lock[r][c] == 0) lockHoleCnt += 1;
            }
        }        
        
        int[][] key2 = rotate(key);
        int[][] key3 = rotate(key2);
        int[][] key4 = rotate(key3);
   
        if(insert(key, lock) || insert(key2, lock) || insert(key3, lock) || insert(key4, lock)) return true;
        return false;
    }
    
    static boolean insert(int[][] key, int[][] lock) {
        // 좌물쇠 범위 확대
        for(int lr=-lockR+1; lr<lockR*2; lr++) {
            for(int lc=-lockC+1; lc<lockC*2; lc++){
                
                // 확대된 lock에 key 꽂아보기
                int insertCnt = 0; // 열쇠의 돌기와 좌물쇠의 홈이 만나는 횟수
                boolean flag = true; // 열쇠의 돌기와 좌물쇠의 돌기가 만나면 더이상 진행할 필요 X
                for(int r=0; r<keyR; r++) {
                    for(int c=0; c<keyC; c++) {
                        if(!checkLockRange(lr+r, lc+c)) continue;
                        if(key[r][c] == 1 && lock[lr+r][lc+c] == 1) flag = false;
                        if(key[r][c] == 1 && lock[lr+r][lc+c] == 0) insertCnt += 1;
                    }
                    if(!flag) break;
                }
                    
                if(flag && insertCnt == lockHoleCnt) return true;
            }
        }
        return false;
    }        
    
    static int[][] rotate(int[][] key) {
        int temp[][] = new int[keyR][keyC];
        for(int r=0; r<keyR; r++) {
            for(int c=0; c<keyC; c++) {
                temp[r][c] = key[keyR-1-c][r];
            }
        }
        return temp;
    }
    
    // 좌물쇠의 좌표 범위 확인 -> 열쇠의 좌표를 대입하려고 할 때, 해당 좌표가 좌물쇠에 포함된 범위인지 확인
    static boolean checkLockRange(int r, int c) {
        return 0 <= r && r < lockR && 0 <= c && c < lockC;
    }
}
