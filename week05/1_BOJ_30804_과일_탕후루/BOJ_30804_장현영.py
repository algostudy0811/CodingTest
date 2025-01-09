"""
set 이용해서 문제푸니까 시간초과 발생
defaultdict로 개수 세어서 문제 풀어보기
"""

import sys
from collections import defaultdict

input = sys.stdin.readline

n = int(input())
arr = list(map(int,input().split()))

p1,p2,cnt = 0,0,0
ans = 0
d = defaultdict(int) # 해시형태로 개수 뽑아내기
# 앞에서 부터 차례대로 진행함
# p2를 계속 더해나가면서 새로운 종류의 과일을 찾고
# 새 종류의 과일이 2개 이상이 되면 p1을 이동시키면서 값을 뺴기
# 투 포인터 개념 보고 다시 문제 풀이 진행
while p2< n:
    # 1단계 : p2를 처음부터 n까지 계속 반복시킨다.
    if d[arr[p2]] == 0: # 처음만나는 과일종류
        cnt += 1
    d[arr[p2]] += 1 # 과일 +1 진행

    # 과일이 3개 이상이 됐을 떄 이제 p2는 그만 전진
    # p1 전진 시작
    while cnt > 2:
        d[arr[p1]] -= 1
        if d[arr[p1]] == 0: # 전진했었을 때 1개였다면 괴일 개수 다시 빼기
            cnt -= 1
        p1 += 1
    ans = max(ans, p2-p1+1)
    p2 += 1

print(ans)