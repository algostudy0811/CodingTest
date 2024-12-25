function solution(picks, minerals) {
  // 총 피로도
  let tired = 0;

  // 총 곡괭이
  let pickEA = picks.reduce((sum, now) => sum + now, 0)
  console.log(pickEA)
  // 광물을 5개씩 그룹화, 곡괭이가 있는 데까지만
  let groups = [];
  for (let i = 0; i < pickEA * 5; i += 5) {
    groups.push(minerals.slice(i, i + 5));
  }

  // 광물별 임의 난이도 설정, 그룹별 난이도 표시
  let levels = []
  groups.forEach((group, idx) => {
    let level = 0;
    for (mineral of group) {
      switch (mineral) {
        case "diamond":
          level += 25;
          break;
        case "iron":
          level += 5;
          break;
        case "stone":
          level += 1;
          break;
      }
    }
    levels.push([level, idx]);
  });

  // 레벨순 정렬, 인덱스값 참조가능하도록
  levels.sort((a, b) => b[0] - a[0])

  // 현재 파고 있는 그룹
  let nowPiking = 0;

  // 곡괭이 순회
  picks.forEach((pick, idx) => {
    // 그 단계 곡괭이를 다 쓸때까지 반복
    while (pick > 0 && nowPiking < levels.length) {

      // 지금 파는 광물그룹 지정
      let groupNow = groups[levels[nowPiking][1]];

      // 인덱스값이 곡괭이 종류
      switch (idx) {
        // 다이아는 모든게 1이므로 광석 갯수가 피로도
        case 0:
          tired += groupNow.length;
          break;
        // 철은 다이아만 5
        case 1:
          for (mineral of groupNow) {
            if (mineral === "diamond") {
              tired += 5;
            } else {
              tired += 1;
            }
          }
          break;
        // 돌은 다이아 25 철 5 돌 1
        case 2:
          for (mineral of groupNow) {
            if (mineral === "diamond") {
              tired += 25;
            } else if (mineral === "iron") {
              tired += 5;
            } else {
              tired += 1;
            }
          }
          break;
      }
      if (pick <= 1 || nowPiking < levels.length)
        nowPiking += 1
      pick -= 1
    }
  });

  // 최종 피로도 반환
  return tired;
}