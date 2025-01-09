"""
화살 수가 정해진 상태에서
가장 큰 점수로 이겨야 하므로 최대한 어피치쏜 것 +1로 이겨야 함
점수를 계산해서 만약에 큰 숫자가 동일하면 낮은 거 더 많이 쏜 경우를 답


"""
from collections import deque


def bfs(n, info):
    res = []
    q = deque([(0, [0] * 11)])
    maxGap = 0
    while q:
        focus, arrow = q.popleft()
        if sum(arrow) == n:  # 종료조건 1) 화살 다 쏜 경우
            apeach, lion = 0, 0
            for i in range(11):
                if not (info[i] == 0 and arrow[i] == 0):
                    if info[i] >= arrow[i]:
                        apeach += 10 - i
                    else:
                        lion += 10 - i
            if apeach < lion:  # 라이언이 이기면
                gap = lion - apeach
                if maxGap > gap:
                    continue
                if maxGap < gap:
                    maxGap = gap  # 최대점수차 갱신
                    res.clear()
                res.append(arrow)  # 최대점수차를 내는 화살상황 저장

        elif sum(arrow) > n:  # 종료조건 2) 화살 더 쏜 경우
            continue

        elif focus == 10:  # 종료조건 3) 화살 덜 쏜 경우
            tmp = arrow.copy()
            tmp[focus] = n - sum(tmp)
            q.append((-1, tmp))

        else:  # 화살 쏘기
            tmp = arrow.copy()
            tmp[focus] = info[focus] + 1
            q.append((focus + 1, tmp))  # 어피치보다 1발 많이 쏘기
            tmp2 = arrow.copy()
            tmp2[focus] = 0
            q.append((focus + 1, tmp2))  # 0발 쏘기
    return res


def solution(n, info):
    winList = bfs(n, info)

    if not winList:
        return [-1]
    elif len(winList) == 1:
        return winList[0]
    else:
        return winList[-1]