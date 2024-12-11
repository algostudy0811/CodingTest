def solution(diffs, times, limit):

    # 6
    # (3 + 6) * (4 - 2) + 3 = 21
    # (8 + 3) * (4 - 2) + 8 = 30
    # 2

    # 59 - sum(times) = 40
    # time_add(res) + sum(times) <= limit

    l = 1
    r = max(diffs)
    n = len(diffs)
    answer = r

    while l < r:
        level = (l + r) // 2
        time = times[0]

        for idx in range(1, n):
            time += times[idx]
            if level < diffs[idx]:
                time += (times[idx] + times[idx - 1]) * (diffs[idx] - level)

        # time이 limit보다 적다면 level의 최소값을 찾으러 (ㅣ == r이 될 때까지)
        if time <= limit:
            r = level
            answer = level
        # time이 limit보다 크다면 level이 부족하다는 의미이므로 level을 올림
        else:
            l = level + 1

    return answer

# diffs = [1, 5, 3]
# times = [2, 4, 7]
# limit = 30
#
# print(solution(diffs, times, limit))