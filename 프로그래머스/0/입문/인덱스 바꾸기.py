def solution(my_string, num1, num2):
    answer = list(my_string)
    answer[num1], answer[num2] = answer[num2], answer[num1]
    return ''.join(answer)

print(solution('hello',1,2)) # 'hlelo'