// 첫 시도 -> 값은 맞게 나오지만 시간 초과 문제
// level을 1씩 증가시키는게 원인인 것 같음. level을 이진탐색 해야할 듯

function solution(diffs, times, limit) {
  // 레벨 수준, 사용한 시간 표시
  let level = 0;
  
  // 제한시간보다 큰경우 계속 돌림
  while (true) {
      // 레벨 조정, 사용 시간 초기화
      level ++;
      let time_used = 0;
      // 난이도 배열 순회
      for (let i=0 ; i<diffs.length; i++) {
          // 이미 사용 시간이 제한을 초과하면 끝내기
          if (diffs[i] <= level) {
              time_used += times[i]
          } else {
              // 틀리는 횟수, 그만큼 전 문제 반복 + 이번문제 시간 소모
              fails = diffs[i]-level
              time_used += (times[i-1] + times[i]) * fails + times[i]
          }
          // 소모 시간이 이미 제한을 넘었으면 반복 종료
          if (time_used > limit) {
           break   
          }
      }
      // 제한시간과 소모 시간이 같거나 작아질 때 while 종료
      if (time_used <= limit) {
          break
      }
  }
  return level;
}

// 이진탐색 적용
function solution(diffs, times, limit) {
  // 전체 길이
  const n = diffs.length;
  
  // 가능한지 판별하는 함수 생성
  function checkLevel(level) {
      // 시간 소모 표시
      let time_used = 0;
      // 난이도 배열 순회
      for (let i = 0 ; i < n; i++) {
          // 이미 사용 시간이 제한을 초과하면 끝내기
          if (diffs[i] <= level) {
              time_used += times[i];
          } else {
              // 틀리는 횟수, 그만큼 전 문제 반복 + 이번문제 시간 소모
              time_used += (times[i-1] + times[i]) * (diffs[i]-level) + times[i];
          }
          // 소모 시간이 이미 제한을 넘었으면 반복 종료
          if (time_used > limit) {
           return false;   
          }
      }
      // 제한을 넘지 않고 for문이 종료되면 true반환
      return true;
  };
  
  // 레벨 수준 이진탐색
  let low = 1;
  let high = 100000;
  let answer = high;
  
  while (low <= high) {
      let mid = Math.floor((low + high) / 2);
      
      if (checkLevel(mid)) {
          answer = mid;
          high = mid - 1;
      } else {
          low = mid + 1;
      }
  }
  
  return answer;
}

// 원래 high의 값을 Math.max(...diffs)로 지정했지만, 이건 배열이 길어지면 런타임 오류랍니다
