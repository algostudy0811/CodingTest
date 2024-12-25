# 메모리 32412kb 시간 32ms

import sys
input = sys.stdin.readline

# 떡 갯수 찾기
# n번째 떡 = n-1번째 떡 + n-2번째 떡
# 피보나치와 비슷함...
# A, B, A+B, (A+B)+B, (A+B)+B+(A+B), ... 순으로 떡이 나옴
# A, B를 찾아내야 하니, A, B의 계수를 찾아내는 방법을 사용

def tteok(D, K):
    # A와 B의 계수를 초기화
    a = [0] * (D+1)
    b = [0] * (D+1)
    
    # 기본 케이스 설정
    a[1], a[2] = 1, 0
    b[1], b[2] = 0, 1
    
    # A와 B의 계수 계산
    for i in range(3, D+1):
        a[i] = a[i-1] + a[i-2]
        b[i] = b[i-1] + b[i-2]
    
    # A와 B 찾기
    for A in range(1, K):
        # 계수는 알고있으니 값을 하나씩 넣어봄
        if (K - a[D] * A) % b[D] == 0:
            B = (K - a[D] * A) // b[D]
            if A <= B:
                return A, B

if __name__ == "__main__":
    # 입력 읽기
    D, K = map(int, input().split())

    # 초기 떡 개수 찾기 및 출력
    A, B = tteok(D, K)
    print(A)
    print(B)