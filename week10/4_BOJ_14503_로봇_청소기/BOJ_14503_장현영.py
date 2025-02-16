"""
조건
a. 왼쪽 청소안한 공간쪽으로 회전하고 한칸 전진 후 현 위치 청소
b. 왼쪽 청소할 공간 x 그 방향으로 회전후 현방향에서 4방향 다시
c. 벽이나 청소가 되어있으면 방향 유지한 채 한찬 후진 후 4방향 또 찾기
d. c에서 후진도 안되는 상황이면 작동 끝
"""

import sys

input = sys.stdin.readline

dr = [-1, 0, 1, 0]  # 북,동,남,서
dc = [0, 1, 0, -1]  # 왼쪽:   서,북,동,남

# 0-3,1-0,2-1,3-2

def turn_left(d):
    if d == 0:
        left_d = 3
    else:
        left_d = d - 1
    return left_d


# 청소기 가동시 그 좌표 2로 설정
# 벽은 1, 청소가능구역 0
def play(board, r, c, ld):
    cnt = 0
    cleaner = []
    flag = True
    while 1:
        # 1번
        board[r][c] = 2
        cleaner.append((r, c))
        # 2번
        ld = turn_left(ld)
        nr = r + dr[ld]
        nc = c + dc[ld]
        # A
        if board[nr][nc] == 0:
            cnt = 0
            r, c = nr, nc
        else:  # B
            cnt += 1
            if cnt == 4:
                back_r = r + dr[ld - 2]
                back_c = c + dc[ld - 2]
                # C
                if board[back_r][back_c] != 1:  # 바라보는 방향을 유지한 채로 한칸후진.
                    cnt = 0
                    r, c = back_r, back_c
                else:  # D
                    break

    cleaner = set(cleaner)  # 중복제거
    return len(cleaner)


board = []
# 세로, 가로
n, m = map(int, input().split())
curr_r, curr_c, d = map(int, input().split())
for _ in range(n):
    board.append(list(map(int, input().split())))

print(play(board, curr_r, curr_c, d))
