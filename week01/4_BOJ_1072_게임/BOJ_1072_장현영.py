import sys

"""
게임
앞으로 모든 게임에 이길 건데,
승률이 바뀔 시점(정수값이 상승할 순간)을 찾기
바뀌지 않을 가능성: 이미 100%인 경우
"""
input = sys.stdin.readline

x,y = map(int, input().split())
# 정수값이 바뀔 경우를 계산하자
win_percent = int(y/x * 100)
if win_percent >= 99:
    print(-1)
    exit(0)
# 당연히 시간 초과 1트 x,y가 10^9
# while 1:
#     new_percent = int((y+ans)/(x+ans) * 100)
#     if win_percent < new_percent:
#         print(ans)
#         break
#     ans += 1
# 1부터 차근차근이 아니라 10^n 단위로 범위를 좁혀서 더해나가기
# 자릿수 찾기 시간초과 2트
# idx = -1
# for i in range(1,10):
#     temp = 10**i
#     new_percent = (y+temp)/(x+temp) * 100
#     if win_percent+1 <= new_percent:
#         idx = i
#         break
#
# for ans in range(10**(idx-1), 10**idx+1):
#     new_percent = (y + ans) / (x + ans) * 100
#     if win_percent+1 <= new_percent:
#         print(ans)
#         break

# 100 단위 계산
# idx = -1
# for temp in range(0,int(1e9),100):
#     new_percent = (y+temp)/(x+temp) * 100
#     if win_percent+1 <= new_percent:
#         idx = temp
#         break
# for ans in range(idx-100, idx+1):
#     new_percent = (y + ans) / (x + ans) * 100
#     if win_percent+1 <= new_percent:
#         print(ans)
#         break

# 범위 전체로 해서 이분 탐색
start, end = 1, 1000000000
while start < end:
    mid = (start + end) // 2

    # x, y에 mid를 각각 더해서 퍼센트를 구하고,
    # 처음 x, y의 퍼센트와 비교한다.
    cur = int((y+mid)*100/(x+mid))
    if cur <= win_percent:
        start = mid+1
    else:
        end = mid
if int((y+end)/(x+end)*100) > win_percent:
    print(end)
else:
    print(-1)
