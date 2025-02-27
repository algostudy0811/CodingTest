/*
[문제]
1. 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가됩니다. 
2. 한 번 증설한 서비스는 k시간 동안 운영하고 그 후에 반납합니다. 
3. 하루 동안 모든 게임 이용자가 게임을 하기 위해 서버를 최소 몇 번 증설해야하는지 

[제한사항]
players 길이 24 
players 원소 1000이하
m은 1000이하
k는 24

[아이디어]
dp

[풀이과정]
1. int[] servers = new int[players]; => 증설 서버
2. for i in range(0, 24)
2.1 servers[i] = servers[i - 1] - servers[i - k] + servers[i - k - 1];
2.2 if(servers[i] * m > players[i]) continue
2.3 servers[i] += Math.ceil(players[i] - servers[i] * m) / 2.0
*/
import java.util.*;

class Solution {
    public static final int HOURS = 24;
    public int solution(int[] players, int m, int k) {
        
        int[] servers = new int[HOURS];
        
        for(int time = 0; time < HOURS; time ++){
            
            if(time > 0) servers[time] = servers[time - 1];
            
            int before = 0;
            if(time - k >= 0) before = servers[time - k];
            if((servers[time] - before) * m > players[time]) continue;
            
            int server = (players[time] - (servers[time] - before) * m) /  m;
            servers[time] += server;
        }
        
        return servers[HOURS - 1];
    }
}
