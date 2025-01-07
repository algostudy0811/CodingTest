package algorithm;
import java.io.*;
import java.util.*;

/*
문제 정리
1. 자물쇠의 경우 NxN의 형태로 주어짐 -> 1이 돌기 0이 홈
2. 열쇠의 경우 MxM의 형태로 1이 돌기 0이 홈 -> NxM인줄알았음
3. 자물쇠와 열쇠가 정확히 일치하는 경우를 구하면 됨

아래를 4번 반복
1. 먼저 오른쪽으로 회전한다
2. 0,0부터 끝까지 열쇠와 자물쇠를 검증한다
2-1. 자물쇠가 있는 부분의 모든 곳이 열쇠랑 자물쇠가 1 과 0이어야한다.
2-2. 모든 곳이 정확하게 일치하면 끝낸다.
*/
class PRO_60059_이석범 {
    private int[][] graph;
    
    //회전하는 메서드
    private int[][] rotate(int[][] key, int time, int KR, int KC) {
        int[][] rotated;
        
        //0과 1의 경우 r,c의 길이가 다를 경우 회전
        //-> 길이가 같음..
        switch(time) {
            case 0:
                rotated = new int[KC][KR];
                
                for(int c=0; c<KC;c++) {
                    for(int r=0; r<KR;r++) {
                        rotated[c][r] = key[(KR-1)-r][c];
                    }
                }
                return rotated;
            case 1:
                rotated = new int[KR][KC];
                
                for(int r=0; r<KR;r++) {
                    for(int c=0; c<KC;c++) {
                        rotated[r][c] = key[(KR-1) - r][(KC-1) - c];
                    }
                }
                
                return rotated;
            case 2:
                rotated = new int[KC][KR];
                
               for(int c=0; c<KC;c++) {
                    for(int r=0; r<KR;r++) {
                        rotated[c][r] = key[r][(KC-1) - c];
                    }
                }
                
                return rotated;
            case 3:
                rotated = new int[KR][KC];
                for(int r=0; r<KR;r++) {
                    for(int c=0; c<KC;c++) {
                        rotated[r][c] = key[r][c];
                    }
                }
                return rotated;
        }
        
        return null;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        
        //키의 크기
        int KR = key.length;
        int KC = key[0].length;
        
        //자물쇠의 크기
        int LR = lock.length;
        int LC = lock[0].length;
        
        //키의 가로 세로 중 큰 것을 구함
        int add = Math.max(KR, KC);
        
        //lock을 확장
        int R = LR + ((add-1) * 2);
        int C = LC + ((add-1) * 2);
        
        int[][] graph = new int[R][C];
        
        //열쇠가 자물쇠 밖에 삐져나올 수 있는데
        //이때의 경계를 구분하기 위해 -1로 채움
        for(int r=0; r<R;r++) {
            Arrays.fill(graph[r] ,-1);
        }
        
        for(int r=0;r<LR;r++) {
            for(int c=0; c<LC;c++) {
                graph[add+r][add+c] = lock[r][c];
            }
        }
        
        //회전 4번하면되므로 맨처음부터 회전
        for(int t=0; t<4;t++) {
            int[][] curKey;
            
            //90도 회전, 270도 회전인 경우
            if(t%2==0) {
                curKey = new int[KC][KR];
            }
            else {
                curKey = new int[KR][KC];
            }
            
            curKey = rotate(key, t, KR, KC);
            
            // print(curKey);
            
            //각 좌표에서 검증
            for(int r=0; r<R;r++) {
                for(int c=0; c<C;c++) {
                    //검증하는 메서드
                    boolean isClear = check(r, c, graph, curKey);
                    if(isClear) return true;
                }
            }
            
        }
        
        return false;
    }
    
    private boolean check(int curR, int curC, int[][] graph, int[][] key) {
        int R = graph.length;
        int C = graph[0].length;
        
        int KR = key.length;
        int KC = key[0].length;
        
        int[][] tmp = new int[R][C];
        for(int r=0; r<R;r++) {
            System.arraycopy(graph[r], 0, tmp[r], 0, C);
        }
        for(int r=0;r<KR;r++) {
            for(int c=0; c<KC;c++) {
                
                if(((curR+r) >= R) || ((curC + c) >= C)) continue;

                if(key[r][c]==1) {
                    if(tmp[curR+r][curC+c]==1) return false;
                    else tmp[curR+r][curC+c] = 1;
                }
            }
        }
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                if(tmp[r][c]==0) return false;
                
            }
        }
        
        return true;
    }
    
    private void print(int[][] list) {
        for(int[] r: list) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}