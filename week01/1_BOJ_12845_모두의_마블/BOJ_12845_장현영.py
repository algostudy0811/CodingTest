"""
풀이 시간 : 25분
핵심: 합칠 떄 항상 레벨이 높은것에 합쳐서 계속 골드를 더해줄 수 있게 하자
ex) 50 40 50 20 10이면
- 순서가 반드시 존재함
- 무조건 큰 값과 값을 더해야함 그래야 level 상승
- 20+ 50 = 70 => 50 40 50(new) 10
- 10+50 = 60  => 50 40 50(new)
- 40+50 = 90 => 50(new) 50
- 50+50 = 100 => 50(new)
- 320이 최댓값
- 즉 10 20 40 50이 최댓값 50을 함께 더해지는 조건임
32412kb 32ms python 성공
"""
import sys

input = sys.stdin.readline

n = int(input())
L = list(map(int,input().split()))

# 가장 작은 레벨부터 탐색 진행하기
#
# max 값 찾고 한번에 더해주기
max_level = max(L) # O(n)

# 50 40 50 20 10
# 10 20 40 50 + 50*4
ans = sum(L) + max_level*(n-2)
print(ans)