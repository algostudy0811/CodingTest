# 좌표압축
# 현재 값보다 작은 값의 개수를 출력한다.

import sys

input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

sorted_arr = sorted(set(arr))

d = {} # 순서 저장
for i,value in enumerate(sorted_arr):
    d[value] = i

for i in range(n):
    print(d[arr[i]], end=' ')