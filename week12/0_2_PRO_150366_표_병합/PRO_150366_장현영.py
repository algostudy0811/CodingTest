"""

오래걸렸던 포인트 : 연결 상태를 list 하나로 인덱스 지워가면서 관리했는데 도저히 찾을 수 없는 반례에 부딪침(11,13,14)
값 변경과 관련해서는 직관적인 for문 말고 while문+방문처리로 한번에 처리하는 것이 맞음

유니온파인드 대신
1. merge, unmerge인 경우 defaultdict로 관리
2. update는 defaultdict에서 visited 구분해서 모든 값 변경해주기


인접하지 않을 수도 있음: 사방탐색 불가
- 연결된 것을 list로 관리하기 -> 오래걸림
"""
from collections import defaultdict, deque


def solution(commands):

    def update_1(r, c, val):
        r, c = int(r), int(c)
        queue = deque([(r, c)])
        visited = []
        while queue:
            cr, cc = queue.pop()
            arr[cr][cc] = val
            visited.append((cr, cc))
            if (cr, cc) in connects:
                for nr, nc in connects[(cr, cc)]:
                    if (nr, nc) not in visited:
                        queue.append((nr, nc))

    def update_2(val1, val2):
        for i in range(1, 51):
            for j in range(1, 51):
                if arr[i][j] == val1:
                    update_1(i, j, val2) # 한 번에 다 바꾸면 이중 for문 빠르게 탈출

    def merge(r1, c1, r2, c2):
        r1, c1, r2, c2 = map(int, [r1, c1, r2, c2])
        if [r1, c1] == [r2, c2]:
            return
        # 이미 합병된 사이
        if (r1, c1) in connects[(r2, c2)] or (r2, c2) in connects[(r1, c1)]:
            return

        connects[(r1, c1)].append((r2, c2))
        connects[(r2, c2)].append((r1, c1))

        word = arr[r1][c1] if arr[r1][c1] else arr[r2][c2]  # else가 False일 수도 있음

        update_1(r1, c1, word)

    def unmerge(r, c):
        r, c = map(int, [r, c])
        visited = []
        queue = deque([(r, c)])
        val = arr[r][c]

        while queue:
            cr, cc = queue.pop()
            visited.append((cr, cc))
            arr[cr][cc] = False
            while connects[(cr, cc)]:
                nr, nc = connects[(cr, cc)].pop()
                if (nr, nc) not in visited:
                    queue.append((nr, nc))
        arr[r][c] = val

    answer = []
    arr = [[False] * 51 for _ in range(51)]
    connects = defaultdict(list)
    for command in commands:
        temp = command.split()
        comm = temp[0]
        if comm == "UPDATE":
            if len(temp) == 4:
                update_1(temp[1], temp[2], temp[3])
            else:
                update_2(temp[1], temp[2])
        elif comm == "MERGE":
            merge(temp[1], temp[2], temp[3], temp[4])
        elif comm == "UNMERGE":
            unmerge(temp[1], temp[2])
        else:
            answer.append(arr[int(temp[1])][int(temp[2])]) if arr[int(temp[1])][int(temp[2])] else answer.append(
                "EMPTY")
    return answer
