
/*
완탐하면 시간 초과날 것 같은데
*/
import java.util.*;
class Solution {
    static int[] cal = {10, 20, 30, 40};
    static int[][] users;
    static int[] emoticons;
    static int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        this.users = users;
        this.emoticons = emoticons;
        answer = new int[2];
        
        dfs(0, new int[users.length][emoticons.length]);
        
        return answer;
    }
    
    private void dfs(int idx, int[][] arr){
        if(idx == emoticons.length){
           
            int result = 0;
            int remove = 0;
            int count = 0;
            
            for(int i = 0; i < arr.length; i ++){
                int sum = Arrays.stream(arr[i]).sum();
                result += sum;
                if(users[i][1] > sum) continue;
                remove += sum;
                count ++;
            }
            
            if(count > answer[0]){
                answer[0] = count;
                answer[1] = result - remove;
            }
            
            else if(count == answer[0] && result - remove > answer[1]){
                answer[0] = count;
                answer[1] = result - remove;
            }
            
            return;
        }
        
        for(int i = 0; i < 4; i++){
            setArr(cal[i], idx, arr);
            dfs(idx + 1, arr);
        }
    }
    
    private void setArr(int num, int idx, int[][] arr){
        
        for(int i = 0; i < users.length; i++){
            arr[i][idx] = 0;
            if(num < users[i][0]) continue;
            
            arr[i][idx] = (int)(emoticons[idx] * (100 - num) / 100.0);
        }

    }
}
