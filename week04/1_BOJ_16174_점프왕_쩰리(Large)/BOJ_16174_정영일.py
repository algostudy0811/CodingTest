import sys
input = sys.stdin.readline

# BFS로 풀기
def jump_check():
    # 이동은 오른쪽과 아래쪽
    dx = [1, 0]
    dy = [0, 1]
    # 방문처리, 큐
    visited = [[False] * N for _ in range(N)]
    visited[0][0] = True
    queue = [(0, 0)]
    while queue :
        x, y = queue.pop()
        for i in range(2) :
            nx = x + int(board[x][y]) * dx[i]
            ny = y + int(board[x][y]) * dy[i]
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] :
                if board[nx][ny] == "-1" :
                    return True
                visited[nx][ny] = True
                queue.append((nx, ny))
    return False

if __name__ == "__main__" :
    N = int(input())
    board = [input().split() for _ in range(N)]
    if jump_check() :
        print("HaruHaru")
    else :
        print("Hing")