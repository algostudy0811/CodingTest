#메모리 34536kb 시간 3896ms

import sys
input = sys.stdin.readline
import math

# 마지막 해는 두 주기의 최소공배수
# 주기가 반복적으로 적용된다
# 현재 해를 주기로 나눈 나머지만큼 더 진행

if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        M, N, x, y = map(int, input().split())
        # 나머지는 0을 포함하여 순환하므로 0 기반으로 조정
        x -= 1
        y -= 1
        year = x
        # 가능한 주기 범위 내에서 탐색
        while year < math.lcm(M, N):
            if year % N == y:
                print(year + 1)
                break
            # 이미 정해진 x값만을 탐색
            year += M
        # 불가능한 경우 분리
        else:
            print(-1)