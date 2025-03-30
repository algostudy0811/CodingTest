"""
전화번호 목록
정렬 먼저해서 작은 것부터 하나씩 비교해가기
해당 문자열이 그대로 있는지만 확인하면 됨
"""


import sys
input = sys.stdin.readline

flag = True
for _ in range(int(input())):
    n = int(input())
    arr = list(input().strip() for _ in range(n)) # 개행처리
    arr.sort() # 길이가 작은것 우선으로 가도록 설정해서 1번씩만 돌게하기
    print(arr)
    for i in range(n-1):
        if arr[i] in arr[i+1][:len(arr[i])]: # 다음 것만큼 길이 확인해서
            print("NO")
            flag = False
            break
    if flag:
        print("YES")
    flag = True