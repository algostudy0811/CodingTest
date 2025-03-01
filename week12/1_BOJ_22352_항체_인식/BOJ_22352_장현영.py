"""
아이디어 :
1. arr1에서 arr2와 다른 점 찾는다.
2. 그 점 bfs 돌려서 arr1값으로 arr2를 변경(1회만)
3. bfs가 끝난 뒤 arr1과 arr2가 다르면 주사 2방 놓은 것
헤맸던 포인트: arr1의 같은 영역을 arr2값으로 바꿔야지 arr2에 arr1로 바꾸면 arr2 영역에서 arr1의 영역을 다 바꾸지 못함
"""

import sys
from collections import deque

input = sys.stdin.readline

# arr1를 arr2에 맞춰서 바꾸기 # arr2를 arr1에 바꾸면 arr1 내 같은 영역 확인을 못함
def bfs(spot):
    visited = [spot]
    queue = deque([spot])
    arr1_val = arr1[spot[0]][spot[1]]
    arr2_val = arr2[spot[0]][spot[1]] # 새로운 값으로 arr1을 바꿔주자
    arr1[spot[0]][spot[1]] = arr2_val
    while queue:
        r,c = queue.popleft()
        for idx in range(4):
            nr,nc = r+dr[idx], c+dc[idx]
            if 0<=nr<n and 0<=nc<m and (nr,nc) not in visited:
                if arr1[nr][nc] == arr1_val: # 기존 영역 찾기
                    arr1[nr][nc] = arr2_val # arr2 새로운 값으로 모두 바꿔주기
                    visited.append((nr,nc))
                    queue.append((nr,nc))




n,m = map(int,input().split())

arr1,arr2 = [],[]
dr = [1,0,-1,0]
dc = [0,1,0,-1]

for i in range(n):
    arr1.append(list(map(int,input().split())))

for i in range(n):
    arr2.append(list(map(int,input().split())))

cnt = 0
start = None
for i in range(n):
    for j in range(m):
        if arr1[i][j] != arr2[i][j]:
            start = (i,j)
            bfs((i,j)) # 한 번만
            break
    if start:
        break

print("YES") if arr1==arr2 else print("NO")