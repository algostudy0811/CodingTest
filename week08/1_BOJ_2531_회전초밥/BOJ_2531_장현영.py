"""
k접시 연속으로 먹으면 할인
특정 번호 쿠폰발급(무료1개)
최대한 다양한 번호를 먹기
"""


import sys

input = sys.stdin.readline

#접시수,가짓수,연속k,쿠폰
n,d,k,c = map(int,input().split())
arr = []
for _ in range(n):
    arr.append(int(input()))

ans = -1
for i in range(n): # 경우의 수
    sushi = set()
    flag = 1
    for j in range(k):
        curr = arr[(i+j)%n]
        if c == curr:
            flag = 0
        sushi.add(curr)
    ans = max(len(sushi)+flag, ans)

print(ans)