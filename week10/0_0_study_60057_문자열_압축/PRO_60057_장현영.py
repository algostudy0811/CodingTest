"""
1트: n개 단위면 앞에서 n개씩 range 이용해 split
연속으로 동일한 것이 나오면
전체 길이 -(n-1), 3개이상 동일하면 -n 씩 계산해주기
 다른 문자열, 9->10, 99->100, 999->1000 등 cnt도 세줘야 함
채점 결과 : 72 / 100

2개 이상일 때 정수 붙이기
aabbaccc (8개)
2a2b1a3c => 7개

2ab2cd2ab2cd => 2ababcdcd
abcabcdede = 2abcdede
# 다른 문자열, 9->10, 99->100, 999->1000 등 cnt도 세줘야 함
"""

def solution(s):
    # 1~ val//2 만큼 접근하기, 반 이상부터는 줄어들지 않음
    val = len(s)
    result = []
    ans = val
    for n in range(1, val//2+1):
        cnt = 1 # 연속되는 개수
        temp_str = ""
        check = s[:n]
        for i in range(n,val,n):
            curr = s[i:i+n]
            if curr == check:
                cnt += 1
            else:
                temp_str += str(cnt) + check if cnt > 1 else check
                check = curr
                cnt = 1
        #나머지
        temp_str += str(cnt) + check if cnt > 1 else check
        ans = min(ans, len(temp_str))
    return ans