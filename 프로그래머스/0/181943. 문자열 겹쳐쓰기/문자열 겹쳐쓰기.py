def solution(my_string, overwrite_string, s):
    my_list = list(my_string)

    for i in range(len(overwrite_string)):
        my_list[s + i] = overwrite_string[i]

    answer = ''.join(my_list)
    return answer

print(solution("He11oWor1d", "lloWorl", 2))