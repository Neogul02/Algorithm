def solution(order):
    answer = 0
    arr = list(str(order))
    for i in arr:
        if int(i) % 3 == 0 and int(i) != 0:
            answer += 1

    return answer


print(solution(29423))  # 2