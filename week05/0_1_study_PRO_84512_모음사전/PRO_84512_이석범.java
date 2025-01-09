package algorithm;
/*

*/
import java.util.*;
import java.io.*;
class PRO_84512_ÀÌ¼®¹ü {
    
    private char[] wordList = {'A', 'E', 'I', 'O', 'U'};
    private List<String> list = new ArrayList<>();
    
    private void DFS(String input, int idx) {
        
        if(idx==5) {
            return;
        }
        
        for(int i=0; i<5;i++) {
            list.add(input+wordList[i]);
            DFS(input+wordList[i], idx+1);
        }
    }
    
    public int solution(String word) {
        
        DFS("", 0);
        
        int answer = list.indexOf(word) + 1;
        return answer;
    }
}