# 메모리 36504kb 시간 80ms

import sys
input = sys.stdin.readline

def max_value(triangle) :
    # 맨 밑에서부터 올라옴
    depth = n - 1
    
    while depth > 0 :
        # 각 층은 층계만큼의 숫자가 있음, +1까지 볼것이므로 총 숫자 -1까지만 순회
        for i in range(depth) :
            # 윗층에 현재 층 2개 중 큰걸 부여함
            triangle[depth-1][i] += max(triangle[depth][i], triangle[depth][i+1])
        # 다 돌고 층 올라가기
        depth -= 1
    
    # 다 돌면 0층에 최대값이 나옴
    return triangle[0][0]


if __name__ == "__main__" :
    # 입력 받기
    n = int(input())
    triangle = []
    for i in range(n) :
        nodes = list(map(int, input().split()))
        triangle.append(nodes)
    print(max_value(triangle))
    

