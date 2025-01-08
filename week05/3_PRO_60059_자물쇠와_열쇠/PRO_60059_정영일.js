// 열쇠를 회전하면서 각 점에 대입해보고 결과 반환하기
// 열쇠가 자물쇠를 넘어서도 대볼수 있으므로 자물쇠를 확대해야 할 듯
// N>=M 이므로 자물쇠를 3배만 확대하면 열쇠가 자유롭게 돌아갈 수 있을 것 -> 검사는 가운데 부분만

function solution(key, lock) {
  // 크기 변수 지정
  const m = key.length;
  const n = lock.length;

  // 열쇠 90도 회전
  function keyRotate(key) {
    const n = key.length;
    // 회전 배열 생성
    const rotated = Array.from({ length: n }, () => Array(n).fill(0));

    // 회전된 위치로 값 복사
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        rotated[j][n - 1 - i] = key[i][j];
      }
    }
    // 회전된 배열 반환
    return rotated;
  }

  // 자물쇠가 열리는지 확인
  function check(newLock) {
    const n = newLock.length;

    // 자물쇠의 중앙 영역이 모두 1인지 확인
    for (let i = n / 3; i < (2 * n) / 3; i++) {
      for (let j = n / 3; j < (2 * n) / 3; j++) {
        // 홈이 안메꿔진 곳 / 돌기가 겹치는 곳이 있다면 실패
        if (newLock[i][j] !== 1) {
          return false
        }
      }
    }
    return true;
  }

  // 자물쇠 크기 3배 확장
  const newLock = Array.from({ length: 3 * n }, () => Array(3 * n).fill(0));

  // 기존 자물쇠를 중앙에 배치
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      newLock[n + i][n + j] = lock[i][j];
    }
  }

  // 열쇠를 4가지 방향으로 회전하며 모든 위치에서 검사
  for (let r = 0; r < 4; r++) {
    key = keyRotate(key); // 열쇠 회전

    // 자물쇠 영역을 포함하여 열쇠를 이동하며 검사
    for (let x = 0; x <= 2 * n; x++) {
      for (let y = 0; y <= 2 * n; y++) {
        // 열쇠를 자물쇠 위에 놓기
        for (let i = 0; i < m; i++) {
          for (let j = 0; j < m; j++) {
            newLock[x + i][y + j] += key[i][j];
          }
        }

        // 자물쇠가 열리는지 확인
        if (check(newLock)) {
          return true;
        }

        // 열쇠를 자물쇠에서 제거
        for (let i = 0; i < m; i++) {
          for (let j = 0; j < m; j++) {
            newLock[x + i][y + j] -= key[i][j];
          }
        }
      }
    }
  }

  return false; // 어떤 경우에도 열리지 않으면 false 반환
}