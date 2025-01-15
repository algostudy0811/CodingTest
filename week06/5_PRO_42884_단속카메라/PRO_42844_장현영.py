"""
끝점 기준으로 최소 heap 사용
카메라를 구간 끝점 설치하는 방식으로 접근하기
"""
import heapq


def solution(routes):
    # 끝점 기준 정렬 (end, start) 형태로 변환
    heap = [(end, start) for start, end in routes]
    heapq.heapify(heap)

    answer = 0
    idx = -30001

    while heap:
        end, start = heapq.heappop(heap)
        if start <= idx:
            continue  # 커버 가능
        idx = end  # 새 카메라 구간 끝점 설치
        answer += 1

    return answer
