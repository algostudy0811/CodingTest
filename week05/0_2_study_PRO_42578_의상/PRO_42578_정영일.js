function solution(clothes) {
  // 모든 옷 종류는 안입기, 하나 고르기의 경우가 있으므로 갯수 + 1의 가짓수
  // 모든 옷들을 안입게되는 경우 하나를 빼줘야 한다.

  // 옷 종류별로 갯수 설정
  let closet = {};
  for (let [_, type] of clothes) {
    closet[type] = (closet[type] || 0) + 1;
  }

  // 각 종류별 (갯수 + 1)을 모두 곱한 뒤, 아무것도 입지 않은 경우를 제외
  return Object.values(closet).reduce((acc, count) => acc * (count + 1), 1) - 1;
}