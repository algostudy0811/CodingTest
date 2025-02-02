# 답이 되는 최솟값과 회전 후 배열 처리하기
# 2 2 5 4 -> 5-2(행)+1, 4-2+1
from collections import deque

def rotate(arr,r1,c1,r2,c2):
    border = deque([])
    # 직사각형
    # 윗줄
    border.extend(arr[r1][c1:c2+1])
    # 오른줄
    for r in range(r1+1,r2+1): # 윗줄빼고 오른줄이라 r1+1부터
        border.append(arr[r][c2])

    # 아랫줄(거꾸로 넣기), 우하단값빼고
    border.extend(arr[r2][c1:c2][::-1])
    # 왼쪽줄(하->상)
    for r in range(r2-1,r1,-1):
        border.append(arr[r][c1])

    result = min(border)

    border.rotate(1)

    #행렬 업데이트
    # 윗줄부터

    for c in range(c1,c2+1):
        arr[r1][c] = border.popleft()

    for r in range(r1+1,r2+1):
        arr[r][c2] = border.popleft()

    for c in range(c2-1,c1-1,-1):
        arr[r2][c] = border.popleft()

    for r in range(r2-1,r1,-1):
        arr[r][c1] = border.popleft()
    return arr,result


def solution(rows, columns, queries):
    # 초기 행렬
    arr = [[0]*(columns+1) for _ in range(rows+1)]
    answer = []
    cnt = 1
    for i in range(1,rows+1):
        for j in range(1,columns+1):
            arr[i][j] = cnt
            cnt += 1
    for q in queries:
        arr, result = rotate(arr,q[0],q[1],q[2],q[3])
        answer.append(result)
    return answer

solution(6,6,[[2,2,5,4],[3,3,6,6],[5,1,6,3]])
solution(3,3,[[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]])
solution(100,97,[[1,1,100,97]])