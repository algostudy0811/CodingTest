"""
2를 곱하거나
1을 오른쪽에 추가하기
홀수를 만드는 방법이 1을 오른쪽에 추가하는 것 외에는 없음
a < (!=) b
1 2 4 8 외에는 값을 나타낼 수 없음
"""

a, b = map(int, input().split())
cnt = 1
if b%2 and b%10 != 1:
    print(-1)
    exit(0)

# 그리디하게 접근하기
# b를 a로 만들기(역으로 접근)
while a < b:
    #print("지금",b)
    if b%10 == 1: # 가장 먼저 해야할 것
        b //= 10
        cnt += 1
        continue
    # 홀수인데 1로 안끝남
    if b%2 and b%10 != 1: # 지금 b가 홀수인데 1이 아니다
        print(-1)
        exit(0)
    # 조건들에 해당 안되는 짝수면 2로 나누고 다시
    b//=2
    cnt += 1

# a > b인 경우도 마지막으로 고려
if a==b:
    print(cnt)
else:
    print(-1)
