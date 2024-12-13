"""
트리 구조를 담아내는 가장 효율적인 방법:
"""


import sys

input = sys.stdin.readline

n = int(input())
tree = dict() # 딕셔너리 구조로 연결된 트리를 나타내기
for _ in range(n):
    root, c1, c2 = input().split()
    tree[root] = (c1 if c1 != '.' else None, c2 if c2 != '.' else None)

# 전위 2 1 3
# 중위 1 2 3
# 후위 1 3 2

# 전위
pre_order_list = []
def pre_order(node):
    if node is not None:
        pre_order_list.append(node)
        pre_order(tree[node][0]) # 왼쪽
        pre_order(tree[node][1]) # 오른쪽

# 중위
in_order_list = []
def in_order(node):
    if node is not None:
        in_order(tree[node][0]) # 왼쪽
        in_order_list.append(node)
        in_order(tree[node][1]) # 오른쪽

# 후위
post_order_list = []
def post_order(node):
    if node is not None:
        post_order(tree[node][0]) # 왼쪽
        post_order(tree[node][1]) # 오른쪽
        post_order_list.append(node)

pre_order("A")
in_order("A")
post_order("A")
print("".join(pre_order_list))
print("".join(in_order_list))
print("".join(post_order_list))