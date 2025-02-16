"""
s -> t
1. 문자열 뒤 A
2. 문자열 뒤 B하고 뒤집기
dfs
"""

import sys

input = sys.stdin.readline

def dfs(s,t):
    # T를 S로 맞춰나가기
    #종료조건
    if s==t:
        return 1
    if len(s) >= len(t):
        return 0 # 불가능상태

    ans = 0
    if t[-1] == 'A': # 마지막 A면 그냥 제거
        ans = dfs(s,t[:-1])
    if ans == 1: # 종료 조건
        return 1
    if t[0] == 'B': # 맨처음이 B면 뒤집고 하나 제거
        ans = dfs(s,t[::-1][:-1])
    return ans

s = list(input().strip())
t = list(input().strip())
print(dfs(s,t))