"""
단방향 도로에서 왕복거리가 가장 오래걸리는 학생의 비용(시간) 계산하기
기존 다익스트라에서 start,end노드 따로 설정해주고,
1~n -- x 와 x -- 1~n 의 왕복거리를 더한 값의 최댓값을 출력하기


"""

import sys
import heapq

input = sys.stdin.readline

def dijkstra(start, end):
    heap = []
    distance = [1e9] * (n + 1)
    heapq.heappush(heap,[0, start]) # 시작노드로 가는 비용 0
    distance[start] = 0
    while heap:
        # heap으로 비용이 최대인 곳을 뽑아낸다.
        cost,now = heapq.heappop(heap)

        if distance[now] < cost: # 최소로 처리된 것이라면 무시
            continue

        for weight, node in arr[now]: # [도착도시,비용]
            tmp = cost + weight
            # 직선보다 우회가 더 큰 경우만 새로 갱신해주기
            if tmp < distance[node]:
                distance[node] = tmp
                heapq.heappush(heap,(tmp, node))

    return distance[end]

n,m,x = map(int,input().split())

arr = [[] for _ in range(n+1)]

for _ in range(m):
    i,j,c = map(int,input().split())
    arr[i].append([c,j])

ans = 0
for i in range(1,n+1):
    # 왕복거리 계산
    ans = max(ans, (dijkstra(i,x)+ dijkstra(x,i)))

print(ans)