package algorithm;
import java.util.*;
import java.io.*;
/*
BFS로 풀면 될것 같음
3차원 배열로 방문처리
키를 가지고 있는 경우와 키를 가지고 있지 않은 경우로 나뉨
키를 가지고 있으면 3차원 배열 중 1을 키를 가지고 있지 않으면 0을 가짐

*/
class PRO_159993_이석범 {
    
    //맵의 그래프를 int로 변환
    private int[][] graph;
    
    //맵 전체 크기
    private int R, C;
    
    //출구를 숫자가 아닌 값으로 하기 위해 밖에서 선언
    private int endR, endC;
    
    // r, c와 키를 가지고 있는 경우를 위해 flag선언
    //키를 가지고 있으면 true, 없으면 false
    class Node {
        int r;
        int c;
        boolean flag;
        
        Node(int r, int c, boolean flag) {
            this.r = r;
            this.c = c;
            this.flag = flag;
        }
    }
    
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    
    private int BFS(int startR, int startC) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startR, startC, false));
        
        //3차원 배열로 선언
        boolean[][][] visited = new boolean[2][R][C];
        visited[0][startR][startC] = true;
        
        //시간을 계산하기 위해 사용
        int cnt = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(--size>=0) {
                Node node = queue.poll();
                int curR = node.r;
                int curC = node.c;
                boolean flag = node.flag;

                for(int d=0; d<4;d++) {
                    int nr = curR + dr[d];
                    int nc = curC + dc[d];
                    
                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]==1) continue;
                    
                    //키를 가지고 있는 경우
                    if(flag) {
                        //방문했으면 넘어감
                        if(visited[1][nr][nc]) continue;
                        
                        //출구를 찾으면 다음에 도착하므로 +1을 하여 시간 반환
                        if(nr == endR && nc == endC) {
                            return cnt+1;
                        }
                        
                        //그외에는 큐에 넣고 방문처리
                        queue.offer(new Node(nr, nc, flag));
                        visited[1][nr][nc] = true;
                        
                    }
                    else {
                        //방문했으면 넘어감
                        if(visited[0][nr][nc]) continue;
                        
                        //키의 위치로 갈 경우
                        if(graph[nr][nc]==2) {
                            //키를 가질때 1번배열에 체크하므로 방문 여부 체크
                            if(visited[1][nr][nc]) continue;
                            visited[1][nr][nc] = true;
                            queue.offer(new Node(nr, nc, true));
                        }
                        //키가 아닌 통로이면 방문처리와 큐에 넣음
                        else {
                            queue.offer(new Node(nr, nc, false));
                            visited[0][nr][nc] = true;
                        }
                    }
                    
                    
                }
            }
            cnt++;
        }
        
        return -1;
    } 
    
    public int solution(String[] maps) {
        
        this.R = maps.length;
        this.C = maps[0].length();
        this.graph = new int[R][C];
        
        int startR = 0;
        int startC = 0;
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                char cur = maps[r].charAt(c);
                switch(cur) {
                    case 'S':
                        startR = r;
                        startC = c;
                        graph[r][c] = 0;
                        break;
                    case 'O':
                        graph[r][c] = 0;
                        break;
                    case 'X':
                        graph[r][c] = 1;
                        break;
                    case 'L':
                        graph[r][c] = 2;
                        break;
                    case 'E':
                        this.endR = r;
                        this.endC = c;
                        graph[r][c] = 0;
                        break;
                }
            }
        }
        
//        print();
        
        int answer = BFS(startR, startC);
        return answer;
    }
    
    private void print() {
        for(int[] r: this.graph) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}