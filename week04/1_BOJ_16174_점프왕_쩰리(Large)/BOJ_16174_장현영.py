'''
점프왕 젤리
우측,아랫방향만 이동
딱 그 칸수만큼 이동하기
HaruHaru
Hing
만약 칸이 2 이상인데
우측,아랫방향 섞어서는 이동 불가
즉, ㄱ,ㄴ자로는 이동 안됨
'''
from collections import deque

n = int(input())
arr = []
for i in range(n):
    arr.append(list(map(int,input().split())))

# bfs
queue = deque()
dr = [0,1] # 우, 하
dc = [1,0]
queue.append((0,0))
visited = [[0]*n for _ in range(n)]
visited[0][0] = 1 # true

while queue:
    r,c = queue.popleft()
    score = arr[r][c]
    if arr[r][c] == -1:
        print("HaruHaru")
        exit(0)
    for i in range(2):
        nr, nc = r + dr[i]*score, c + dc[i]*score
        if 0<=nr<n and 0<=nc<n and not visited[nr][nc]:
            queue.append((nr,nc))
            visited[nr][nc] = 1
print("Hing")