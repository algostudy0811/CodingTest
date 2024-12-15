"""
A를 B번 곱한 수를 C로 나눈 나머지를 구하기
EX) 16을 2번 곱한 수> 256을 7로 나눈 나머지 구하기

코드 참고
분할정복
10^11(11번보다)
(10^5)^2*10(5+1+1 = 7번)
((10^2)^2*10)^2*10 (2+1+1+1+1= 5번)
"""

import sys

input = sys.stdin.readline
def solution(a,b,c):
    if b == 1: # 거듭제곱 없이 바로 a의 나머지 리턴
        return a%c
    else:
        k = solution(a, b//2, c)
        if b%2: # b가 홀수면 a한번 더해주기
            return (k*k*a)%c
        else:
            return (k*k)%c
a,b,c = map(int, input().split())
print(solution(a,b,c))


