import sys
input = sys.stdin.readline

# ADDH = +60, ADDT = +10, MINT = -10, ADDO = +1, MINO = -1 
# 앞에 있는 버튼을 덜 누른것이 먼저 온다.
# 버튼 누르는 횟수 최소화 -> 앞의 버튼 최소화

# 30분대 까지는 10분 버튼이 덜 누르게 된다.
# 5분까지는 더하기로 하는것이 이득.

# 시간 계산 함수
def time_button(num):
    # 버튼별 횟수 기록
    ADDH, ADDT, MINT, ADDO, MINO = 0, 0, 0, 0, 0

    # 한시간이 넘는 시간은 일단 한시간 버튼 사용
    if num >= 60 :
        ADDH = num // 60
        num %= 60
        # 끝이면 바로 반환
        if num == 0:
            return ADDH, ADDT, MINT, ADDO, MINO
        
    # 40이 넘으면 한시간 버튼 추가 후 빼는 방식이 좋음
    if num >= 40 :
        ADDH += 1
        num = 60 - num
        # 이제 빼줘야 하므로 10분부터 해결
        MINT = num // 10
        num %= 10
        # 그 뒤 일의자리를 비교, 5분 이상이면 10분 버튼 사용 / 빼는 중
        if num > 5 :
            MINT += 1
            num = 10 - num
            # 이제 남은 것은 추가로 더 뺀것이기 때문에 더하기로 마무리
            ADDO = num
        # 5분 이하면 그냥 빼기로 마무리
        else:
            MINO = num
    # 40분 미만이면 더해주는 방식으로 진행
    else :
        ADDT = num // 10
        num %= 10
        # 그 뒤 일의자리를 비교, 5분 이상이면 10분 버튼 사용 / 더하는 중
        if num > 5 :
            ADDT += 1
            num = 10 - num
            # 이제 남은 것은 추가로 더 더한것이기 때문에 빼기로 마무리
            MINO = num
        # 5분 이하면 그냥 더하기로 마무리
        else:
            ADDO = num
    # 결과 반환시 마지막 버튼 조율
    if ADDT >= 4 :
        ADDT -= 4
        ADDH += 1
        MINT += 2
    if MINT >= 4 :
        MINT -= 4
        ADDH -= 1
        ADDT += 2
    if ADDO >= 6 :
        ADDO -= 6
        ADDT += 1
        MINO += 4
    return ADDH, ADDT, MINT, ADDO, MINO

if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        N = int(input())
        result = time_button(N)
        print(*result)