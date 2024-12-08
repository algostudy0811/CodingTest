import sys
from collections import deque
input = sys.stdin.readline

def get_level(opinions):
    cut = int(n * 0.15 + 0.5)
    cal_opinions = sorted(opinions)
    cal_opinions = deque(cal_opinions)
    for i in range(cut):
        cal_opinions.popleft()
        cal_opinions.pop()
    sum_num = sum(cal_opinions)
    return int(sum_num / (n - 2 * cut) + 0.5)

if __name__ == "__main__":
    n = int(input())
    opinions = []
    for _ in range(n):
        level = int(input())
        opinions.append(level)
    if len(opinions) == 0:
        print(0)
    else:
        print(get_level(opinions))