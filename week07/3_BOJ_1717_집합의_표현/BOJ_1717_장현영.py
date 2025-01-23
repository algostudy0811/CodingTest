"""
유니온파인드 문제
합집합인 경우 두 원소를 합해주기(부모 설정)
원소가 속해있냐는 것은 부모확인
"""


import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n, m = map(int, input().split())

parent = [i for i in range(n+1)]

def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(i, j):
    i, j = find(i), find(j)
    parent[i] = j


for _ in range(m):
    flag, a, b = map(int,input().split())
    if flag == 0: # 합집합 형태
        union(a, b)
    else:
        if find(a) == find(b):
            print('YES')
        else:
            print('NO')