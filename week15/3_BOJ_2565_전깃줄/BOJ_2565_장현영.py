"""
전깃줄
최소 전깃줄 제거 = 최대 전깃줄 놔두기
LIS 만들기
"""

l = int(input())
arr = []

for _ in range(l):
    a,b = map(int,input().split())
    arr.append((a,b))

arr.sort()

ans = [[] for _ in range(l)]
for i in range(l):
    if i == 0:
        ans[i].append(arr[i][1])
    else:
        for j in range(i):
            if ans[j][-1] < arr[i][1]:
                if len(ans[i])-1 < len(ans[j]):
                    ans[i] = ans[j] + [arr[i][1]]
        if not ans[i]:
            ans[i].append(arr[i][1])

val = 0
for i in range(l):
    val = max(val, len(ans[i]))
print(l-val)