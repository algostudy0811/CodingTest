"""
이동 횟수 세기
포인터, 부분합 초기값을 0,0,total_list[0]말고, 0,n,sum(queue1)로 진행하기!
p1:0, p2:n부터 출발(queue1은 [p1:p2] 구조, p1,p2 이동하면서 최소 이동값 찾아주기)
target에 맞춰서 움직이기(queue1 합 기준 queue2 앞에서부터 합산해주기)
| 3 2 7 2 | 4 6 5 1
-> | 3 2 7 2 4 | 6 5 1 (p2 이동 진행) p2 

|1 2 1 2 | 1 10 1 2
-> (p1) 1 2 1 2 1 10 (p2) 1 2 (p2 이동 진행) p2 > 6번 위치: 6-4= 2번 이동
-> 1 2 1 2 1 p1 10 p2 1 2 (p1 이동 진행) p1 > 5번 이동: 5번 이동
7번 이동

10 1 2 1 || 2 1 1 2
-> 10 p2 1 2 1 2 1 1 2

슬라이스 윈도우 활용

"""

def solution(queue1, queue2):

    total_list = queue1 + queue2
    total_sum = sum(total_list)
    target = total_sum // 2

    # 목표 합이 불가능한 경우
    if total_sum % 2 != 0:
        return -1

    n = len(queue1)
    p1, p2 = 0, n 
    part_sum = sum(queue1)  # queue1 합으로 부분합 초기화
    min_val = 1e9  # 최소값 초기화

    # 슬라이딩 윈도우
    while p1 < n*2 and p2 < n*2:
        if part_sum == target:
            move_count = p1 + (p2-n if p2 >= n else 0)
            min_val = min(min_val, move_count)

        if part_sum < target:
            if p2 < n*2:
                part_sum += total_list[p2]
            p2 += 1
        else:
            part_sum -= total_list[p1]
            p1 += 1

    return min_val if min_val != 1e9 else -1