
# # DSLR 함수 지정

# def D(n) :
#     return 2*n % 10000

# def S(n) :
#     if n == 0 :
#         return 9999
#     else :
#         return n-1

# # 4자리 이상은 계속 내려주므로 
# def L(n) : 
#     return (n%1000)*10 + n//1000

# def R(n) :
#     return (n%10)*1000 + n//10

# 1~10000까지의 숫자에 최단 경로를 저장
# 시간도 넉넉하고 지정한 숫자가 마지막이어도 연산 가능할듯
# 시간초과?? 왜? -> pop(0)이 O(n)이라서..
# 덱으로 앞에서 꺼내게 조정 -> 그래도 시간초과
# 모든 경우를 다 큐에 넣어서 그런가? -> 연산 시 방문 확인하고 넣어도 시간초과
# 문자열 함수를 굳이 만들지 말고 처리
# 방문 처리도 set로 변경하여 방문한 곳만 넣기 -> 역시 시간초과
# 로직이 틀렸나? DSLR을 무슨 순서로 처리해야하는지 기준을 잡을 수가 없음

import sys
input = sys.stdin.readline
from collections import deque

def BFS(A, B):
    visited = set()
    queue = deque([(A, "")]) 
    visited.add(A)
    
    while queue:
        now, func = queue.popleft()
        
        if now == B:
            return func
        
        for i in range(4):
            next_num = calc(now, i)
            
            if next_num not in visited:
                visited.add(next_num)
                if i == 0:
                    queue.append((next_num, func + "D"))
                elif i == 1:
                    queue.append((next_num, func + "S"))
                elif i == 2:
                    queue.append((next_num, func + "L"))
                else:
                    queue.append((next_num, func + "R"))

# 계산 함수 분리
def calc(n, idx):
    if idx == 0:  # D: 두 배
        return (n * 2) % 10000
    elif idx == 1:  # S: -1
        return 9999 if n == 0 else n - 1
    elif idx == 2:  # L: 왼쪽으로 회전
        return (n % 1000) * 10 + (n // 1000)
    else:  # R: 오른쪽으로 회전
        return (n % 10) * 1000 + (n // 10)
    
if __name__ == "__main__" :
    T = int(input())
    for _ in range(T) :
        A, B = map(int, input().split())
        print(BFS(A, B))