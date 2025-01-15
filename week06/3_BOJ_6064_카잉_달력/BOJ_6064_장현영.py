"""
카잉달력
10 12
1:1
2:2
...
10->10:10
11->1:11
12->2:12
13->3:1
입력 받아서 해당 값이 존재하는지 계산해서 체크하기

10 12 (2*5*6 최소공배수)
11 22 33 44 55 66 77 88 99 AA
1B 2C 31 42 53 64 75 86 97 A8
19 2A 3B 4C 51 62 73 84 95 A6
17 28 39 4A 5B 6C 71 82 93 A4
15 26 33 44 55 66 79 88 99 A2
13 24 35 46 57 68 79 8A 9B AC
-----------------------------
유클리드 알고리즘 최대공약수: https://blockdmask.tistory.com/53

풀이참고: https://chanmuzi.tistory.com/8
"""
import math
# 최대공약수 찾기
# a,b 최대공약수 = b, a%b의 최대공약수
# def gcd(m,n):
#     while n != 0:
#         m,n = n, m%n
#     return m

def num(m, n, x, y):
    while x <= m * n:  # 최대 범위
        if (x-y) % n == 0:  # 나머지로 확인
            return x
        x += m
    return -1


t = int(input())
for i in range(t):
    m, n, x, y = map(int, input().split())
    print(num(m, n, x, y))