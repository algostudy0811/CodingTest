/*
[문제]
1. A와 B 도둑이 물건을 훔친다
2. 두 도둑 모두 경찰에 붙잡하지 않는다.
3. A 도둑은 물건 i를 훔칠 때 info[i][0]
4. B 도둑은 물건 i를 훔칠 때 info[i][1]
5. 흔적은 1이상 3이하입니다. 
6. A도둑은 흔적의 누적 개수가 N개 이상이면 붙잡힙니다. 
7. B도둑은 흔적의 누적 개수가 M개 이상이면 붙잡힙니다. 
8. 모든 물건을 훔쳤을 때 A 도둑이 남긴 흔적의 누적 개수의 최솟값
9. 두 도둑이 모두 붙잡히지 않게 할 수 없다면 -1 

[제한사항]
1. 물건 = info는 40 이하 
2. N과 M은 120 이하

[아이디어 ]
완전 탐색 => info가 40 이므로 시간 초과
냅색 => B를 최대를 만드는 방법으로 

[풀이과정]
1. int total : A의 가중치를 다 더한 값
2. Node[][] nodes = new Node[][] 생성
3. for i in range(0, info의 길이)
3.1 for j in range(0, M)
3.1.1 nodes[i][j] = new Node(0, 0);
3.1.2 node[i][j] = Math.max(nodes[i - 1][j], nodes[i - 1][j - info[1]])
4. nodes[최대][최대].A 출력

class Node:
    int A;
    int B;
    
[시간복잡도]
O(M * L) L은 info의 길이
*/
import java.util.*;
class Solution {
    public int solution(int[][] info, int N, int M) {
        int answer = 0;
        
        int aTotal = 0;
        for(int[] arr : info){
            aTotal += arr[0];
        }
        
        int L = info.length;
        
        Node[][] nodes = new Node[L][M];
        
        for(int i = 0; i < L; i++){
            for(int j = 0; j < M; j++){
                int A = aTotal;
                int B = 0;
                
                nodes[i][j] = new Node(A, B);
                
                if(j == 0) continue;
                
                
                if(i == 0){
                    A = nodes[0][j - 1].A;
                    B = nodes[0][j - 1].B;
                    
                    if(j == info[i][1]){
                        A -= info[i][0];
                        B += info[i][1];
                    }
                }
                
                else{
                    B = Math.max(nodes[i][j - 1].B, nodes[i - 1][j].B);
                    A = Math.min(nodes[i][j - 1].A, nodes[i - 1][j].A);
                    
                    if(j >= info[i][1]){
                        B = Math.max(B, nodes[i - 1][j - info[i][1]].B + info[i][1]);
                        A = Math.min(A, nodes[i - 1][j - info[i][1]].A - info[i][0]);
                    }    
                }
                
                nodes[i][j].B = B;
                nodes[i][j].A = A;
            }
        }
        
        Node result = nodes[L - 1][M - 1];
        
        if(result.A < N) answer = result.A;
        else answer = -1;
        
        return answer;
    }
    
    public void print(Node[][] nodes){
        for(int i = 0; i < nodes.length; i++){
            System.out.println(Arrays.toString(nodes[i]));
        }
    }
    
    public class Node{
        int A;
        int B;
        
        public Node(int A, int B){
            this.A = A;
            this.B = B;
        }
        
        public String toString(){
            return "A : " + A + " B: " + B;
        }
    }
}
