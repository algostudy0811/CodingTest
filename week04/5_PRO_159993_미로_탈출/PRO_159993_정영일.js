function solution(maps) {
  // 레버로 진행 -> 출구로 진행 최단거리 두번 시행해야함

  // 탐색 기본 정보 설정
  const rows = maps.length;
  const cols = maps[0].length;
  const directions = [[1, 0], [-1, 0], [0, 1], [0, -1]];

  let start, lever, exit;

  // S, L, E의 위치를 찾음
  for (let i = 0; i < rows; i++) {
    for (let j = 0; j < cols; j++) {
      if (maps[i][j] === 'S') {
        start = [i, j];
      }
      if (maps[i][j] === 'L') {
        lever = [i, j];
      }
      if (maps[i][j] === 'E') {
        exit = [i, j];
      }
    }
  }

  // BFS 탐색 -> 시작 지점에서 목표 지점까지의 최단 경로를 찾음
  function bfs(start, target) {
    // 큐, 방문처리
    const queue = [[...start, 0]];
    const visited = Array.from({ length: rows }, () => Array(cols).fill(false));
    visited[start[0]][start[1]] = true;

    while (queue.length) {
      const [x, y, dist] = queue.shift();

      // 목표 지점에 도달하면 거리 반환
      if (x === target[0] && y === target[1]) {
        return dist;
      }

      for (const [dx, dy] of directions) {
        const nx = x + dx;
        const ny = y + dy;

        // 유효한 이동인지 확인하고, 방문하지 않은 통로라면 큐에 추가
        if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && !visited[nx][ny] && maps[nx][ny] !== 'X') {
          visited[nx][ny] = true;
          queue.push([nx, ny, dist + 1]);
        }
      }
    }

    // 목표 지점에 도달할 수 없으면 -1 반환
    return -1;
  }

  // 불가능한 경우 바로 -1 반환
  const toLever = bfs(start, lever);
  if (toLever === -1) {
    return -1;
  }

  const toExit = bfs(lever, exit);
  if (toExit === -1) {
    return -1;
  }

  return toLever + toExit;
}