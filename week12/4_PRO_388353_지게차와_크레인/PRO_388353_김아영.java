/*
문제
1. 세로 n, 가로 m 
2. 알파벳 하나 => 밖부분만
3. 알파벳 두개 => 안부분도

제한사항
50 50

아이디어
bfs
*/
import java.util.*;
class Solution {
    static int N, M;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][] available;
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        N = storage.length;
        M = storage[0].length();
        
        map = new char[N][M];
        available = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = storage[i].charAt(j);
            }
        }
        
        for(String request : requests){
            if(request.length() == 1){
                Queue<int[]> queue = findQueue();
                remove(queue, request.charAt(0));
            }
            else{
                remove(request.charAt(0));
            }
            
        }
        
        answer = count();
        
        
        return answer;
    }
    
    private int count(){
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(available[i][j]) continue;
                count ++;
            }
        }
        return count;
    }
    
    private void remove(char input){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(available[i][j]) continue;
                if(input == map[i][j]){
                    available[i][j] = true;
                }
            }
        }
    }
    
    private void remove(Queue<int[]> queue, char input){
        
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int row = node[0];
            int col = node[1];
            
            if(map[row][col] == input){
                available[row][col] = true;
            }
        }
    }
    
    private Queue<int[]> findQueue(){
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(i * j == 0 || i == N - 1 || j == M - 1){
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        
        boolean flag = true;
        int size = queue.size();
        
        
        while(!queue.isEmpty()){
            if(size == 0){
                if(flag) break;
                size = queue.size();
                flag = true;
            }
            
            int[] node = queue.poll();
            size --;
            
            if(!available[node[0]][node[1]]) {
                queue.add(node);
                continue;
            }
            
            for(int i = 0; i < 4; i++){
                int nr = node[0] + dy[i];
                int nc = node[1] + dx[i];
                
                if(nr < 0 || nr >= N) continue;
                if(nc < 0 || nc >= M) continue;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
                flag = false;
            }         
        }
        
        return queue;
    }
}
