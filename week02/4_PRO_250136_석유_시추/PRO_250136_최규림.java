import java.io.*;
import java.util.*;
/**
- BFS 활용
- colArr : 시추관 위치별 총 석유량
- BFS 를 통해서 석유 덩어리의 column 값들을 set에 저장하고, 석유 덩어리의 크기를 구하기
- set에 저장된 column 값 == colArr의 index
- colArr[index] += 석유 덩어리 크기
**/
class Solution {
    
    static int N, M;
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }    
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};    
    static int[] colArr;
    static boolean[][] visited;
    
    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        colArr = new int[M];
        visited = new boolean[N][M];    
        
        // BFS 를 통해 석유 덩어리 찾기
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(land[r][c] == 1 && !visited[r][c]) {
                    bfs(r, c, land);
                }
            }
        }        
        
        // 시추관 최대값
        answer = Arrays.stream(colArr).max().getAsInt();
        return answer;
    }
    
    static void bfs(int r, int c, int[][] land) {
        Queue<Point> q = new ArrayDeque<Point>();
        q.add(new Point(r, c));
        visited[r][c] = true;
        
        // 석유 덩어리의 column 값들 관리
        Set<Integer> colSet = new HashSet<Integer>();
        colSet.add(c);
        
        // 석유 덩어리의 크기
        int cnt = 1;
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            for(int i=0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                
                if(!checkRange(nr, nc) || visited[nr][nc] || land[nr][nc] == 0) continue;
                q.add(new Point(nr, nc));
                visited[nr][nc] = true;
                cnt += 1;
                colSet.add(nc);
            }                
        }
        
        // 석유 덩어리의 column 값들에 석유 덩어리 크기 누적
        Iterator<Integer> iterSet = colSet.iterator();
        while(iterSet.hasNext()) {
            int col = iterSet.next();
            colArr[col] += cnt;
        }
    }
    
    static boolean checkRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
