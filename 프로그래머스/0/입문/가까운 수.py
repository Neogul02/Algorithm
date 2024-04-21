def solution(array, n):
    copy = []
    for i in array:
        copy.append(abs(i - n))

    minimum = min(copy)  # 절댓값의 최솟값

    sames = []  # 만약 같은 절댓값이 여러개라면
    for i in range(len(array)):
        if copy[i] == minimum:
            sames.append(array[i])
    return min(sames)


print(solution([-5, 5], 0))
