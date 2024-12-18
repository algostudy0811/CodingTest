"""
석유시추
BFS 활용해서 시추관 설치 열 찾기
가장 많이 뽑을 수 있는 석유열 찾아서 접근
"""

from collections import deque


def bfs(row, col, land):
    n, m = len(land), len(land[0])
    visited_col = set()
    dy = [0, 0, 1, -1]
    dx = [1, -1, 0, 0]

    oil = 0
    q = deque([(row, col)])
    while len(q) != 0:
        y, x = q.popleft()
        if land[y][x] == 1:
            visited_col.add(x)
            oil += 1
            land[y][x] = 0
            for i in range(4):
                ny, nx = y + dy[i], x + dx[i]
                if 0 <= ny < n and 0 <= nx < m:
                    if land[ny][nx] == 1:
                        q.append((ny, nx))

    return land, oil, visited_col


def solution(land):
    n, m = len(land), len(land[0])
    total_oil = [0] * m
    for col in range(m):
        for row in range(n):
            if land[row][col] == 1:
                land, oil, visited_col = bfs(row, col, land)
                for i in visited_col:
                    total_oil[i] += oil

    return max(total_oil)