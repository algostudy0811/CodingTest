"""
완전탐색
product 중복함수 활용해서 문제 구하기
"""

from itertools import product


def solution(users, emoticons):
    answer = []
    sales = [10, 20, 30, 40]

    for case in product(sales, repeat=len(emoticons)):  # 할인율 적용 모든 경우
        result = [0, 0]  # [가입자 수, 구매 가격]
        for user in users:
            temp = 0  # user 지불 비용
            for idx, sale in enumerate(case):
                if sale >= user[0]:  # 이모티콘 할인율이 유저가 원하는 할인율 이상이면 구매
                    temp += emoticons[idx] * (100 - sale) // 100

            if temp >= user[1]:  # 유저가 생각하는 예산보다 초과하는 경우 이모티콘 플러스에 가입
                result[0] += 1  # result에 이모티콘 플러스 가입자 카운트 +1
            else:
                result[1] += temp  # 이모티콘 플러스에 가입하지 않는다면 result에 이모티콘 구매 가격 누적

        answer.append(result)

    answer.sort(key=lambda x: (-x[0], -x[1]))  # 가입자 최대, 판매액 최대순

    return answer[0]