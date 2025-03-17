package algorithm;
import java.io.*;
import java.util.*;
/*
이전 풀이
이진 탐색 풀이
일정 숫자로 통일 후 남은 숫자는 n개만 -1
3 5 5 > 4 4 5가 작다는 의미

정답 풀이
우선 순위 큐를 사용하여 1씩 빼줌

*/
class PRO_12927_이석범 {

    
//     private final int LEN = 50_001;
    
//     private int max = 0;
    
//     private long[] pow = new long[LEN];
    
//     private long res = Long.MAX_VALUE;
    
//     private void binarySearch(int n, int[] works) {
//         int start = 0;
//         int end = max;
        
//         while(start<=end) {
//             int mid = (start + end) / 2;
            
//             boolean flag = check(mid, works, n);
//             if(flag) start = mid + 1;
//             else {
                
//                 cal(mid, n, works);
                
//                 // System.out.println(mid+" "+res);
                
//                 end = mid - 1;
                
//             }
//         }
//     }
    
//     private boolean check(int mid, int[] works, int n) {
//         int cnt = 0;
//         for(int i:works) {
//             if(i > mid) cnt += i - mid;
//         }
        
//         if(cnt <= n) return false;
//         else return true;
        
//     }
    
//     private void cal(int mid, int n, int[] works) {
//         int[] tmp = new int[max+1];
//         int cnt = n;
//         for(int i:works) {
//             if(i > mid) {
//                 cnt -= (i - mid);
//                 tmp[mid]++;
//             }
//             else {
//                 tmp[i]++;
//             }
//         }
        
//         // System.out.println(Arrays.toString(tmp));
        
//         int idx = mid;
        
//         // System.out.println(idx);
        
//         while(cnt !=0) {
//             if(tmp[idx] > cnt) {
//                 tmp[idx] -= cnt;
//                 tmp[idx-1] += cnt;
//                 break;
//             }
//             else {
//                 cnt -= tmp[idx];
//                 tmp[idx] = 0;
//                 idx--;
//             }
//         }
        
//         long result = 0;
//         // System.out.println(Arrays.toString(tmp));
        
//         for(int i=1; i<=max;i++) {
//             result += pow[i] * tmp[i];
//         }
        
        
//         res = Math.min(result, res);
        
//     }
    
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        long answer = 0;
        
        for(int i:works) {
            pq.add(i);
        }
        
        while(n!=0) {
            int num = pq.poll();
            
            if(num==0) return 0;
            
            pq.offer(num-1);
            n--;
            
        }
        
        for(int i:pq) {
            answer += (long) Math.pow(i, 2);
        }
        
//         for(int i=1; i<LEN;i++) {
//             pow[i] = (long)Math.pow(i, 2);
//         }
        
//         long answer = 0;
        
//         int sum = 0;
        
//         for(int i:works) {
//             sum += i;
//             max = Math.max(max, i);
//         }
        
//         if(sum > n) {
//             binarySearch(n, works);
//             answer = res;
//         }
//         else answer = 0;
        

        return answer;
    }
}