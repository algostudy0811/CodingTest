package algorithm;
import java.util.*;
import java.io.*;

/*
n명의 사용자, m개의 이모티콘
이모티콘의 한개의 할인율은 10,20,30,40%
각 사용자는 일정할인 모두 구매
합이 일정이상이면 플러스 가입
-> 이모티콘의 할인율을 계산
-> 비율 4개, 이모티콘 최대 7개 
-> 28개
*/
class PRO_150368_이석범 {
    
    static int cnt = 0;
    static int price = 0;
    
    static void back(int[][] users, int[] emoticons, int idx, int[] list) {
        
        if(idx==emoticons.length) {
            double[][] saleList = new double[emoticons.length][2];
            
            for(int i=0; i<emoticons.length;i++) {
                switch(list[i]) {
                    case 1:
                        saleList[i][0] = (double) emoticons[i] * 0.9;
                        saleList[i][1] = 10;
                        break;
                    case 2:
                        saleList[i][0] = (double) emoticons[i] * 0.8;
                        saleList[i][1] = 20;
                        break;
                    case 3:
                        saleList[i][0] = (double) emoticons[i] * 0.7;
                        saleList[i][1] = 30;                                     
                        break;
                    case 4:
                        saleList[i][0] = (double) emoticons[i] * 0.6;
                        saleList[i][1] = 40;
                        break;
                }
            }
            
            // System.out.println(Arrays.toString(saleList));
            
            check(saleList, users);
            return;
        }
        
        for(int i=1; i<=4;i++) {
            list[idx] = i;
            back(users, emoticons, idx+1, list);
        }
    }
    
    static void check(double[][] saleList, int[][] users) {
        int people = users.length;
        int emo = saleList.length;
        
                    
        int tmpCnt = 0;
        int tmpPrice = 0;
        
        for(int i=0; i<people;i++) {
            int minSale = users[i][0];
            int sum = 0;
            for(int j=0; j<emo;j++) {
                if(minSale <= saleList[j][1]) {
                    sum += saleList[j][0];
                }
            }

            
            if(sum >= users[i][1]) {
                tmpCnt++;
            }
            else {
                tmpPrice += sum;
            }
        }
        
        if(cnt <= tmpCnt) {
            if(cnt==tmpCnt) {
                price = Math.max(price, tmpPrice);
            }
            else {
                cnt = tmpCnt;
                price = tmpPrice;
            }
        }
        
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        int len = emoticons.length;
        back(users, emoticons, 0, new int[len]);
        
        int[] answer = {cnt, price};
        
        
        return answer;
    }
}