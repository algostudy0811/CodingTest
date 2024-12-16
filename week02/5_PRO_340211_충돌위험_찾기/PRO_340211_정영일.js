// 첫 구현 시도 시간 초과 -> 충돌 발생 시에만 체크하도록 수정해야 할듯?
// ---------------------------------------------------------
function solution(points, routes) {    
  // 결과값, 물류 센터 좌표 생성
  let dangerPoint = 0;
  let board = Array.from({length:101}, () => Array(101).fill(0));
  
  // 로봇이 좌표 처음 배치되고 검사해야함
  // 도착한 다음 시간에 없어짐
  
  // 현재 시간, 끝난 로봇 표시
  let time = 0;
  let endRobots = Array(routes.length).fill(0);
  
  // 로봇 현재 위치
  let nowRobots = Array.from({length:routes.length}, () => []);
  
  // 로봇 배치
  for (let r=0; r<routes.length; r++) {
      let startPoint = points[routes[r][0]-1];
      console.log(startPoint[0], startPoint[1]);
      board[startPoint[0]][startPoint[1]] += 1;
      nowRobots[r] = [startPoint[0], startPoint[1]];
  }
  
  // 위험 상태 판단 함수
  function dangerCheck() {
      // 전체 검수
      for (i=1; i<101; i++) {
          for (j=1; j<101; j++) {
              if (board[i][j] > 1) {
                  dangerPoint += 1;
              }
          }
      }
  };
  // 시간 흐름 함수
  function timeStep() {
      // 각 로봇 순회
      for (let r=0; r<routes.length; r++) {
          // 지금 위치가 들러야 할 포인트 좌표라면? 포인트 한곳에 도착한 것. routes 갱신 후 로직 실행해야함
          if(nowRobots[r] === points[routes[r][0]-1]) {
              routes[r] = routes[r].splice(0,1)
          }
          // 루트에서 더 갈데가 없고 아직 안끝난 로봇이면? 로봇 퇴장, 끝난 로봇에 표시
          if (routes[r].length < 1 && endRobots[r] === 0) {
              board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
              endRobots[r] = 1;
              continue;
          }
          
          // 목적지 설정
          let toGo = points[routes[r][0]-1];
          
          // 이동 여부
          let changed = false;
          
          // r 좌표 먼저 비교
          if (nowRobots[r][0] !== toGo[0]) {
              // 목적지 좌표가 크면 밑으로 아니라면 위로
              if (nowRobots[r][0] < toGo[0]) {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]+1][nowRobots[r][1]] += 1;
                  nowRobots[r][0] += 1;
                  changed = true;
              } else {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]-1][nowRobots[r][1]] += 1;
                  nowRobots[r][0] -= 1;
                  changed = true;
              }
          }
          
          // r좌표로 이동하지 않았다면 조건에 따라 좌우로 이동
          if (!changed) {
              if (nowRobots[r][1] < toGo[1]) {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]][nowRobots[r][1]+1] += 1;
                  nowRobots[r][1] += 1;
                  changed = true;
              } else {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]][nowRobots[r][1]-1] += 1;
                  nowRobots[r][1] -= 1;
                  changed = true;
              }
          }
      }
  };
  
  // 검사
  dangerCheck();
  
  // 모든 로봇이 끝나기 전까지 이동, 검사 반복
  while (endRobots.includes(0)) {
      timeStep();
      dangerCheck();
  }
  
  // 다 끝나면 결과값 반환
  return dangerPoint
}

// ------------------------------------------------------------
function solution(points, routes) {    
  // 결과값, 물류 센터 좌표 생성
  let dangerPoint = 0;
  let board = Array.from({length:101}, () => Array(101).fill(0));
  
  // 로봇이 좌표 처음 배치되고 검사해야함
  // 도착한 다음 시간에 없어짐
  
  // 현재 시간, 끝난 로봇 표시
  let time = 0;
  let endRobots = Array(routes.length).fill(0);
  
  // 로봇 현재 위치
  let nowRobots = Array.from({length:routes.length}, () => []);
  
  // 로봇 배치
  for (let r=0; r<routes.length; r++) {
      let startPoint = points[routes[r][0]-1];
      console.log(startPoint[0], startPoint[1]);
      board[startPoint[0]][startPoint[1]] += 1;
      nowRobots[r] = [startPoint[0], startPoint[1]];
  }
  
  // 위험 상태 판단 함수
  function dangerCheck() {
      // 전체 검수 <- 시간 낭비 / 현재 로봇 위치만 살피기
      let checkPoint = new Set(nowRobots.map(point => point.join(',')));
      for (point of checkPoint) {
          let [x, y] = point.split(',').map(Number);
          if (board[x][y] > 1) {
              dangerPoint++;
          }
      }
  };
  
  // 시간 흐름 함수
  function timeStep() {
      // 검사해야하는지
      let movedPoint = [];
      
      // 각 로봇 순회
      for (let r=0; r<routes.length; r++) {
          // 지금 위치가 들러야 할 포인트 좌표라면? 포인트 한곳에 도착한 것. routes 갱신 후 로직 실행해야함
          if(JSON.stringify(nowRobots[r]) === JSON.stringify(points[routes[r][0]-1])) {
             routes[r].splice(0,1);
          }
          // 루트에서 더 갈데가 없고 아직 안끝난 로봇이면? 로봇 퇴장, 끝난 로봇에 표시
          if (routes[r].length < 1 && endRobots[r] === 0) {
              board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
              endRobots[r] = 1;
              continue;
          }
          // 이미 끝난 곳의 경우 목적지가 더 없을 때 걸러줘야 함
          if (routes[r].length === 0) {
              continue;
          }
          
          // 목적지 설정
          let toGo = points[routes[r][0]-1];
          
          // 이동 여부
          let changed = false;
          
          // r 좌표 먼저 비교
          if (nowRobots[r][0] !== toGo[0]) {
              // 목적지 좌표가 크면 밑으로 아니라면 위로
              if (nowRobots[r][0] < toGo[0]) {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]+1][nowRobots[r][1]] += 1;
                  nowRobots[r][0] += 1;
                  movedPoint.push(nowRobots[r]);
                  changed = true;
              } else {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]-1][nowRobots[r][1]] += 1;
                  nowRobots[r][0] -= 1;
                  movedPoint.push(nowRobots[r]);
                  changed = true;
              }
          }
          
          // r좌표로 이동하지 않았다면 조건에 따라 좌우로 이동
          if (!changed) {
              if (nowRobots[r][1] < toGo[1]) {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]][nowRobots[r][1]+1] += 1;
                  nowRobots[r][1] += 1;
                  movedPoint.push(nowRobots[r]);
                  changed = true;
              } else {
                  board[nowRobots[r][0]][nowRobots[r][1]] -= 1;
                  board[nowRobots[r][0]][nowRobots[r][1]-1] += 1;
                  nowRobots[r][1] -= 1;
                  movedPoint.push(nowRobots[r]);
                  changed = true;
              }
          }
      }
      for (let move of movedPoint) {
          if (board[move[0]][move[1]] > 1) {
              dangerCheck();
              break
          }
      }
  };
  
  // 첫 배치 후 한번 체크
  dangerCheck();
  // 모든 로봇이 끝나기 전까지 이동, 검사 반복
  while (endRobots.includes(0)) {
      timeStep();
  }
  
  // 다 끝나면 결과값 반환
  return dangerPoint;
}