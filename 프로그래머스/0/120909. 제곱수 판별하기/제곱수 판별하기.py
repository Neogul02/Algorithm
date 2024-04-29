def solution(n):
    answer = 0
    for i in range(1, 1000001):
        if n == i**2:
            answer = 1
            break
        else:
            answer = 2
    return answer