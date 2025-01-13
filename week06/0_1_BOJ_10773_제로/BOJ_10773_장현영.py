"""
stack 구조에서 o이되면 pop
stack의 총합 구하기
"""

import sys
from collections import deque

input = sys.stdin.readline

k = int(input())
stack = deque()
for _ in range(k):
    temp = int(input())
    stack.pop() if temp==0 else stack.append(temp)

print(sum(stack))