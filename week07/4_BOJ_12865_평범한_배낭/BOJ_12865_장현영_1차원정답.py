"""
2차원 dp: 228328kb 5092ms
1차원 dp: 36264kb  2412ms
"""
import sys

input = sys.stdin.readline

n,k = map(int, input().split())
dp = [0] * (k+1) # 해당 무게 용량에서 넣을 수 있는 최대 가치

for _ in range(n):
    weight, value = map(int, input().split())
    # 역으로 추적하면서 안 넣었을 떄와 현재 물건 넣었을 때 기존 가치 더한 값 최대 찾기
    for i in range(k, weight-1, -1): # 역순으로 줄여나가기
        dp[i] = max(dp[i], dp[i-weight] + value)
print(dp[-1])
