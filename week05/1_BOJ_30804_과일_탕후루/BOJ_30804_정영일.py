import sys
input = sys.stdin.readline
from collections import deque

# 양쪽에서 과일 제거 가능, 최대한 적은 과일 제거 및 덱 활용?
# 세트 활용 인자 종류 2개 이하 판별하기

# 제거하는 행위 = 한 칸 전진으로 본다면.. 최소 거리 찾기와 유사
# BFS 활용?

# 경우를 모두 봐야하니 재귀함수? 재귀로 bfs 구현 가능?

# 앞 빼기, 뒤 빼기이므로 이진트리?
# 이진트리 탐색방법?

# 앞에서 a개, 뒤에서 b개 제거이므로 수를 조절하며 시도?

# 포인터를 설정하여 비교하기?

# 세트 활용 인자 종류 2개 이하 판별하기
def checking(tanghuru):
    return len(set(tanghuru)) <= 2

if __name__ == "__main__":
    N = int(input()) 
    fruits = list(map(int, input().split()))  
    max_tanghuru = 0 

    # 포인터 설정
    left = 0  
    right = 0  
    # 딕셔너리 활용 저장
    fruit_count = {}  

    # 오른쪽 포인터가 끝까지 이동할 때까지 반복
    while right < N:
        if fruits[right] in fruit_count:
            fruit_count[fruits[right]] += 1  
        else:
            fruit_count[fruits[right]] = 1  # 새로운 과일 추가

        # 과일 종류가 두가지가 넘게되면 왼쪽 포인터 이동
        while len(fruit_count) > 2:
            fruit_count[fruits[left]] -= 1 
            if fruit_count[fruits[left]] == 0:
                del fruit_count[fruits[left]]  #
            left += 1  

        # 최대값 갱신
        max_tanghuru = max(max_tanghuru, right - left + 1) 
        right += 1 

    print(max_tanghuru)  # 최대 탕후루 길이 출력