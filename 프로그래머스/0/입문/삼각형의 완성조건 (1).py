# 삼각형을 만들 수 있다면 1, 만들 수 없다면 2
def solution(sides):
    return 1 if max(sides) < sum(sides) - max(sides) else 2


print(solution([199, 72, 222]))
