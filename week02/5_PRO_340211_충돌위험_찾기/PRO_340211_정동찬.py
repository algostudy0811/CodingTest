from collections import Counter

def solution(points, routes):
    def to_imaginary(array):
        return array[0] + array[1] * 1j

    def move(S_idx):
        S = robot_position[S_idx]
        E = to_imaginary(points[robot_target[S_idx] - 1])

        if S.real != E.real:
            S += 1 if S.real < E.real else -1
        elif S.imag != E.imag:
            S += 1j if S.imag < E.imag else -1j

        robot_position[S_idx] = S
        return S == E

    # 로봇 수
    x = len(routes)

    # 로봇의 도착 여부
    # for i in range(1, 1 + n):
    #     robot |= 1 << i
    robot = (1 << (1 + x)) - 2

    # 로봇의 현재 위치, 1 ~ x
    robot_position = [0] + [to_imaginary(points[routes[idx][0] - 1]) for idx in range(x)]

    # 로봇의 타겟, 1 ~ x
    robot_target = [0] + [1 for _ in range(x)] # routes 내 각 array의 인덱싱으로 표시

    # 로봇의 각 routes 길이, 1 ~ x
    robot_routes_len = [0] + [len(routes[idx]) for idx in range(x)]

    timestep = 0
    answer = 0
    while robot:
        counted = Counter(robot_position[1:])
        print(f'time: {timestep} counted: {counted}')
        print([key for key, value in counted.items()])
        duplicates = sum([1 for key, value in counted.items() if key != 0 and value > 1])

        answer += duplicates

        # 이번 timestep에서 종료되는 robot
        # 루프 마지막에 robot 및 robot_position을 초기화
        finish = 0

        for r in range(1, 1 + x):
            print(r, robot & (1 << r))
            if not (robot & (1 << r)):
                continue

            # move해서 target에 도달했다면
            if move(r):
                # target이 마지막이라면
                if robot_target[r] == robot_routes_len[r]:
                    finish |= 1 << r

                # 다음 target으로 가야 한다면
                else:
                    robot_target[r] += 1

        for r in range(1, 1 + x):
            if finish & (1 << r):
                robot &= ~(1 << r)
                robot_position[r] = 0

        timestep += 1

    return answer

points = [[3, 2], [6, 4], [4, 7], [1, 4]]
routes = [[4, 2], [1, 3], [2, 4]]
print(solution(points, routes))