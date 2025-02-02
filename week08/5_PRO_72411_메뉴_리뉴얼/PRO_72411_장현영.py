"""
2명 이상 주문만 코스에 포함 가능
코스는 result의 길이로 가능한 숫자들
"""
from itertools import combinations
from collections import defaultdict

def solution(orders, course):
    answer = []
    max_len = 1
    for each in orders:
        max_len = max(max_len,len(each))
    for i in course:
        if i > max_len:
            break
        d = defaultdict(int)
        for each in orders:
            tmp_list = sorted(list(each))
            comb = list(combinations(tmp_list,i))
            for c in comb:
                d[c] += 1
        d = sorted(d.items(), key=lambda x: -x[1])
        able_cnt = d[0][1]
        if able_cnt < 2:
            continue
        for tup,num in d:
            temp_str = ''.join(tup)
            if num < able_cnt:
                break
            answer.append(temp_str)
    answer.sort()
    return answer