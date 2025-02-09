def solution(s):
    s = s[2:-2]
    str_sets = s.split("},{")  #["2","2,1"]

    new_sets = [list(map(int, sub.split(','))) for sub in str_sets]
    new_sets.sort(key=len)

    result = []
    seen = set()
    for subset in new_sets:
        for num in subset:
            if num not in seen:
                result.append(num)
                seen.add(num)

    return result