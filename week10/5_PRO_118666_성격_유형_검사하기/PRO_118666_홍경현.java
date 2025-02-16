package STUDY.week10;

/*
[문제 해석]
라이언형 R, 튜브형 T
콘형 C, 프로도형 F
제이지형 J, 무지형 M
어피치형 A, 네오형 N
-> 총 16가지 유형

n개의 질문, 7개의 선택지 
- 매우 동의/비동의 3점
- 동의/비동의 2점
- 약간 동의/비동의 1점

모든 질문의 성격 유형 점수를 더하여 각 지표에서 더 높은 점수를 받은 성격 유형으로 판단
단, 점수가 같으면 사전 순으로 빠른 성격 유형 

질문 판단 지표 문자열 배열 survey
선택한 선택지를 담은 1차원 정수 배열 choices

[제한사항]
1. 1<=survey 길이(n)<=1,000
    - RT, TR, FC, CF, MJ, JM, AN, NA
    - survey[i]의 첫번째 캐릭터는 i+1번 질문의 비동의
    - survey[i]의 두번째 캐릭터는 i+1번 질문의 동의
2. choices의 길이 = servey의 길이
    - choices[i] 는 검사자가 선택한 i+1번째 질문의 선택지
    - 1<=chioces[i]<=7

[문제 해결 프로세스]
int arr['Z']
int score[8] = {0, 3, 2, 1, 0, 1, 2, 3}

1. choices[i] > 4 : arr[survey[i].charAt(0)] += choices[i];
2. choices[i] < 4 : arr[survey[i].charAt(1)] += choices[i];
*/
class PRO_118666_홍경현 {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        int[] arr = new int['Z'];
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        
        int len = survey.length;
        for(int i=0; i<survey.length; i++){
            if(choices[i] < 4) arr[survey[i].charAt(0)] += score[choices[i]];
            else arr[survey[i].charAt(1)] += score[choices[i]];
        }
        
        char[] kakao = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        
        for(int i=0; i<7; i+=2){
            if(arr[kakao[i]] >= arr[kakao[i+1]]) answer += kakao[i];
            else answer += kakao[i+1];
        }
        return answer;
    }
}