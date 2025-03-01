"""
호텔
비용, 고객 늘어나기

효율로 따지려고 했으나
다양한 경우의 수 발생으로 dp로 접근

어디까지가 시간초과 이슈 발생할지 판단하기
dp로 접근할 때 배열크기도 항상 생각하기

"""
c,n = map(int,input().split())

arr = []
dp = [1e9]*2001 # 고객 확보하는데 필요한 최소 비용(최소 20*1000)
# 비용과 고객수
for _ in range(n):
    arr.append(tuple(map(int,input().split())))
dp[0] = 0

for cost, value in arr:
    for i in range(value, 2001):
        dp[i] = min(dp[i], dp[i-value]+cost) # 이 옵션을 선택하거나, 선택하지 않았을 떄 비용 중 최소 선택
#print(dp)
print(min(dp[c:]))