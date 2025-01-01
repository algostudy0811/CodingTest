function solution(h1, m1, s1, h2, m2, s2) {
  // 알람 횟수
  let alarm = 0;

  // 초당 침별 회전 틱
  const hour = 360 / 43200;
  const minute = 360 / 3600;
  const second = 360 / 60;

  // 초기 침 위치
  let startHour = ((h1 % 12 * 3600 + m1 * 60 + s1) * hour) % 360;
  let startMinute = ((m1 * 60 + s1) * minute) % 360;
  let startSecond = (s1 * second) % 360;

  // 진행 할 시간 초로 환산하여 계산
  let startTime = h1 * 3600 + m1 * 60 + s1
  let endTime = h2 * 3600 + m2 * 60 + s2
  let time = endTime - startTime;


  // 초기 겹침 확인
  if (startHour === startSecond || startMinute === startSecond) {
    alarm += 1
  }
  // 시간을 1초씩 진행하며 겹치는지 판별
  for (let i = 1; i <= time; i++) {
    // 이동한 침 각도 정보
    let nowHour = (startHour += hour) % 360;
    let nowMinute = (startMinute += minute) % 360;
    let nowSecond = (startSecond += second) % 360;
    console.log(nowHour, nowMinute, nowSecond)
    // 건드린 경우표시 -> 두 침 모두 건드렸을때 둘이 겹쳐있다면 한번만 세기 위해
    let hourTouch = false;
    let minuteTouch = false;
    // 처음에 겹쳐있었다면 처음 1초는 안셈 : 무조건 겹치게 되고, 초침이 제일 빠르게 치고나가니 세면 안됨
    if (alarm > 0 && i === 1) {
      continue;
    }
    // 겹침 확인
    if (nowSecond >= nowHour && Math.abs(nowSecond - nowHour) <= 6) {
      console.log(Math.abs(nowSecond - nowHour))
      hourTouch = true;
      alarm += 1;
    }
    if (nowSecond >= nowMinute && Math.abs(nowSecond - nowMinute) <= 6) {
      console.log(Math.abs(nowSecond - nowMinute))
      minuteTouch = true;
      alarm += 1;
    }
    // 시침, 분침이 겹쳐있다면 알람은 한번만
    if (hourTouch && minuteTouch) {
      if (nowMinute === nowHour) {
        alarm -= 1;
      }
    }
    // 이전 값 이동
    startHour = nowHour;
    startMinute = nowMinute;
    startSecond = nowSecond;
  }
  return alarm
}

// 테스트
console.log(solution(11, 59, 30, 12, 0, 0)) // 0

// 좀 이상함... 11시 58분 59초부터 11시 59분 0초까지 1초만에 겹치는데 1이 나와야함 왜??
// 각도가 330, 348, 354인데 초침이 가장 앞에있고 딱 1초 진행해서 안겹치는데 왜 1이 나와야하는지..
