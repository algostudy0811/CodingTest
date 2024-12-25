import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

# 암호가 n자리이면, n-1자리까지는 중복순열을 다 시도한 것.
# n-1 자리까지 시도한 횟수 + n 자리 중복순열 시도, 결과 900528로 나누기
# 중복순열 갯수 = (문자 수)**(자리 수)
# 지수가 늘어나는 등비수열 -> 공비는 문자 수

# r**n이 너무 큰 수 일수 있으므로 모듈화 계산 필요함

def mod_exp(r, n):
    result = 1
    r = r % 900528  # r을 미리 나누어 작은 값으로 초기화
    
    # n이 홀수면 r 곱함
    while n > 0:
        if n % 2 == 1: 
            result = (result * r) % 900528
        # r을 제곱하고 나눔
        r = (r * r) % 900528  
        n //= 2  # n을 반으로 나눔
    
    return result

# 자리수별 암호 시도 함수
def password_try(r, n) :
    if r == 1 :
        return n
    
    r_pow_n = mod_exp(r, n) - 1
    r_minus_1 = r - 1

    if r_pow_n == 0 :
        return 0

    sun = (r * r_pow_n) % 900528
    result = (sun * pow(r_minus_1, -1, 900528)) % 900528

    return result

# 중복순열 시도 함수
def password_nword(now) :
    global nword_shot, found
    if found :
        return
    elif len(now) == len(password) :
        nword_shot += 1
        if now == password :
            found = True
            return
        return
    for i in range(len(data)) :
        if found :
            return
        new = now + data[i]
        password_nword(new)
    
if __name__ == "__main__":
    data = input().strip()
    password = input().strip()
    # 한 글자일 경우 바로 출력
    if len(password) == 1 :
        print(data.index(password)+1)
        exit()
    shot = password_try(len(data), len(password)-1)
    nword_shot = 0
    found = False
    password_nword("")
    print((shot + (nword_shot % 900528)) % 900528)
    
        
    