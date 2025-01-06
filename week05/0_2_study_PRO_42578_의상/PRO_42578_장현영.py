"""
모든 조합을 다 곱한 뒤에 빼기
"""

def solution(clothes):
    d = dict()
    for name, kind in clothes:
        if kind in d:
            d[kind] += 1  # 개수 늘리기
        else:
            d[kind] = 2  # 개수 초기선언, 안 입는 경우까지

    answer = 1
    for cnt in d.values():
        answer *= cnt
    return answer - 1
