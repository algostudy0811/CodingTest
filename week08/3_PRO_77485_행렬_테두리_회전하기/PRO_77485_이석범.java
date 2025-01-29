package algorithm;
import java.io.*;
import java.util.*;

/*
회전하는 방식
d를 4번 회전하면서 이전의 값을 다음 값으로 넘기면서 이동
다음값을 임시로 저장하기 위해 cur, next변수 사용
우 -> 하 -> 좌 -> 상으로 이동
약 1시간 소요
*/
class PRO_77485_이석범 {
    
    private int[][] graph;
    
    //시작지점과 끝나는지점을 간단히 하기 위해 사용
    //간단히 하기 위해 update() 메서드 사용해도 될듯
    private class Node {
        int r;
        int c;
        
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        public String toString() {
            return this.r +":"+this.c;
        }
    }
    
    //우 -> 하 -> 좌 -> 상
    private int[] dr = {0, 1, 0, -1};
    private int[] dc = {1, 0, -1, 0};
    
    //정답 배열
    private int[] answer;
    
    private int rotate(int[] query) {
        
        Node start = new Node(query[0], query[1]);
        Node end = new Node(query[2], query[3]);
        
        //방향벡터 변수
        int d = 0;
        int r = start.r;
        int c = start.c;
        int cur = graph[r][c];
        
        //회전하는 곳에서 가장 작은 수를 구하기 위해 사용
        int min = cur;
        while(d < 4) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            //시작지점과 끝지점 사이만 움직임 밖으로 나가면 이전으로 옮기고 방향을 바꿈
            if(nr < start.r || nr > end.r || nc < start.c || nc > end.c) {
                nr -= dr[d];
                nc -= dc[d];
                d++;
                continue;
            }
            
            min = Math.min(min, graph[nr][nc]);
            
            int next = graph[nr][nc];
            
            graph[nr][nc] = cur;
            cur = next;
            
            r = nr;
            c = nc;
        }
        
        return min;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        
        graph = new int[rows+1][columns+1];
        int cnt = 1;
        for(int r=1; r<=rows;r++) {
            for(int c=1; c<=columns;c++) {
                graph[r][c] = cnt++;
            }
        }
        
//        print();
        answer = new int[queries.length];
        
        for(int i=0; i<queries.length;i++) {
            int min = rotate(queries[i]);
            answer[i] = min;
//            print();
        }
        
        print();

        return answer;
    }
    
    private void print() {
        for(int[] r: graph) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}