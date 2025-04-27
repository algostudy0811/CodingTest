package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The type Bj g 4 5427 ��.
 *
 * T ����
 * C R
 * .�� ��
 * #�� ��
 * @�� ����
 * *�� ��
 * �Ƹ� ��� �Ѿ�� Ż���� ��
 *
 * ####
 * #*@.
 * ####
 * 
 * 82024KB 704ms
 */
public class BOJ_5427_�̼��� {

    static int R, C;

    static char[][] graph;

    static class Node {
        int r;
        int c;
        boolean isFire;

        Node(int r, int c, boolean isFire) {
            this.r = r;
            this.c = c;
            this.isFire = isFire;
        }
    }

    static int startR, startC;

    static List<Node> fireList;

    //0�� ����� Ż�� 1�� ���� ������ ��
    static boolean[][][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int BFS() {
        Queue<Node> queue = new ArrayDeque<>();
        for(int i=0; i<fireList.size();i++) {
            Node fire = fireList.get(i);
            queue.offer(fire);
            visited[1][fire.r][fire.c] = true;
        }
        queue.offer(new Node(startR, startC, false));
        visited[0][startR][startC] = true;
        int cnt = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(--size >= 0) {
                Node current = queue.poll();

                for(int d=0; d<4;d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

                    //���ϰ��
                    if(current.isFire) {
                        //��� ����ų� ���̰ų� �湮���� ���
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc]=='#' || visited[1][nr][nc]) continue;

                        visited[1][nr][nc] = true;
                        queue.offer(new Node(nr, nc, true));

                    }
                    //���� �ƴ� ���
                    else {
                        //Ż�� ����
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C) return cnt+1;

                        if(graph[nr][nc] == '#' || visited[0][nr][nc] || visited[1][nr][nc]) continue;

                        queue.offer(new Node(nr, nc, false));
                        visited[0][nr][nc] = true;
                    }
                }
            }
            cnt++;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T;t++) {
            st = new StringTokenizer(br.readLine());


            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            graph = new char[R][C];
            visited = new boolean[2][R][C];
            fireList = new ArrayList<>();

            for(int r=0; r<R;r++) {
                String tmp = br.readLine();
                graph[r] = tmp.toCharArray();
            }

            for(int r=0; r<R;r++) {
                for(int c=0; c<C;c++) {
                    if(graph[r][c] == '@') {
                        startR = r;
                        startC = c;
                    }
                    else if(graph[r][c] == '*') {
                        fireList.add(new Node(r, c, true));
                    }
                }
            }

            int result = BFS();
            sb.append(result == 0 ? "IMPOSSIBLE" : result).append("\n");
        }
        System.out.println(sb);
    }
}