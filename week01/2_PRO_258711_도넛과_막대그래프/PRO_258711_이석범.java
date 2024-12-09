package algorithm;

import java.util.*;
import java.io.*;

/*
1. 도넛 그래프 -> n개 정점 n개 간선, 아무 정점에서 출발 -> n-1개 정점 모두 한번 방문, 모든 정점이 2개씩만 연결
2. 막대 그래프 -> n개 정점 n-1개 간선, 한 정점만이 n-1개 모두 출발, 모든 정점이 1개
3. 8자 그래프 -> 2n+1 개, 2n+2개의 간선, 2개 도넛 에서 정점 하나씩 연결

문제: 각 그래프들은 나눠줘 있음 -> 이걸 연결한 하나의 정점을 찾고 0번째 배열
연결한 하나의 정점 찾는 과정 -> topology정렬로 찾기 -> 막대그래프 마지막 빼고 이거밖에 없음
근데 그래프는 최소 2개 이상임
각 그래프의 개수 구하기

*/
class Solution {
    
    static int[] scores;
    static List<Integer>[] graph;
    static int max = 0;
    
    public void topology(int[][] edges) {
        //1번부터 시작이므로
        scores = new int[max+1];
        graph = new List[max+1];
        //입력값 리스트로 변경
        for(int i=1; i<max+1;i++) {
            graph[i] = new ArrayList<>();
        }
        
        
        for(int r=0; r<edges.length;r++) {
            //input, output
            int i = edges[r][0];
            int o = edges[r][1];
            scores[o]+=1;
            graph[i].add(o);
        }
        
    }
    
    public int check(int num) {
        // System.out.println(num);
        boolean[] visited = new boolean[max+1];
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.offer(num);
        
        int e = 0;
        int n = 0;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            if(visited[node]) continue;
            visited[node] = true;
            n += 1;
            for(int i=0; i<graph[node].size();i++) {
                int next = graph[node].get(i);
                queue.offer(next);
                //방문한거와 상관없이 엣지들은 더함
                e += 1;
            }
        }
        // System.out.println(e+" "+n);
        
        if(e==n) return 1;
        else if(e==n-1) return 2;
        else return 3;
    }
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        for(int r=0; r<edges.length;r++) {
            for(int c=0; c<edges[r].length;c++) {
                max = Math.max(max, edges[r][c]);
            }
        }
        
        // System.out.println(max);
        
        topology(edges);
        
        //여기서 scores가 0이고 나가는게 2개 이상인 것만 가져오기
        int conEdge = 0;
        for(int i=1; i<max+1;i++) {
            if(scores[i]==0 && graph[i].size() >=2) {
                conEdge = i;
                answer[0] = i;
                break;
            }
        }
        //연결된 정점에서 연결된 정점에서 체크
        //1이면 도넛, 2면 막대, 3이면 8자
        for(int i=0; i<graph[conEdge].size();i++) {
            int shape = check(graph[conEdge].get(i));
            answer[shape] += 1;
        }
        
        
        return answer;
    }
}