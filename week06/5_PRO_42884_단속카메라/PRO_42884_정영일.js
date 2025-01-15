function solution(routes) {
  // 모든 차량이 찍혀야 함
  // 차량이 나간 지점을 기준으로 정렬
  routes.sort((a, b) => a[1] - b[1]);

  let camera = -30001;
  let answer = 0;

  // 차가 지나갈때 시작점보다 카메라가 뒤면 그 차의 끝으로 옮김
  // 나간 지점 기준으로 정렬되었으므로 효율적
  for (let [start, end] of routes) {
    if (camera < start) {
      camera = end;
      answer++;
    }
  }

  return answer;
}