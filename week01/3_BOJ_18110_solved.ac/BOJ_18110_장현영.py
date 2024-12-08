"""
절사평균: 앞뒤 % 단위로 자르기
소수값이 나오면 첫째자리에서 반올림하여 정수로
믿을 수 없는 round함수..
https://blockdmask.tistory.com/418
만약 음수를 사용해야된다면
round(-2.5) = -2 일텐데
양수,음수인 경우로 조건 분기해서 -0.5, + 0.5로 처리
그렇다면 round는 어떨 떄 쓸지 고민해봐야됨.
"""

import sys
import heapq
input = sys.stdin.readline
n = int(input())
if n == 0:
    print(0)
    exit(0)

min_heap = []
max_heap = []
ans = 0
# round(0.5) = 0
# round(2.5) = 2
# round(4.5) = 4 ...
# round는 동일한 값만큼 떨어져 있을 떈 짝수를 더 선호한다.
iqr_15 = int((n*0.15)+0.5) # 음수가 없어서 0.5 더한뒤 정수형으로 변환
print(iqr_15)
for _ in range(n):
    k = int(input())
    heapq.heappush(min_heap, k)
    heapq.heappush(max_heap, -k)
    ans += k

# 절삭평균
for _ in range(iqr_15):
    ans -= heapq.heappop(min_heap)
    ans += heapq.heappop(max_heap)

print(int((ans/(n-iqr_15*2))+ 0.5))
