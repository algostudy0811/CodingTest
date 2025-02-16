"""
후위 연산자
"""
from collections import deque
n = int(input())
calc_str = input().strip()

alphabet = [chr(i) for i in range(ord("A"), ord("A")+n)]
nums = []

for _ in range(n):
    nums.append(int(input()))

d = dict(zip(alphabet,nums))
stack = deque()
for each in calc_str:
    if each.isalpha():
        stack.append(d[each])
    else: # 연산자
        b = stack.pop()
        a = stack.pop()
        if each == '+':
            stack.append(a+b)
        elif each == '-':
            stack.append(a-b)
        elif each == '*':
            stack.append(a*b)
        elif each == '/':
            stack.append(a/b)

print(f"{stack.pop():.2f}")

