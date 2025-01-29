def solution(str1, str2):
    answer = []
    for i in range(len(str1)):
        answer.append(str1[i])
        answer.append(str2[i])

    return ''.join(answer)