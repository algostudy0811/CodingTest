# 메모리 32412kb 시간 36ms

import sys
input = sys.stdin.readline

#-------------------------------------------------------------------------------
def times(A,B,C) :
    # 나머지가 반복될것이라 판단
    # 3의 거듭제곱의 경우 7로 나눴을때 나머지가 3,2,6,4,5,1로 반복됨

    # 예제 1의 경우 10, 4, 4, 4, 4 와 같이 반복됨...
    # 반복 사이클을 재는 예외처리를 해야할 듯 -> 해도 시간 초과..

    # 나머리 리스트를 딕셔너리로 해서 키값을 조회했는데도 시간초과...

    # 나머지 리스트
    D_list = {}
    now = 0
    num = A
    while True :
        # 진행된 횟수 기록
        now += 1
        # 현재 A를 C로 나눈 나머지
        new_D = num % C
        # 반복없이 끝까지 진행되었다면 나머지 반환
        if now >= B:
            return new_D
        # 나머지 리스트에 있다면 반복 체크
        if new_D in D_list :
            repeat = []
            check = now
            # 현재 진행한 만큼 다시 진행해봄 : 사이클 크기 측정
            for _ in range(check):
                new_D = num % C
                # 만약 다시 반복이 발견됨 : 사이클 크기 나옴
                if new_D in repeat :
                    # 현재 진행 : now에 저장됨. 결과는 B와 now의 차이만큼 더 진행해야함
                    return repeat[(B-now)%len(repeat)]
                repeat.append(new_D)
                num = num * A
                now += 1
        # 겹치지 않으면 리스트에 추가, A값 갱신
        D_list.update({new_D: True})
        num = num * A
# -------------------------------------------------------------------
# 거듭제곱의 성질로 파악
# 지수가 짝수면 반으로 나누고 제곱할 수 있음
# 홀수면 하나를 빼고 곱하기로 나타낼 수 있음
# 주어진 수를 계속 줄여서 표현...

def square_times(A,B,C) :
    # 1은 바로 반환
    if B == 1 :
        return A % C

    # 재귀로 절반씩 나눔. 홀수의 경우 나중에 추가로 곱해 보정
    # 전부 빠져나옴 : 1이라는 최소에 도달 -> 값을 계산
    half = square_times(A, B//2, C)
    half = (half ** 2) % C

    # 지수 홀수 보정
    if B % 2 == 1 :
        half = (half * A) % C
    
    return half


if __name__ == "__main__" :
    A, B, C = map(int, input().split())
    print(times(A,B,C))
    # print(square_times(A,B,C))