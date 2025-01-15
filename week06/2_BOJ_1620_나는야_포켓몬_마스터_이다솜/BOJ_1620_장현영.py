"""
사전형 문제
시간초과 해결: 매번 value를 key로 찾는것보다 역사전을 만들어서 하기
51528kb 268ms
"""

import sys

input = sys.stdin.readline

n,m = map(int,input().split())

# 포켓몬이름
d = dict()
reverse_d = dict()
for i in range(1,n+1):
    pocketmon = input().strip("\n")
    d[i] = pocketmon
    reverse_d[pocketmon] = i

for _ in range(m):
    input_name = input().strip()
    # 숫자와 문자 구분하기
    try:
        num = int(input_name)
        print(d[num])
    except ValueError:
        # 미리 만들어놓은 새로운 key-value 뒤집은 값을 참고
        print(reverse_d[input_name])
        # for k, v in d.items():
        #     if v == input_name:
        #         print(k)
