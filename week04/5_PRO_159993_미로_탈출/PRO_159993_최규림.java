import java.io.*;
import java.util.*;

/**
1) 출발지 -> 레버
1-1) 레버 못찾으면 -1 return
1-2) 레버 찾으면, 레버 찾은 시간 저장

2) 레버 -> 도착지
2-1) 도착지 못찾으면 -1 return
2-2) 도착지 찾으면, 도착지 찾은 시간 저장

3) 레버 찾은 시간과 도착지 찾은 시간 합쳐서 return
**/
class Solution {
    
    static class Point {
        int r, c, t;
        Point(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static Point start, lever, end;
    static int R, C;
    static boolean[][] visited;
    
    public int solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        
        for(int r=0; r<R; r++) {
            char[] chArr = maps[r].toCharArray();
            for(int c=0; c<C; c++) {
                if(chArr[c] == 'S') start = new Point(r, c, 0);
                else if(chArr[c] == 'L') lever = new Point(r, c, 0);
                else if(chArr[c] == 'E') end = new Point(r, c, 0);
            }
        }
                        
        // 출발지 -> 레버
        visited = new boolean[R][C];
        int startToLeverTime = bfs(start, lever, maps);
        if(startToLeverTime == -1) return -1;        
        
        // 레버 -> 도착지
        // 방문여부 초기화
        visited = new boolean[R][C];
        int leverToEndTime = bfs(lever, end, maps);
        if(leverToEndTime == -1) return -1;
                
        return startToLeverTime + leverToEndTime;
    }
    
    static int bfs(Point start, Point target, String[] maps) {
        Queue<Point> q = new ArrayDeque<Point>();
        q.add(start);
        visited[start.r][start.c] = true;
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            for(int i=0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                
                // 범위 이탈했거나, 이동 불가능한 경우(통로X, 이미 방문한 통로) PASS
                if(!checkRange(nr, nc) || !isPossible(nr, nc, maps[nr].charAt(nc))) continue;
                
                // 목적지에 도착한 경우, 소요시간 return
                if(isTarget(nr, nc, target)) return now.t + 1;
                visited[nr][nc] = true;
                q.add(new Point(nr, nc, now.t + 1));
            }            
        }
        
        // 목적지에 도달하지 못한 경우 -1 return
        return -1;
    }
    
    // 레버 or 도착지 확인 메서드
    static boolean isTarget(int r, int c, Point target) {
        return r == target.r && c == target.c;
    }
    
    // 이동 가능 확인 메서드(통로O + 첫 방문)
    static boolean isPossible(int r, int c, char value) {
        return value != 'X' && !visited[r][c];
    }
    
    // 범위 확인 메서드
    static boolean checkRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
