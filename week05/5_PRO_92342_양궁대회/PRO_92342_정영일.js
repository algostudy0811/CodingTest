function solution(n, info) {
  // 상대보다 많이 쏴야 그 점수를 먹음, 점수는 점수별로 한번만 부여
  // 효율적으로 상대보다 조금 더 쏘면서 점수 먹어보기
  // 백트래킹으로 쏴보는게 좋을 듯
  // 점수 인덱스를 넘어가면서 상대를 이길만큼 쏴보기
  // 만약 내줘야할 점수면 다시 돌아와서 안쏘고 넘어가기

  // 비교 초기값 설정
  let maxDifference = -1;
  let bestResult = [-1];

  // 비교용 점수차 구하기
  function calculateScore(ryan) {
    let apeachScore = 0;
    let ryanScore = 0;

    for (let i = 0; i < 11; i++) {
      if (info[i] > ryan[i]) {
        apeachScore += (10 - i);
      } else if (info[i] < ryan[i]) {
        ryanScore += (10 - i);
      }
    }

    return ryanScore - apeachScore;
  }

  // 점수별 화살 쏘기, 안쏘기 백트래킹
  function backtrack(remainArrows, ryan, idx) {
    // 화살이 없거나 0점까지 도달하면 그만쏘기
    if (remainArrows === 0 || idx === 11) {
      // 점수차 비교 후 갱신
      let scoreDiff = calculateScore(ryan);
      if (scoreDiff > maxDifference) {
        maxDifference = scoreDiff;
        bestResult = [...ryan];
      } else if (scoreDiff === maxDifference) {
        // 점수차가 같다면 작은 점수를 많이 쏜 것으로 갱신
        for (let i = 10; i >= 0; i--) {
          if (ryan[i] > bestResult[i]) {
            bestResult = [...ryan];
            break;
          } else if (ryan[i] < bestResult[i]) {
            break;
          }
        }
      }
      return;
    }

    // 어피치가 쏜 화살보다 하나 더 쏴야 이길 수 있음
    let arrowsRequired = info[idx] + 1;
    // 쏠 수 있으면 지금 점수에 화살 쏨
    if (remainArrows >= arrowsRequired) {
      ryan[idx] = arrowsRequired;
      backtrack(remainArrows - arrowsRequired, ryan, idx + 1);
      ryan[idx] = 0;
    }

    // 지금 점수 그냥 넘어가기
    backtrack(remainArrows, ryan, idx + 1);
  }

  // 초기상태로 백트래킹 시작
  backtrack(n, new Array(11).fill(0), 0);

  // 마지막 검사
  let remainArrows = n;
  for (let i = 0; i < 11; i++) {
    remainArrows -= bestResult[i];
  }
  for (let i = 0; remainArrows > 0 && i < 11; i++) {
    // 이미 이길 수 있는 점수는 넘어감
    if (info[i] < bestResult[i]) {
      continue
    }
    // 이기기위해 필요한 화살수보다 더 쐈다면 조정
    let arrowsRequired = info[i] + 1;
    if (remainArrows >= arrowsRequired - bestResult[i]) {
      bestResult[i] = arrowsRequired;
      remainArrows -= (arrowsRequired - bestResult[i]);
    }
  }
  // 이겼는데 화살이 남았다면 적절히 분배해야함
  if (remainArrows > 0) {
    bestResult[10] += remainArrows;
  }

  // 이길 수 없거나 비기면 -1반환
  if (maxDifference <= 0) {
    return [-1];
  }

  return bestResult;
}
