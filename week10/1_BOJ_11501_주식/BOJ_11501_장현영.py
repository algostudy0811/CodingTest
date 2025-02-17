"""
주식 마지막 값을 확인
"""

t = int(input())
for _ in range(t):
    n = int(input())
    stock = list(map(int, input().split()))
    stock= stock[::-1] # 거꾸로 확인해서
    max_val = stock[0]
    ans = 0
    # 거꾸로 확인해서 maxval 갱신해주고, 마지막 값의 이익들을 더해나가기
    for i in range(1, n):
        if max_val < stock[i]:
            max_val = stock[i]
            continue
        ans += max_val - stock[i]

    print(sum)