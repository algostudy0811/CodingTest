package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 트리의 성질인 자식의 오른쪽 자식과 왼쪽 자식의 번호는
 * 2K, 2K+1이므로 오리의 자신의 인덱스를 1까지 부모 노드를 보면서 true인 것을 확인하는 과정을 통해 땅 번호 구함
 * O(NlogN)의 시간복잡도를 가짐
 * 50908KB 392ms
 */
public class BOJ_20364_이석범 {
	
	static boolean[] graph;
	static int N, Q;
	
	static int check(int idx) {
		
		int res = 0;
		int tmp = idx;
		
		//루트 노드인 1까지 확인
		while(tmp>0) {
			//이미 존재하는 경우
			if(graph[tmp]) {
				res = tmp;
			}
			
			//홀수일 경우 추가적으로 1을 뺌
			if(tmp % 2 == 1) {
				tmp--;
			}
			
			tmp = tmp / 2;
		}
		
		if(res==0) {
			graph[idx] = true;
		}
		
		return res;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N+1];
		
		for(int i=0; i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			
			int res = check(idx);
			sb.append(res).append("\n");	
		}
		
		System.out.println(sb);
		
	}
}
