package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * ���� Ǯ�� �� ���� ������ B, W�� �����ϰ� �ٲ���ϴ� ���� üũ
 * 8x8 �� �߿� �ٲ�� ���� �ּҰ� �Ǵ� ��츦 ����
 * 12744KB 80ms
 */
public class BOJ_1018_�̼��� {
	
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
				//W�� 0 B�� 1�� ǥ��
				if(tmp.charAt(c)=='W') {
					graph[r][c] = 0;
				}
				else graph[r][c] = 1;
			}
		}
		
		//�ٲ�� ��� üũ
		int[][][] reArt = new int[2][R][C];
		
		int result = Integer.MAX_VALUE;
		//�� �ݺ����� �»���� ������ ����
		for(int i=0; i<2;i++) {
			//�ٲ�� ���� üũ
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					//�»�ܰ� ���� ���
					if((r+c) % 2==0) {
						if(graph[r][c]!=i) {
							reArt[i][r][c] = -1;
						}
					}
					//�ٸ� ���
					else {
						if(graph[r][c]==i) {
							reArt[i][r][c] = -1;
						}
					}
				}
			}
			
			//8x8ü���� ����� ��ǥ�� üũ
			for(int r=0; r<=R-8;r++) {
				A: for(int c=0; c<=C-8;c++) {
					int cnt = 0;
					
					//�� ��ǥ���� 8x8ü������ ����
					for(int x=0; x<8;x++) {
						for(int y=0; y<8;y++) {
							//ī��Ʈ ����
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
