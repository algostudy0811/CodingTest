package algorithm;
import java.io.*;
import java.util.*;

/*
ȸ���ϴ� ���
d�� 4�� ȸ���ϸ鼭 ������ ���� ���� ������ �ѱ�鼭 �̵�
�������� �ӽ÷� �����ϱ� ���� cur, next���� ���
�� -> �� -> �� -> ������ �̵�
�� 1�ð� �ҿ�
*/
class PRO_77485_�̼��� {
    
    private int[][] graph;
    
    //���������� ������������ ������ �ϱ� ���� ���
    //������ �ϱ� ���� update() �޼��� ����ص� �ɵ�
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
    
    //�� -> �� -> �� -> ��
    private int[] dr = {0, 1, 0, -1};
    private int[] dc = {1, 0, -1, 0};
    
    //���� �迭
    private int[] answer;
    
    private int rotate(int[] query) {
        
        Node start = new Node(query[0], query[1]);
        Node end = new Node(query[2], query[3]);
        
        //���⺤�� ����
        int d = 0;
        int r = start.r;
        int c = start.c;
        int cur = graph[r][c];
        
        //ȸ���ϴ� ������ ���� ���� ���� ���ϱ� ���� ���
        int min = cur;
        while(d < 4) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            //���������� ������ ���̸� ������ ������ ������ �������� �ű�� ������ �ٲ�
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