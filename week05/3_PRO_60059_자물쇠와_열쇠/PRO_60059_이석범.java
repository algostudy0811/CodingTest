package algorithm;
import java.io.*;
import java.util.*;

/*
���� ����
1. �ڹ����� ��� NxN�� ���·� �־��� -> 1�� ���� 0�� Ȩ
2. ������ ��� MxM�� ���·� 1�� ���� 0�� Ȩ -> NxM���پ˾���
3. �ڹ���� ���谡 ��Ȯ�� ��ġ�ϴ� ��츦 ���ϸ� ��

�Ʒ��� 4�� �ݺ�
1. ���� ���������� ȸ���Ѵ�
2. 0,0���� ������ ����� �ڹ��踦 �����Ѵ�
2-1. �ڹ��谡 �ִ� �κ��� ��� ���� ����� �ڹ��谡 1 �� 0�̾���Ѵ�.
2-2. ��� ���� ��Ȯ�ϰ� ��ġ�ϸ� ������.
*/
class PRO_60059_�̼��� {
    private int[][] graph;
    
    //ȸ���ϴ� �޼���
    private int[][] rotate(int[][] key, int time, int KR, int KC) {
        int[][] rotated;
        
        //0�� 1�� ��� r,c�� ���̰� �ٸ� ��� ȸ��
        //-> ���̰� ����..
        switch(time) {
            case 0:
                rotated = new int[KC][KR];
                
                for(int c=0; c<KC;c++) {
                    for(int r=0; r<KR;r++) {
                        rotated[c][r] = key[(KR-1)-r][c];
                    }
                }
                return rotated;
            case 1:
                rotated = new int[KR][KC];
                
                for(int r=0; r<KR;r++) {
                    for(int c=0; c<KC;c++) {
                        rotated[r][c] = key[(KR-1) - r][(KC-1) - c];
                    }
                }
                
                return rotated;
            case 2:
                rotated = new int[KC][KR];
                
               for(int c=0; c<KC;c++) {
                    for(int r=0; r<KR;r++) {
                        rotated[c][r] = key[r][(KC-1) - c];
                    }
                }
                
                return rotated;
            case 3:
                rotated = new int[KR][KC];
                for(int r=0; r<KR;r++) {
                    for(int c=0; c<KC;c++) {
                        rotated[r][c] = key[r][c];
                    }
                }
                return rotated;
        }
        
        return null;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        
        //Ű�� ũ��
        int KR = key.length;
        int KC = key[0].length;
        
        //�ڹ����� ũ��
        int LR = lock.length;
        int LC = lock[0].length;
        
        //Ű�� ���� ���� �� ū ���� ����
        int add = Math.max(KR, KC);
        
        //lock�� Ȯ��
        int R = LR + ((add-1) * 2);
        int C = LC + ((add-1) * 2);
        
        int[][] graph = new int[R][C];
        
        //���谡 �ڹ��� �ۿ� �������� �� �ִµ�
        //�̶��� ��踦 �����ϱ� ���� -1�� ä��
        for(int r=0; r<R;r++) {
            Arrays.fill(graph[r] ,-1);
        }
        
        for(int r=0;r<LR;r++) {
            for(int c=0; c<LC;c++) {
                graph[add+r][add+c] = lock[r][c];
            }
        }
        
        //ȸ�� 4���ϸ�ǹǷ� ��ó������ ȸ��
        for(int t=0; t<4;t++) {
            int[][] curKey;
            
            //90�� ȸ��, 270�� ȸ���� ���
            if(t%2==0) {
                curKey = new int[KC][KR];
            }
            else {
                curKey = new int[KR][KC];
            }
            
            curKey = rotate(key, t, KR, KC);
            
            // print(curKey);
            
            //�� ��ǥ���� ����
            for(int r=0; r<R;r++) {
                for(int c=0; c<C;c++) {
                    //�����ϴ� �޼���
                    boolean isClear = check(r, c, graph, curKey);
                    if(isClear) return true;
                }
            }
            
        }
        
        return false;
    }
    
    private boolean check(int curR, int curC, int[][] graph, int[][] key) {
        int R = graph.length;
        int C = graph[0].length;
        
        int KR = key.length;
        int KC = key[0].length;
        
        int[][] tmp = new int[R][C];
        for(int r=0; r<R;r++) {
            System.arraycopy(graph[r], 0, tmp[r], 0, C);
        }
        for(int r=0;r<KR;r++) {
            for(int c=0; c<KC;c++) {
                
                if(((curR+r) >= R) || ((curC + c) >= C)) continue;

                if(key[r][c]==1) {
                    if(tmp[curR+r][curC+c]==1) return false;
                    else tmp[curR+r][curC+c] = 1;
                }
            }
        }
        
        for(int r=0; r<R;r++) {
            for(int c=0; c<C;c++) {
                if(tmp[r][c]==0) return false;
                
            }
        }
        
        return true;
    }
    
    private void print(int[][] list) {
        for(int[] r: list) {
            for(int c:r) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}