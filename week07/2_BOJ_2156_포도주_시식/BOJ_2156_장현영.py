"""
포도주 시식
- 선택하면 마시기
- 연속 3잔 불가
- 가장 많은 포도주 먹기
n <= 10000
dp로 풀기
dp[i]를 i번째까지 봤을 떄 가장 많이 먹을 수 있는 포도주로 갱신시키기

dp[1] = arr[1]
dp[2] = arr[1]+arr[2]
3번째
- 마시지 않는다.
dp[3] => dp[2]
- 3번째 마신다, => 2번째는 안 마신다
dp[3] => dp[1]+arr[3]
- 3번째 마신다, => 첫번째는 안 마신다(그 전전까지의 dp값에서 안마신다)
dp[3] => dp[0]+arr[2]+arr[3]

dp[3] = max(dp[2], dp[1]+arr[3], dp[0]+arr[2]+arr[3])
dp[4] = max(dp[3], dp[2]+arr[4], dp[1]+arr[3]+arr[4])

"""

import sys

input = sys.stdin.readline

n = int(input())
arr = [0] # 0 idx 안씀
dp = [0]*(n+1) # 0 idx 안씀
for _ in range(n):
    arr.append(int(input()))

if n == 1:
    print(arr[1])
    exit(0)

dp[1] = arr[1]
dp[2] = arr[2]+dp[1]

for idx in range(3,n+1):
    dp[idx] = max(dp[idx-1], dp[idx-2]+arr[idx], dp[idx-3]+arr[idx-1]+arr[idx])

print(dp[n])