"""
모두가 연결된 상태에서 개수를 센 다음에 가장 중간 지점의 엣지를 제거해서 나오는 것의 차이
리스트 해시맵으로 연결상태 보이기
"""

from collections import deque


def bfs(start, d, n):
    visited = [0] * (n + 1)
    queue = deque([start])
    visited[start] = 1
    cnt = 1
    while queue:
        curr = queue.popleft()
        for next in d[curr]:
            if not visited[next]:
                visited[next] = 1
                queue.append(next)
                cnt += 1
    return cnt


def solution(n, wires):
    ans = 101

    # 초기 세팅
    d = {i: [] for i in range(1, n + 1)}
    for a, b in wires:
        d[a].append(b)
        d[b].append(a)

    # 하나씩 제거 후 bfs
    for a, b in wires:
        d[a].remove(b)
        d[b].remove(a)

        cnt1 = bfs(a, d, n)  # 한 번만
        cnt2 = n - cnt1
        ans = min(ans, abs(cnt1 - cnt2))

        d[a].append(b)
        d[b].append(a)

    return ans