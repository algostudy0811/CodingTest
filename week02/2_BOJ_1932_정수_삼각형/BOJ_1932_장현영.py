"""
정수 삼각형
풀이시간 1시간 30분(dp로 접근하고나서 30분)
최댓값을 각 level마다 갱신하는 것으로 접근했으나 자식 노드마다 덧셈 결과가 달라짐
dp로 푸는 문제
모든 경우의 수를 다 따지면 당연히 시간초과
level마다 더해나간 최대 결과치를 저장하면서 내려가기!
36504kb 124ms
"""

import sys

input = sys.stdin.readline

n = int(input())
arr = [[]]
for _ in range(n):
    nums = list(map(int,input().split()))
    arr.append(nums)



for level in range(2, n+1): # level 2부터 값 새롭게 더해주기
    for j in range(level):
        left = arr[level-1][j-1] if j > 0 else 0 # j가 0일 땐 왼쪽 부모 안 보기
        right = arr[level-1][j] if j < level-1 else 0 # j가 level과 같은 값일 땐 오른쪽 부모 안 보기
        arr[level][j] += max(left, right)

print(max(arr[n]))