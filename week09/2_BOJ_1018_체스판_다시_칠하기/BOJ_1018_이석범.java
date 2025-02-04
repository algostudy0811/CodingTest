package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 문제 풀이 맨 위의 왼쪽을 B, W로 고정하고 바꿔야하는 것을 체크
 * 8x8 판 중에 바뀌는 것이 최소가 되는 경우를 구함
 * 12744KB 80ms
 */
public class BOJ_1018_이석범 {
	
	static int R, C;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		for(int r=0; r<R;r++) {
			String tmp = br.readLine();
			for(int c=0; c<C;c++) {
				//W는 0 B는 1로 표현
				if(tmp.charAt(c)=='W') {
					graph[r][c] = 0;
				}
				else graph[r][c] = 1;
			}
		}
		
		//바뀌는 경우 체크
		int[][][] reArt = new int[2][R][C];
		
		int result = Integer.MAX_VALUE;
		//이 반복문은 좌상단의 색깔을 고정
		for(int i=0; i<2;i++) {
			//바뀌는 것을 체크
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					//좌상단과 같은 경우
					if((r+c) % 2==0) {
						if(graph[r][c]!=i) {
							reArt[i][r][c] = -1;
						}
					}
					//다른 경우
					else {
						if(graph[r][c]==i) {
							reArt[i][r][c] = -1;
						}
					}
				}
			}
			
			//8x8체스를 만드는 좌표만 체크
			for(int r=0; r<=R-8;r++) {
				A: for(int c=0; c<=C-8;c++) {
					int cnt = 0;
					
					//각 좌표에서 8x8체스판을 만듦
					for(int x=0; x<8;x++) {
						for(int y=0; y<8;y++) {
							//카운트 세기
							if(reArt[i][r+x][c+y]==-1) cnt++;
							
							if(cnt>result) continue A;
						}
					}
					
					result = Math.min(result, cnt);
				}
			}
		}
		
		System.out.println(result);
	}
}
