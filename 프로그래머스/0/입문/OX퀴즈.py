def solution(quiz):
    answer = []
    # [연산자]는 + 와 - 중 하나입니다.
    for q in quiz:
        left, right = q.split('=')
        left = left.split()
        print(left)
        if left[1] == '+': # 더하기 연산
            if int(left[0]) + int(left[2]) == int(right):
                answer.append('O')
            else:
                answer.append('X')
        elif left[1] == '-': # 빼기 연산
            if int(left[0]) - int(left[2]) == int(right):
                answer.append('O')
            else:
                answer.append('X')
    return answer


print(solution(["3 - 4 = -3", "5 + 6 = 11"])) # ["X", "O"]