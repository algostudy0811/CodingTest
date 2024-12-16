package algorithm;
import java.io.*;
import java.util.*;
/*
BFS로 석유가 있는 곳들을 찾고 배열에 저장
N^2 -> 250_000
set[]을 하나 만들어서 각 열에 어떤 석유 위치가 있는지를 넣음
*/
class PRO_250136_이석범 {
    
    static int R, C;
    //각 열에 어떤 석유지가 있는지 체크하기 위해 사용
    //같은 열에 중복가능성 있으므로 set 사용
    static Set<Integer>[] setList;
    
    static class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int BFS(int[][] land, int startR, int startC, int idx) {
        Queue<Node> queue = new ArrayDeque<>();
        
        queue.offer(new Node(startR, startC));
        //방문 체크를 빈땅으로 만들어서 체크
        land[startR][startC] = 0;
        //현재 열에 현재 석유원을 추가
        setList[startC].add(idx);
        //크기를 재기 위해 사용
        int cnt = 1;
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            
            for(int d = 0; d<4;d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                
                if(nr < 0 || nr >= R || nc < 0 || nc >= C || land[nr][nc] == 0) continue;
                
                //인접한 곳이 석유면 크기 및 해당열에 해당 석유원 추가
                land[nr][nc] = 0;
                setList[nc].add(idx);
                cnt++;
                queue.offer(new Node(nr, nc));
                
            }
            
        }
        
        return cnt;
    }
    
    public int solution(int[][] land) {
        
        //크기 미리 계산
        R = land.length;
        C = land[0].length;
        
        setList = new Set[C];
        for(int c=0; c<C;c++) {
            setList[c] = new HashSet<>();
        }
        
        int idx = 0;
        //각 석유원이 크기가 얼마나인지 저장하기 위해 사용
        Map<Integer, Integer> map = new HashMap<>();
        
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                //석유인 경우 BFS돌림
                if(land[r][c]==1) {
                    int res = BFS(land, r, c, idx);
                    map.put(idx++, res);
                }
            }
        }
        
        int answer = 0;
        
        for(int c=0; c<C;c++) {
            int tmp = 0;
            //각 열에 존재하는 석유원 모두 가져오기
            //단순 이중 반복문보다는 빠를듯
            for(Integer i:setList[c]) {
                tmp += map.get(i);
            }
            answer = Math.max(answer, tmp);
        }
        
        return answer;
    }
}