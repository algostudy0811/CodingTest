"""
틀린 코드
1. 다른 지점을 다 찾고, 그 지점들이 서로 연결됐는지 확인함
- arr1, arr2의 연결된 값들을 다 바꿔줘야하는데, 그러지 못한 코드
"""

import sys
from collections import deque

input = sys.stdin.readline

# 바뀌지 않거나 한 지점이 바뀌면 yes
# 두 지점이 바뀌면 no

# 다른 지점 모두 넣어서 인접한 곳인지 확인만 하면 됨
def bfs(spots):
    if not spots:
        return "YES"
    start = next(iter(spots)) # 아무 한 점
    visited = {start}
    queue = deque([start])

    while queue:
        r,c = queue.popleft()
        for idx in range(4):
            nr,nc = (r+dr[idx], c+dc[idx])
            if (nr,nc) in spots and (nr,nc) not in visited and arr1[nr][nc] == arr1[r][c]: # 인접하면서 값도 같아야 같은 영역
                visited.add((nr,nc))
                queue.append((nr,nc))

    return "YES" if len(visited) == len(spots) else "NO"






n,m = map(int,input().split())

arr1,arr2 = [],[]
spots = set()
dr = [1,0,-1,0]
dc = [0,1,0,-1]
for i in range(n):
    arr1.append(list(map(int,input().split())))

for i in range(n):
    arr2.append(list(map(int,input().split())))

for i in range(n):
    for j in range(m):
        if arr1[i][j] != arr2[i][j]:
            spots.add((i,j))


print(bfs(spots))