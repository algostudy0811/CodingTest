"""
과일 탕후루
"""

import sys

input = sys.stdin.readline

n = int(input())
arr = list(map(int,input().split()))

ans = n
# 종류가 2개 이하일 때 까지 움직인다
# 포인터의 모든 조합 중 가장 적게 움직이는 값을 뽑아내기


# 슬라이싱 이용하기
# 2개 이하로 나올때만 움직인 값 출력
# 2개 이하가 아닌 경우는 -1 출력
def under_two(pt1,pt2):
    new_kinds = len(set(arr[pt1:n-pt2]))
    print(new_kinds, pt1,pt2)
    return pt1+pt2 if new_kinds <=2 else n

# i+j가 n이 될 순 없음
for i in range(n): # pt1
    if i > ans:
        break
    for j in range(n): # pt2
        if i+j >= ans:
            break
        print([i,j])
        ans = min(ans, under_two(i,j))

print(n-ans)