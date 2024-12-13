# 메모리 32412kb 시간 36ms

import sys
input = sys.stdin.readline

# 순회별 함수 작성
def preorder(node) :
    # . 제거 / 루트-왼쪽-오른쪽
    if node != '.':
        print(node, end='')
        preorder(tree[node][0])
        preorder(tree[node][1])

def inorder(node) :
    # . 제거 / 왼쪽-루트-오른쪽
    if node != '.':
        inorder(tree[node][0])
        print(node, end='')
        inorder(tree[node][1])

def postorder(node) :
    # . 제거 / 왼쪽-오른쪽-루트
    if node != '.':
        postorder(tree[node][0])
        postorder(tree[node][1])
        print(node, end='')

if __name__ == "__main__":
    N = int(input())
    tree = {}
    for i in range(N) :
        parent, *child = input().split()
        tree[parent] = child
    preorder('A')
    print()
    inorder('A')
    print()
    postorder('A')