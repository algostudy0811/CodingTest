import java.io.*;
import java.util.*;

/**
1. 정점과 간선 관계 정리
- 해당 정점에서 다른 정점으로 나아가는 간선 그래프 -> graph
- 해당 정점으로 다른 정점이 들어오는 간선 그래프 -> reverseGraph
- 정확한 정점의 값들 확인을 위해 HashSet 사용, 해당없는 정점들 PASS

2. SIZE = 1 인 도넛 처리(특이한 케이스)
- 간선 관계 처리하기 애매한 부분 먼저 방문처리하여 다른 케이스에서 배제

3. 임의의 정점 찾기
- 1) 임의의 정점을 향한 간선(reverseGraph)은 존재할 수 없음 + 해당 정점이 다른 정점으로 향하는 간선이 복수개
    - 첫 번째 조건만으로 확인하면 예제1에서 2개 찾아짐(+ 막대 그래프 첫 번째 정점)
- 2) 위의 조건에서 못 찾으면 막대 그래프의 첫 번째에 임의의 정점이 붙었다고 생각할 수 밖에 없음

4. 막대 그래프 찾기 (순환이 안이뤄지는 케이스이기 때문에 쉽게 먼저 처리)
- 1) 일반 막대 그래프 찾기 -> 막대 그래프의 머리(정점)에서 순환이 멈추기 때문에, 해당 정점을 특정할 수 있음, 정점 역추적하면서 방문 처리
- 2) SIZE = 1인 막대 그래프 처리 -> 간선이 없는 경우 찾기

5. 8자 찾기
- 8자 그래프는 나가는 간선과 들어오는 간선의 개수가 2개인 특징이 존재 -> 임의의 정점의 경우를 제외하고 2개씩 존재하는 정점 찾아서 순환하며 방문처리

6. 도넛 찾기
- 나머지 정점은 단순 순환만 하기 때문에 미방문처리된 정점 대상으로 찾기
**/

class Solution {
    static List<List<Integer>> graph = new ArrayList<>(); // 다른 정점으로 향하는 간선 리스트
    static List<List<Integer>> reverseGraph = new ArrayList<>(); // 다른 정점이 들어오는 간선 리스트
    static int maxValue = -1; // 정점 최대값 구하기
    static int randomValue = -1; // 임의의 정점
    static boolean[] visited;
    static Set<Integer> set = new HashSet<Integer>(); // 정점 여부 확인
    static int donut, bar, eight;
    
    public int[] solution(int[][] edges) {
        for(int[] edge:edges){
            int start = edge[0];
            int end = edge[1];
            maxValue = Math.max(maxValue, start);
            maxValue = Math.max(maxValue, end);
            set.add(start);
            set.add(end);
        }
                
        for(int i=0; i<=maxValue; i++) {
            graph.add(new ArrayList<Integer>());            
            reverseGraph.add(new ArrayList<Integer>());
        }
        
        for(int[] edge:edges) {
            int start = edge[0];
            int end = edge[1];
            graph.get(start).add(end);
            reverseGraph.get(end).add(start);
        }
        
        visited = new boolean[maxValue + 1];
        donut = 0;
        bar = 0;
        eight = 0;                
        
        // 임의의 정점 찾기
        for(int i=1; i<=maxValue; i++) {
            if(!set.contains(i) || visited[i]) continue;
            
            // 다른 정점을 향하는 간선이 1개 초과인 경우 + 자신에게 들어오는 간선이 없는 경우
            if(graph.get(i).size() > 1 && reverseGraph.get(i).size() == 0) {
                randomValue = i;
                break;
            }
        }
        
        // 임의의 정점을 찾지 못했다면, 막대 그래프 끝에 걸린 경우 -> 자신에게 들어온 간선이 없는 정점이 임의의 정점
        if(randomValue == -1) {
            for(int i=1; i<=maxValue; i++) {
                if(!set.contains(i) || visited[i]) continue;
                if(reverseGraph.get(i).size() == 0) {
                    randomValue = i;
                    break;
                }
            }            
        }
        
        // 자기 자신에게 돌아오는 도넛 경우 처리하기
        for(int i=1; i<maxValue; i++) {
            if(graph.get(i).size() == 0) continue;
            if(graph.get(i).get(0) == i) {                
                visited[i] = true;
                donut += 1;
            }
        }
        
        // 막대 그래프 찾기
        for(int i=1; i<=maxValue; i++) {
            if(!set.contains(i) || visited[i] || i == randomValue) continue;
            // 막대 그래프 머리 찾기
            if(graph.get(i).size() == 0) {                
                checkBarGraph(i);
                bar += 1;
            }
        }
        
        // 막대 size == 1 인 경우 찾기
        for(int i=1; i<=maxValue; i++) {
            if(!set.contains(i) || visited[i] || i == randomValue) continue;
            if(graph.get(i).size() == 0 || (graph.get(i).size() == 1 && graph.get(i).get(0) == randomValue)) {
                visited[i] = true;
                bar += 1;
            }
        }
        
        // 8자 찾기        
        for(int i=1; i<=maxValue; i++) {
            if(!set.contains(i) || visited[i] || i == randomValue) continue;
            
            int graphCnt = 0;            
            for(int value:graph.get(i)) {
                if(value == randomValue) continue;
                graphCnt += 1;
            }
            
            int reverseGraphCnt = 0;
            for(int value:reverseGraph.get(i)) {
                if(value == randomValue) continue;
                reverseGraphCnt += 1;
            }
                                    
            if(graphCnt == 2 && reverseGraphCnt == 2) {                
                visited[i] = true;                
                for(int start:graph.get(i)) {                            
                    checkVisited(start);
                }
                eight += 1;
            }            
        }
        
        // 도넛 찾기
        for(int i=1; i<=maxValue; i++) {
            if(!set.contains(i) || visited[i] || i == randomValue) continue;
            // 도넛의 끝 부분 찾기, 중간 지점이면 방문여부로 순환 불가                    
            checkVisited(i);
            donut += 1;            
        }
                
        return new int[]{randomValue, donut, bar, eight};
    }
    
    static void checkBarGraph(int start) {
        visited[start] = true;
        while(true) {
            List<Integer> list = reverseGraph.get(start);
            if(list.isEmpty()) {
                visited[start] = true;
                break;
            }
            
            for(int l:list) {
                if(l == randomValue) {
                    if(list.size() == 1) return;
                    else continue;
                }
                visited[l] = true;
                start = l;
            }                        
        }
    }
    
    static void checkVisited(int start) {
        visited[start] = true;
        while(true) {            
            int next = graph.get(start).get(0);
            if(visited[next]) return;
            visited[next] = true;
            start = next;
        }
    }
   
}
