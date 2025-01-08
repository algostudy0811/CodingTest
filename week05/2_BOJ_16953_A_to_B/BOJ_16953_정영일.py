import sys
input = sys.stdin.readline

# A를 B로 바꾸는 최소 연산 횟수 구하기
def atob(A, B):
    operations = 0
    while B > A:
        # 2로 나누어 떨어지면 2로 나누기
        if B % 2 == 0:
            B //= 2
        # 나머지가 1인 경우 1 붙이는 연산
        elif B % 10 == 1:
            B //= 10
        # 그 외에는 연산이 불가능하므로 -1 반환
        else:
            return -1
        operations += 1
    # 마지막 연산 결과 체크
    if B == A:
        return operations + 1
    else:
        return -1

if __name__ == "__main__":
    A, B = map(int, input().split())
    print(atob(A, B))