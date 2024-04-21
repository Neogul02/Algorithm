def solution(cipher, code):
    cipher = list(cipher)
    answer = [cipher[i*code-1] for i in range(1, (len(cipher)//code)+1)]
    return ''.join(answer)


print(solution('dfjardstddetckdaccccdegk', 4))  # attack
