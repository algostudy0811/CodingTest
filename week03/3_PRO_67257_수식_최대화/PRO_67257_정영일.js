function solution(expression) {
  // 연산자는 최대 3가지, 순서를 미리 정해놓음
  let calculates = [
    ["*", "+", "-"],
    ["*", "-", "+"],
    ["+", "*", "-"],
    ["+", "-", "*"],
    ["-", "+", "*"],
    ["-", "*", "+"],
  ];

  // 각 연산자 우선순위별 계산을 해서 최대값과 비교
  let maxNum = 0;

  // 계산 함수 작성
  function calculating(expression, order) {
    // 숫자거나 / +,-,*중 하나거나 -> 매치해서 분리 및 리스트로 저장
    let line = /(\d+|[\+\-\*])/g;
    let tokens = expression.match(line);

    // 우선순위별 계산 진행
    order.forEach(sign => {
      while (tokens.includes(sign)) {
        let index = tokens.indexOf(sign);
        let valueA = Number(tokens[index - 1]);
        let valueB = Number(tokens[index + 1]);
        let result = 0;

        // 기호에 따라 계산
        switch (sign) {
          case "*":
            result = valueA * valueB;
            break;
          case "+":
            result = valueA + valueB;
            break;
          case "-":
            result = valueA - valueB;
            break;
        }

        //계산 후 리스트에서 제거
        tokens.splice(index - 1, 3, result);
      }
    });
    // 리스트가 하나 남으면 결과값
    return tokens[0];
  }

  // 순열들을 순회하며 실행 및 최대값 도출
  calculates.forEach(order => {
    let result = Math.abs(calculating(expression, order));
    if (result > maxNum) {
      maxNum = result;
    }
  });
  return maxNum
}