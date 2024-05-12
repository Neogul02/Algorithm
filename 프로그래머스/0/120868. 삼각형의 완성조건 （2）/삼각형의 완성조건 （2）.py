def solution(sides):
    sides.sort()
    answer = []

    for i in range(sides[1]-sides[0]+1, sides[1] + 1):  # 가장 긴 변이 [1]인 경우
        answer.append(i)

    for j in range(sides[1] + 1, sides[0] + sides[1]):
        answer.append(j)

    return len(answer)