"""
부동소수점!
나눗셈에 문제발생
반드시 곱 연산 후 나누기
"""

import sys
from collections import deque

input = sys.stdin.readline

queue = deque()

n = int(input())
if n == 0:
    print(0)
    exit(0)
cutting_num = int(n*0.15+0.5)
arr = []
ans = 0
for _ in range(n):
    temp = int(input())
    ans += temp
    arr.append(temp)
arr.sort()

# print("ans",ans)
# print(cutting_num)
for i in range(cutting_num):
    ans -= arr[i]
    ans -= arr[-(i+1)]
    # print(arr[i],"",arr[-i])

# print("after ans")
print(int(ans/(n-2*cutting_num)+0.5))
