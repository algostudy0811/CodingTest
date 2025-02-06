package algorithm;

import java.io.*;
import java.util.*;

class Solution {
    
    private int[][] graph;
    
    private boolean[] visited;
    
    private int BFS(int j, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(j);
        visited[j] = true;
        int cnt = 1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int i=1; i<=n;i++) {
                if(graph[cur][i]==1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        
        graph = new int[n+1][n+1];
        
        int len = wires.length;
        int answer = Integer.MAX_VALUE;
        
        for(int i=0; i<len;i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        
        for(int i=0; i<len;i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            graph[a][b] = 0;
            graph[b][a] = 0;
            
            visited = new boolean[n+1];
            
            int tmp = 0;
            
            for(int j=1; j<=n;j++) {
                if(visited[j]) continue;
                
                int num = BFS(j, n);
                if(tmp==0) tmp = num;
                else tmp = Math.abs(tmp - num);
            }
            
            answer = Math.min(answer, tmp);
            
            graph[a][b] = 1;
            graph[b][a] = 1;
            
        }
        
        return answer;
    }
}