package algorithm;
import java.io.*;
import java.util.*;

class PRO_388353_이석범 {
    
    private int[][] graph;
    private int R, C;
    
    private void remove(int alphabet) {
        for(int r=1; r<R+1;r++) {
            for(int c=1; c<C+1;c++) {
                if(graph[r][c] == alphabet) graph[r][c] = 0;
            }
        }
    }
    
    private class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    
    private void BFS(int alphabet) {
        boolean[][] visited = new boolean[R+2][C+2];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            
            int r = n.r;
            int c = n.c;
            
            for(int d=0; d<4;d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >=R+2 || nc < 0 || nc >=C+2 || visited[nr][nc]) continue; 
                
                if(graph[nr][nc] == alphabet) {
                    graph[nr][nc] = 0;
                    visited[nr][nc] = true;
                    continue;
                }
                if(graph[nr][nc] == 0) {
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            
        }
         
    }
    
    
    public int solution(String[] storage, String[] requests) {
        
        R = storage.length;
        C = storage[0].length();
        
        graph = new int[R+2][C+2];
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                graph[r+1][c+1] = storage[r].charAt(c);
            }
        }
        
        // print();
        
        for(int i=0; i<requests.length;i++) {
            int alphabet = requests[i].charAt(0);
            
            //그냥 크레인인 경우 이중 반복문으로 0으로 만들기
            if(requests[i].length()==2) {
                remove(alphabet);
            }
            //지게차인 경우 0,0을 기준으로 체크
            else {
                BFS(alphabet);
            }
        }
        
        // print();
        
        int answer = 0;
        
        for(int[] r: graph) {
            for(int c: r) {
                if(c!=0) answer++;
            }
        }
        
        return answer;
    }
    
    
    
    private void print() {
        for(int[] r: graph) {
            for(int c: r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}