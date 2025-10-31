import collections

def solution(clothes):
    cloth_dict = collections.defaultdict(int)

    for c, k in clothes:
        cloth_dict[k] += 1

    answer = 1
    for v in cloth_dict.values():
        answer *= (v + 1)
    return answer - 1