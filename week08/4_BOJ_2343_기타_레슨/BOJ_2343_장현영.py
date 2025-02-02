"""
각각 담기는 블루레이는 무조건 연속 순서대로!
nums에 담기는 숫자는 순서가 아닌 시간(분)!(정렬된 것 아님)

블루레이 최소 크기 :원소 1개 중 최댓값, 최대 크기 sum(nums): m=1일때
(max(nums) <= ans <= sum(nums))

왜 이분탐색인가?

블루레이의 개수가 많으면 ans를 늘리고, 개수가 적으면 ans를 줄이기
"뭔가 기준점이 있어야 하고 그 기준점은 조건에 따라 달라질 수 있다. 그렇다면 이분 탐색이겠구나"

## mid 기준 답이 가능한 값들중 최솟값 구하기 위해 (왼쪽, 오른쪽)으로 반씩 잘라서 생각하기

test case 기준 9 <= ans <= 45로 답 찾기 진행하기

"""

import sys

input = sys.stdin.readline

n,m = map(int,input().split())
nums = list(map(int,input().split()))
low, high = max(nums), sum(nums)

# 이진탐색 시작
while low <= high:
    mid = (low + high) // 2
    blueray_cnt = 0 # 블루레이 크기
    each_sum = 0

    # for문이 가능한 이유: 연속으로 더해지기 때문!
    for i in range(n):
        if each_sum + nums[i] > mid: # mid보다 크면 새로운 블루레이 cnt+=1
            each_sum = 0  # 새로운 블루레이
            blueray_cnt += 1
        each_sum += nums[i]

    blueray_cnt += 1 if each_sum else 0

    if blueray_cnt <= m: # 개수가 m보다 작거나 같으면 한계를 낮춘다.
        high = mid-1
    else:
        low = mid+1        # 개수가 m보다 크면 한계를 높인다.

print(low) # 최소 크기 low 출력