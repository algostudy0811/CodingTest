import sys
input = sys.stdin.readline

# 마을을 두개로 분리, 유지비 최소화하기
# 가장 적은 비용 간선 이용 후 두개로 분리
# 분리한다는 것 = 간선을 없애기 -> 가장 큰 비용 간선 제거

# 부모 노드를 찾기
def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

# 두 노드를 합치는 함수 (부모 노드를 찾아서 연결)
def union(x, y):
    rootX = find(x)
    rootY = find(y)
    if rootX != rootY:
        parent[rootY] = rootX

if __name__ == "__main__" :
    N, M = map(int, input().split())
    houses = []
    for _ in range(M):
        A, B, C = map(int, input().split())
        houses.append((C, A, B))

    # 유지비를 기준으로 정렬
    houses.sort()
    # 본인을 부모로 설정
    parent = list(range(N + 1))
    # 결과값 지정
    total_cost = 0
    max_cost = 0
    # 최소 비용으로 모두 연결
    for cost, a, b in houses:
        if find(a) != find(b):
            union(a, b)
            total_cost += cost
            max_cost = cost

    # 모두 연결한 뒤 가장 큰 간선 삭제하여 마을 분리하기
    print(total_cost - max_cost)