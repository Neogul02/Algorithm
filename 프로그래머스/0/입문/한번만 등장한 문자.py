def solution(s):
    answer = []

    for i in list(s):
        if s.count(i) == 1:
            answer.append(i)

    return ''.join(sorted(answer))


print(solution('hello'))
