package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


/*
 * 투 포인터 사용
 * 시작 위치에서 2개의 포인터를 이용
 * 1 1 2 2 3 1 -> 이거의 경우 1122로 4개가나옴
 * 37292KB 340ms
 */
public class BOJ_30804_이석범 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			int n = Integer.parseInt(st.nextToken());
			
			input[i] = n;
			
		}
		
		int result = 1;
		
		//투포인터로 특정 조건에 만족할때까지 right를 움직이고 조건이 안맞으면 left를 움직이면 된다.
		int left = 0;
		int right = 0;
		
		//투포인터를 움직이면서 값을 저장하기 위해 사용
		HashMap<Integer, Integer> map = new HashMap<>();
		
		
		while(right < N) {
			
//			System.out.println(left+" "+right);
//			System.out.println(result);
			
			//사이즈가 2이하일 경우 
			//만약 과일이 존재한다면 기존값에 +1 추가
			if(map.containsKey(input[right])) {
				map.put(input[right], map.get(input[right])+1);
			}
			//그외에
			else {
				//만약 과일이 2개있는데 없는 과일일 경우 3개의 과일이 되므로 left를 움직여야함
				if(map.size()==2) {
					//left의 과일을 찾고 만약 개수가 1개면 map에서 삭제
					int num = map.get(input[left]);
//						System.out.println("num: "+num);
					
					if(num==1) map.remove(input[left]);
					
					//그외에는 -1을 한것을 저장
					else {
						map.put(input[left], num-1);
					}
					
					//left의 값 +1 증가
					left++;
					continue;
				}
				
				map.put(input[right], 1);
			}
			
			//최대개수 저장
			result = Math.max(result, right-left+1);
			
			right++;



		}
		
		System.out.println(result);
		

	}
}
