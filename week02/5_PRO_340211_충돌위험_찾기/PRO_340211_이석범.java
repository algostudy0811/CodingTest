package algorithm;
/*
point가 존재하고 각 포인트에서 포인트 사이 최단 경로를 구함
-> 최단 경로가 같으면 r을 우선 적용
map의 크기는 101x101
-> 크기 세기 편하게 하기 위해
routes대로 DFS? -> 시간이 오래 걸릴듯
1번 로봇이 4->2로 가는 최단 경로를 구함
이때 최단 경로이므로 각각의 좌표에 대해
3, 2 -> 4, 7로 간다고 했을 때
r로 +1칸 c로 +5칸 가는것이 조건에 맞게 가는 형태임
리스트로 로봇의 개수 만큼 구한다음 시간 순서에 맞게 저장 후 비교하면 될 듯
*/
import java.util.*;
import java.io.*;
class PRO_340211_이석범 {
    
    static List<Node>[] list;
    static final int N = 101;
    
    static class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        Node(int[] position) {
            this.r = position[0];
            this.c = position[1];
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        
        //로봇 사이즈랑 이동 경로 미리 저장
        int robotSize = routes.length;
        int routeSize = routes[0].length;
        
        //각 로봇의 경로를 저장하기 위해 리스트 사용
        list = new List[robotSize];
        for(int i=0; i<robotSize;i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<robotSize;i++) {
            //1부터 시작이유는 이전값을 가져오면 되는데 최소 좌표가 2개 이므로
            int time = 0;
            for(int j=1; j<routeSize;j++) {
                int preIdx = routes[i][j-1] - 1;
                int curIdx = routes[i][j] - 1;
                //깊은 복사를 하기 위해 사용
                int[] pre = {points[preIdx][0], points[preIdx][1]};
                int[] cur = {points[curIdx][0], points[curIdx][1]};

                
                //pre랑 cur의 r을 먼저 비교
                //r을 먼저 동일하게 맞추고 c를 맞춤
                if(pre[0] > cur[0]) {
                    while(pre[0]!=cur[0]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[0]--;
                    }
                }
                else if(pre[0] < cur[0]) {
                    while(pre[0]!=cur[0]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[0]++;
                    }
                }
                
                if(pre[1] > cur[1]) {
                    while(pre[1]!=cur[1]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[1]--;
                    }
                }
                else if(pre[1] < cur[1]) {
                    while(pre[1]!=cur[1]) {
                        list[i].add(new Node(pre[0], pre[1]));
                        pre[1]++;
                    }
                }
                
                //위에 저렇게 하면 마지막 저장 안됨
                //마지막 위치도 저장하기 위해 사용
                if(j==routeSize-1) {     
                    list[i].add(new Node(pre[0], pre[1]));
                }
            }
        }
        
        // for(int i=0; i<robotSize;i++) {
        //     for(Node n: list[i]) {
        //         System.out.println(n.r+" "+n.c);
        //     }
        //     System.out.println();
        // }
        
        int answer = 0;
        int time = 0;
        
        //최대 걸린 시간 구함
        for(int i=0; i<robotSize;i++) {
            time = Math.max(time, list[i].size());
        }
        
        //각 시간대에 좌표들 저장하기 위해 사용
        Map<Integer, Integer> map;
        
        for(int t=0; t<time;t++) {
            map = new HashMap<>();
            
            //각 시간대에 로봇의 위치를 구하고 그 위치가 중복되는지 확인
            for(int i=0; i<robotSize;i++) {
                if(list[i].size()-1 >=t) {
                    //map에 키를 저장할때 int로 넣고 싶을 때
                    //r, c 값이 있고 최대 100이므로 101인 N을 곱함
                    //혹은 자릿수로 해도됨
                    int tmp = list[i].get(t).r * N + list[i].get(t).c;
                    //키가 존재하면 값에 1을 더함
                    if(map.containsKey(tmp)) {
                        map.put(tmp, map.get(tmp)+1);
                    }
                    //키가 없으면 추가
                    else {
                        map.put(tmp, 1);
                    }
                }
                
            }
            //키를 반복문 돌리면서 1보다 큰 경우 정답에 1을 더함
            for(Integer key: map.keySet()) {
                if(map.get(key)>1) {
                    answer += 1;
                }
            }
        }
        
        return answer;
    }

}