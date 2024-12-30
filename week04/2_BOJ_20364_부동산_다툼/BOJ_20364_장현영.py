"""
부동산 다툼
더이상 오리 집어넣을 수 없을 때 해당 땅번호 출력
이진트리
오리가 해당 땅에갈 수 있는지ㅍ없는지 확인하기
입출력 시간 빠르게 처리하기!!
sys.stdin.readline
"""

import sys

input = sys.stdin.readline

n, q = map(int, input().split())
occupied = [0] * (n+1)  # 점유 상태를 나타내는 리스트

for _ in range(q):
    duck_land = int(input())  # 오리 땅 번호
    current = duck_land
    conflict_land = 0  # 점유 땅
    flag = False
    # 루트까지 탐색하며 점유 상태 확인
    while current > 0:
        if occupied[current]:
            flag = True
            conflict_land = current  # 점유 중인 땅 업데이트
        if current%2: # 홀수
            current -= 1
        current //= 2

    if flag:
        print(conflict_land)
    else:
        # 점유 중인 땅이 없다면 점유 처리
        occupied[duck_land] = True
        print(0)