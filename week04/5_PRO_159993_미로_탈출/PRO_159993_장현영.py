"""
미로탈출
BFS로 최단거리 찾기
"""

from collections import deque

def bfs(start, end, maps, rows, cols):
    dr = [0, 1, 0, -1]
    dc = [1, 0, -1, 0]
    queue = deque([start])
    visited = [[0] * cols for _ in range(rows)]
    visited[start[0]][start[1]] = 1
    distance = 0

    while queue:
        for _ in range(len(queue)):
            r, c = queue.popleft()
            if (r, c) == end:
                return distance
            for i in range(4):
                nr, nc = r + dr[i], c + dc[i]
                if 0 <= nr < rows and 0 <= nc < cols and not visited[nr][nc] and maps[nr][nc] != 'X':
                    visited[nr][nc] = 1
                    queue.append((nr, nc))
        distance += 1

    return -1  # 도달할 수 없는 경우


def solution(maps):
    rows, cols = len(maps), len(maps[0])
    start, lever, end = None, None, None

    # 위치 찾기
    for r in range(rows):
        for c in range(cols):
            if maps[r][c] == 'S':
                start = (r, c)
            elif maps[r][c] == 'L':
                lever = (r, c)
            elif maps[r][c] == 'E':
                end = (r, c)

    # BFS로 거리 계산
    dist_to_lever = bfs(start, lever, maps, rows, cols)
    if dist_to_lever == -1: # 레버에 도달 x
        return -1

    dist_to_exit = bfs(lever, end, maps, rows, cols)
    if dist_to_exit == -1:
        return -1  # 출구에 도달 x

    return dist_to_lever + dist_to_exit