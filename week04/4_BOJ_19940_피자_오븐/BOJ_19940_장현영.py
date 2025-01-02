"""
피자오븐
시,분,초 단위로 생각해서 정답 구하기
풀이참고
인덱스 내 조건식넣어서 더할떄 뺄때 인덱스 차이두기 true이먄 1 false이면 0
"""

import sys
input = sys.stdin.readline

for _ in range(int(input())):
    n = int(input())
    buttons = [0]*5
    sixties, tens, ones = n//60, (n % 60)//10, n % 10

    if ones > 5:
        tens += 1
        ones -= 10
    if tens > 3:
        sixties += 1
        tens -= 6
    if tens < 0 and ones == 5:
        tens += 1
        ones -= 10

    buttons[0] = sixties
    buttons[2-(tens >= 0)] = abs(tens)
    buttons[4-(ones >= 0)] = abs(ones)
    print(*buttons)