import sys
input = sys.stdin.readline

# 각 지점에서 도착지점까지 거리 표시하기
# 못가는 지점은 -1, 원래 벽은 0

# 각 점에서 도착지점까지의 최단거리를 표시해야하므로 BFS이용
# 길이를 가져가면서 적용
def path_length(y,x,length):
    queue = []
    queue.append([y,x,length])
    visited[y][x] = 1
    frame[y][x] = 0
    while queue:
        q = queue.pop(0)
        for d in range(4):
            di = q[0]+dti[d]
            dj = q[1]+dtj[d]
            if di<0 or di>N-1 or dj<0 or dj>M-1:
                continue
            elif visited[di][dj]==0 and frame[di][dj]==1:
                visited[di][dj]=1
                # 지금까지의 길이를 적용
                frame[di][dj]=q[2]
                queue.append([di,dj,q[2]+1])

if __name__=="__main__":
    N,M = map(int,input().split())
    frame = [list(map(int,input().split())) for _ in range(N)]
    visited = [[0]*M for _ in range(N)]
    dti = [0,1,0,-1]
    dtj = [1,0,-1,0]
    for i in range(N):
        for j in range(M):
            if frame[i][j]==2:
                path_length(i,j,1)
                for ii in range(N):
                    for jj in range(M):
                        if frame[ii][jj]==1 and visited[ii][jj]==0:
                            frame[ii][jj]=-1
                for f in frame:
                    print(*f)
                exit()