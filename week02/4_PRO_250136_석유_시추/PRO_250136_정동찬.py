from collections import deque

def solution(land):
    n = len(land) # 땅의 깊이
    m = len(land[0]) # 땅의 너비

    dr = [0, 1, 0, -1]
    dc = [1, 0, -1, 0]

    oil_dict = dict() # { count : cube }

    count = 1 # 석유 덩어리 수
    def bfs(r, c):

        Q = deque()
        Q.append([r, c])
        visited[r][c] = count

        cube = 1 # 석유 덩어리의 크기

        while Q:
            sr, sc = Q.popleft()
            for k in range(4):
                nr, nc = sr + dr[k], sc + dc[k]
                if nr < 0 or nr >= n or nc < 0 or nc >= m or not land[nr][nc] or visited[nr][nc]:
                    continue

                visited[nr][nc] = count
                cube += 1
                Q.append([nr, nc])

        oil_dict[count] = cube # oil_dict에 이번 석유 덩어리의 총 크기를 입력
        return 1 # bfs 호출 후 count를 더해 각 cube마다 다른 count를 부여해 구분

    visited = [[0 for _ in range(m)] for __ in range(n)] # 각 oil 덩어리마다 count를 이용해 번호로 구분시킬 것

    for c in range(m):
        for r in range(n):
            if land[r][c] and not visited[r][c]:
                count += bfs(r, c)

    answer = 0
    for c in range(m):
        oil_count = 0 # 시추관이 지나가는 oil 덩어리 총합
        oil_dict_check = 0 # 시추관이 c번째 column을 지날 때 몇 번째 oil 덩어리를 지나는가를 비트마스킹으로 기록

        for r in range(n):
            if visited[r][c] and not oil_dict_check & 1 << visited[r][c]:
                oil_dict_check |= 1 << visited[r][c]
                oil_count += oil_dict[visited[r][c]]

        answer = answer if answer > oil_count else oil_count

    return answer

# land = [[0, 0, 0, 1, 1, 1, 0, 0], [0, 0, 0, 0, 1, 1, 0, 0], [1, 1, 0, 0, 0, 1, 1, 0], [1, 1, 1, 0, 0, 0, 0, 0], [1, 1, 1, 0, 0, 0, 1, 1]]
# land = [[1, 0, 1, 0, 1, 1], [1, 0, 1, 0, 0, 0], [1, 0, 1, 0, 0, 1], [1, 0, 0, 1, 0, 0], [1, 0, 0, 1, 0, 1], [1, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1]]
# print(solution(land))