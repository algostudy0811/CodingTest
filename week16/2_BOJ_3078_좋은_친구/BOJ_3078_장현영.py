"""
좋은 친구
K이하로 인덱스 차이나면서 이름길이 같은 좋은친구 찾기
이름길이 같은 애들끼리 먼저 묶어보고
K 이하면 개수 세보기
"""
import sys
from collections import defaultdict

input = sys.stdin.readline

n,k = map(int,input().split())
d = defaultdict(list)
for i in range(1,n+1):
    name = input().strip()
    d[len(name)].append(i)

cnt = 0
# k 이하의 인덱스 찾기
# 투포인터로 차이 확인
for key in d:
    d[key].sort()
    r = 0
    for l in range(len(d[key])):
        while r < len(d[key]) and d[key][r] - d[key][l] <= k:
            r += 1
        cnt += (r-l) -1
print(cnt)
