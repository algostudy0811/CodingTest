# 메모리 39196kb 시간 288ms

import sys
input = sys.stdin.readline

# 말 종류별 이동 로직

def queenmove(i, j):
    global safezone
    # 말 진행 경로
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]
    # 경로 별로 진행, 경로에 말이 있으면 진행 중지
    for direction in directions:
        x, y = i, j
        while True:
            x += direction[0]
            y += direction[1]
            if x < 0 or x >= n or y < 0 or y >= m or board[x][y] in ["Q", "K", "N"]:
                break
            if board[x][y] == 0:
                board[x][y] = -1
                safezone -= 1

def knightmove(i, j):
    global safezone
    # 나이트 진행 경로
    directions = [(-2, -1), (-2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2), (2, -1), (2, 1)]
    # 경로 별로 진행
    for direction in directions:
        x, y = i + direction[0], j + direction[1]
        if 0 <= x < n and 0 <= y < m and board[x][y] == 0:
            board[x][y] = -1
            safezone -= 1


if __name__ == "__main__" :
    n, m = map(int, input().split())
    board = [[0] * m for _ in range(n)]
    safezone = n * m
    leftpoint = 0

    # 말 배치
    pieces = ["Q", "K", "N"]
    for piece in pieces:
        ea, *position = map(int,input().split())
        leftpoint += ea
        for i in range(0, len(position), 2):
            board[position[i]-1][position[i+1]-1] = piece
            safezone -= 1

    # 탐색 시작, 남은 말이 0이면 탐색 중지
    # 말 위치에 도달하면 해당 말의 이동 로직 수행
    for i in range(n):
        if leftpoint == 0:
            break
        for j in range(m):
            if leftpoint == 0:
                break
            elif board[i][j] == 0:
                pass
            elif board[i][j] == "Q":
                queenmove(i,j)
                leftpoint -= 1
            elif board[i][j] == "K":
                knightmove(i,j)
                leftpoint -= 1
            # 폰은 그냥 장애물
            elif board[i][j] == "N":
                leftpoint -= 1

    # 결과 출력
    print(safezone)