"""
도넛과 막대 그래프
cycle 그래프(n,n), 단선트리(n,n-1), cycle2회(2n+1,2n+2) 그래프 3형태를 찾아내기
임의로 매긴 번호 중 모두를 연결하는 core 1개를 찾아서 정점 먼저 번호 찾고
그래프의 연결상태로 3형태의 그래프 개수를 찾아내기
1. 모든 그래프의 연결? : 3형태의 그래프 개수 합은 최소 2이므로
최소 2개 방향으로 연결된 정점이 후보가 됨 + output이 0개!

8자 그래프
1. cycle먼저 감지, 현재 cycle이 점 하나도 또 cycle로 진행되는지 보기

edge가 너무 많아서 인접 리스트로 접근하기

*** 무관한 정점 하나는 카운팅에서 제거해야함!!
- 2번예시도 4->2가 막대그래프가 아니라 2 단독 막대그래프임
즉, 무관한 정점 먼저 찾고 빼고 개수 세기

대박 아이디어..
https://velog.io/@seoky1219/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8F%84%EB%84%9B%EA%B3%BC-%EB%A7%89%EB%8C%80-%EA%B7%B8%EB%9E%98%ED%94%84python
그래프를 특정할 수 있는 노드를 찾는다
생성된 노드
생성된 노드는 indegree가 0개 outdegree가 n개(2개 이상)
막대와 8자 모양만 먼저 센다!
막대 모양 그래프
가장 마지막 노드는 indegree가 1개 이상(생성 노드와의 연결일 수 있음) outdegree가 0개
8자 모양 그래프
중간에 있는 노드는 indegree가 2개 outdegree가 2개

여기에 만족하지 않는 노드들은 그냥 중간 노드일 뿐이라 개수 세지 않음

도넛 모양 그래프
생성된 노드의 outdegree 수에서 막대모양 그래프와 8자모양 그래프의 개수를 뺴서 구한다



"""

def solution(edges):
    # a->b
    in_degrees = [0 for _ in range(1000001)] # b의 개수
    out_degrees = [0 for _ in range(1000001)]  # a의 개수
    created = -1
    doughnut,stick,eight = 0,0,0
    max_num = -1
    for a,b in edges:
        max_num = max(max_num,a,b)
        in_degrees[b] += 1
        out_degrees[a] += 1

    for i in range(1, max_num + 1):
        if in_degrees[i] == 0 and out_degrees[i] >= 2:
            created = i
        elif in_degrees[i] >= 1 and out_degrees[i] == 0:
            stick += 1
        elif in_degrees[i] >= 2 and out_degrees[i] == 2:
            eight += 1
    doughnut = out_degrees[created] - (stick + eight)
    return [created, doughnut, stick, eight]


edges = [[2, 3], [4, 3], [1, 1], [2, 1]]