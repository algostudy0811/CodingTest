import sys
input = sys.stdin.readline

# 포켓몬 이름을 순서와 함께 저장해야함
# 인덱스도 찾아야하므로 순서도 중요
if __name__=="__main__":
    N,M = map(int,input().split())
    pokemon = {}

    for n in range(N):
        name = input().strip()
        pokemon[name]=n+1
    dic_no = [0]+list(pokemon)
    for m in range(M):
        search = input().strip()
        # 알파벳 입력이면 인덱스 출력, 아니면 이름 출력
        if not search.isalpha():
            print(dic_no[int(search)])
        else:
            print(pokemon[search])