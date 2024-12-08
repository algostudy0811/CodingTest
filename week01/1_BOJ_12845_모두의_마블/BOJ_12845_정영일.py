#시간초과 실패한 코드    
# def mix_card(x):
#     global points
#     chosen = 100000
#     site = 0
#     for d in range(2):
#         if x+compare[d] < 0 or x+compare[d]>=len(cards) or cards[x] == cards[x+compare[d]] :
#             continue
#         elif cards[x+compare[d]] < cards[x]:
#             if chosen >= cards[x+compare[d]]:
#                 chosen = cards[x+compare[d]]
#                 site = compare[d]
#     points += cards[x]+cards[x+site]
#     cards.pop(x+site)
    

# if __name__ == "__main__" :
#     total = int(input())
#     cards = list(map(int,input().split()))
#     if len(cards) == 1 :
#         print(0)
#         exit()
#     max_num = max(cards)
#     compare = [-1,1]
#     points = 0
#     position = cards.index(max_num)

#     while True:
#         if len(cards) == 1:
#             break
#         elif position >= len(cards)-1:
#             position = cards.index(max_num)
#         elif cards[position] == max_num:
#             mix_card(position)
#         else :
#             position += 1
#     print(points)                



def max_point(cards):
    total_points = 0 
    while len(cards) > 1:
        max_card = max(cards)
        max_index = cards.index(max_card)
        if max_index == 0 :
            total_points += cards[max_index] + cards[max_index+1]
            cards.remove(cards[max_index+1])
        elif max_index == len(cards)-1 :
            total_points += cards[max_index] + cards[max_index-1]
            cards.remove(cards[max_index-1])
        else:
            if cards[max_index-1] > cards[max_index+1] :
                total_points += cards[max_index] + cards[max_index+1]
                cards.remove(cards[max_index+1])
            else:
                total_points += cards[max_index] + cards[max_index-1]
                cards.remove(cards[max_index-1])
    return total_points

if __name__ == "__main__":
    n = int(input())
    cards = list(map(int, input().split()))
    if n == 1 :
        print(0)
    else :
        print(max_point(cards))