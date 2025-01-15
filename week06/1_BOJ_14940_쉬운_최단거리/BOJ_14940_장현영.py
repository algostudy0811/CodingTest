"""
모든 좌표의 목표 지점까지 거리 구하기
"""

import sys
from collections import deque

input = sys.stdin.readline


def bfs(sr,sc):
    queue = deque()
    queue.append((sr,sc))
    dist = [[-1]*m for _ in range(n)]
    dist[sr][sc] = 0
    while queue:
        r,c = queue.popleft()
        for idx in range(4):
            nr = r+dr[idx]
            nc = c+dc[idx]
            if 0 <= nr < n and 0 <= nc < m and arr[nr][nc] == 1 and dist[nr][nc] == -1:
                queue.append((nr,nc))
                dist[nr][nc] = dist[r][c] + 1
    return dist

dr = [0,1,0,-1]
dc = [1,0,-1,0]
n,m = map(int,input().split())
arr = []
for i in range(n):
    temp = list(map(int, input().split()))
    arr.append(temp)
    for j in range(m):
        if temp[j] == 2:
            sr, sc = i, j

ans = bfs(sr,sc)

for i in range(n):
    for j in range(m):
        if arr[i][j] == 0:
            print(0, end=" ")
        else:
            print(ans[i][j], end=" ")
    print()


