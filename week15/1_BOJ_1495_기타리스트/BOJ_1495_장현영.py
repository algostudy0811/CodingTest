"""
dp[i][j] -> i번째 곡을 j 볼륨으로 연주가능여부

"""
n,s,m=map(int,input().split())
volume=list(map(int,input().split()))
dp=[[0]*(m+1) for _ in range(n+1)]
dp[0][s]=1

for i in range(1,n+1):
    for j in range(m+1):
        if dp[i-1][j]>0: # 연주 가능
            # 이전 곡 연주여부 체크헤서 확인
            if 0<=j+volume[i-1]<=m:
                dp[i][j+volume[i-1]]=1
            if 0<=j-volume[i-1]<=m:
                dp[i][j-volume[i-1]]=1

answer=-1
for i in range(m+1):
    if dp[n][i]==1:
        answer=max(answer,i)

print(answer)