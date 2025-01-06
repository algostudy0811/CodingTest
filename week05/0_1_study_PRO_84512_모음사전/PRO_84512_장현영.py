"""
풀이시간 : 30분
aeiou

a0000:1
aa000:2
aaa00:3
aaaa0:4
----
aaaaa:5
aaaae:6
aaaai:7
aaaao:8
aaaau:9
----
aaae0:10
aaaea:11
aaaei:12
aaaeo:13
aaaeu:14
---

"""
from itertools import product

vowels = ['A', 'E', 'I', 'O', 'U']

words = []
for i in range(1, 6):
    for p in product(vowels, repeat=i):
        words.append(''.join(p))

#print(words[:20])
words.sort()
#print(words[:20])

def solution(word):
    return words.index(word)+1

# 테스트
print(solution("AAAAE"))
print(solution("AAAE"))
print(solution("I"))
print(solution("EIO"))