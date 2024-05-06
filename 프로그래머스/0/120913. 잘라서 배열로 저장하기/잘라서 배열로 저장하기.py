def solution(my_str, n):
    answer = []
    for i in range(0, len(my_str), n):
        answer.append(my_str[i:i+n]) # n개씩 끊어서 answer에 추가
    return answer