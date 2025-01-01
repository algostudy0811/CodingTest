import sys
input = sys.stdin.readline

# 오리 도착 여부 확인 함수
def duck_check(num):
    global land
    # 시작점 저장, 막힌 곳 저장
    start = num
    block = 0
    # 이진 트리이기 때문에 2로 나누어가며 확인
    while num > 0:
        if land[num] == 1:
            block = num
        num //= 2
    # 막힌 곳이 없으면 해당 위치에 오리가 도착
    if block == 0:
        land[start] = 1
        return 0
    # 막힌 곳이 있으면 막힌 곳 중 가장 마지막=경로상 처음 반환
    return block

if __name__ == "__main__":
    N, Q = map(int, input().split())
    land = [0] * (N + 1)
    for _ in range(Q):
        duck = int(input())
        print(duck_check(duck))
        