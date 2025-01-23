"""
냅색문제

K까지 들어가는 배낭에 W를 넣는데 V를 최대화하기
물건을 쪼갤 순 없음
3 4  5  6 => 7
6 8 12 13
"""

import sys

input = sys.stdin.readline

n, k = map(int,input().split())
worth = -1
items = [(0,0)] # 해당 무게일 떄 최대 가치
dp = [[0] * (k+1) for _ in range(n+1)] # 해당 무게일 떄 최대 가치
for _ in range(n):
    w, v = map(int,input().split())
    items.append([w,v])

# 만약 물건 4개, 최대 가방용량 10kg이면 40번 확인하기
# 물건 8개에 가방 용량 최대 15kg이면 120번 확인하기
for i in range(1, n+1): # 물건 개수
    for j in range(1, k+1): # 가방 용량 1~k까지 확인해서 item 넣을 수 있는지 판단
        weight,worth = items[i][0],items[i][1]
        # 지금 물건이 가방에 들어갈 수 없다(지금 물건이 더 크다)
        if j < weight:
            dp[i][j] = dp[i-1][j] # 물건 안 넣은 것과 동일
        else: # 가치비교하기: 안 넣은 것 가치([i-1][j])와 지금 물건 넣기 전 가치([i-1][j-weight] + 현 가치) 비교하기
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight]+worth)

print(dp[n][k])