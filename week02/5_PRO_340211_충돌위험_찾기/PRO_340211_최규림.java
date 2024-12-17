import java.io.*;
import java.util.*;

/**
1. 로봇별로 경로를 List에 저장
2. 각 List의 index는 시간의 개념으로 생각
3. index 순서대로 모든 로봇의 위치(r, c) 를 비교하여 겹치면 충돌로 판단
**/
class Solution {
        
    static int[][][] board;
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static Point[] pointArr;
    static List<Point>[] visitedList; // 로봇별로 경로 위치 저장할 리스트
    
    static int R, C;
    static int maxStep = 0;
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        int robotCnt = routes.length;
        visitedList = new List[robotCnt];
        for(int i=0; i<robotCnt; i++) {
            visitedList[i] = new ArrayList<Point>();
        }        
        
        // 물류센터 크기 찾기
        pointArr = new Point[points.length + 1];
        for(int i=0; i<points.length; i++) {
            pointArr[i + 1] = new Point(points[i][0], points[i][1]);
            R = Math.max(R, points[i][0]);
            C = Math.max(C, points[i][1]);
        }
        
        // 각 로봇의 경로 탐색
        for(int i=0; i<robotCnt; i++) {
            int startPointIdx = routes[i][0];
            search(i, pointArr[startPointIdx], routes);
        }
        
        
        int step = 0; // 로봇이 움직인 횟수
        // 로봇의 최대 index 값동안 반복 (로봇이 움직인 최대 수)
        while(step < maxStep) {         
            // 로봇이 한칸씩 이동할 때마다 이동 위치 누적하기 위한 배열
            int[][] visited = new int[R+1][C+1];            
            for(int i=0; i<robotCnt; i++) {
                // 로봇별로 이동한 횟수가 다르기 때문에, 이동 횟수가 끝난 로봇은 취급X
                if(visitedList[i].size() <= step) continue;
                
                // i 번째 로봇이 step 번째에 이동한 위치
                Point p = visitedList[i].get(step);                                
                
                // 이전에 위치한 로봇이 존재하면 충돌
                if(visited[p.r][p.c] == 1) {                                        
                    answer += 1;                    
                } 
                
                // 로봇의 위치 (p.r, p.c) 로봇이 존재하는 개수 누적
                visited[p.r][p.c] += 1;                
            }   
            // 로봇 움직인 횟수 누적
            step += 1;
        }
     
        return answer;
    }
    
    static void search(int robotIdx, Point now, int[][] routes) {        
        // 시작지점
        visitedList[robotIdx].add(new Point(now.r, now.c));
        // now -> next 이동하면서 탐색
        for(int i=1; i<routes[robotIdx].length; i++) {            
            int nextPointIdx = routes[robotIdx][i];
            Point next = pointArr[nextPointIdx];
            move(robotIdx, now, next);
            now = next;
        }
        
        // 로봇이 움직인 횟수 최대값 찾기(idx)
        maxStep = Math.max(maxStep, visitedList[robotIdx].size());
    }
    
    static void move(int robotIdx, Point now, Point next) {        
        // 세로부터 계산
        if(now.r < next.r) {
            for(int r=now.r + 1; r<=next.r; r++) {                
                visitedList[robotIdx].add(new Point(r, now.c));                    
            }
        } else if(now.r > next.r){
            for(int r=now.r - 1; r>=next.r; r--) {
                visitedList[robotIdx].add(new Point(r, now.c));                    
            }
        }
        
        // 가로 계산
        if(now.c < next.c) {
            for(int c=now.c+1; c<=next.c; c++) {
                visitedList[robotIdx].add(new Point(next.r, c));                    
            }
        } else if(now.c > next.c) {
            for(int c=now.c-1; c>=next.c; c--) {
                visitedList[robotIdx].add(new Point(next.r, c));    
            }            
        }                           
    }

}
