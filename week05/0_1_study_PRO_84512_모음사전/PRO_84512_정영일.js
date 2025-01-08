
// dfs로 들어가서 5자리가 단어 길이 제한.
// 메모이제이션으로 글자수 기록하기
let source = ["A", "E", "I", "O", "U"]
let dictionary = {};
let order = 1;

// 재귀함수로 5자리까지 순열 및 순서 값 배정
function fillDictionary(now) {
  if (now.length >= 5) {
    return
  }
  for (letter of source) {
    next = now + letter;
    dictionary[next] = order;
    order++;
    fillDictionary(next);
  }
}

// 함수 실행하여 사전 채우기
fillDictionary("");

// 결과 반환 -> 메모이제이션을 처음에 해놓으면 좋은데 프로그래머스는 함수를 계속 다시 실행하니까..
function solution(word) {
  return dictionary[word]
}