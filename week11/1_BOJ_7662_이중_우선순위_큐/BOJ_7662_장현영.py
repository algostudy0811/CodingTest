"""
정수값 자체가 우선순위
삭제: 우선순위에 따라서 결정(D 1최댓값, D -1최솟값)
"""

import sys
import heapq

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    k = int(input())
    min_heap, max_heap = [],[]
    visited = [0]*1000001 # 핵심
    for idx in range(k):
        calc, num = map(str,input().split())
        n = int(num)
        if calc == 'I':
            heapq.heappush(min_heap,(n,idx))
            heapq.heappush(max_heap,(-n,idx))
            visited[idx] = 1
        else:
            if len(min_heap)*len(max_heap) == 0:
                continue
            if n == 1: # 최댓값 삭제
                while max_heap and not visited[max_heap[0][1]]: # 최소힙에서 삭제된 거라면 먼저 삭제처리
                    heapq.heappop(max_heap)
                if max_heap:
                    visited[max_heap[0][1]] = 0
                    heapq.heappop(max_heap)
            else:
                while min_heap and not visited[min_heap[0][1]]: # 최소힙에서 삭제된 거라면 먼저 삭제처리
                    heapq.heappop(min_heap)
                if min_heap:
                    visited[min_heap[0][1]] = 0
                    heapq.heappop(min_heap)
    # 동기화: 유효하지 않은 값 정리
    while min_heap and not visited[min_heap[0][1]]:
        heapq.heappop(min_heap)
    while max_heap and not visited[max_heap[0][1]]:
        heapq.heappop(max_heap)

    if not min_heap or not max_heap: # 비어있으면 EMPTY
        print("EMPTY")
    else: #
        print(max_heap, min_heap)
        print(f"{-heapq.heappop(max_heap)[0]} {heapq.heappop(min_heap)[0]}")
