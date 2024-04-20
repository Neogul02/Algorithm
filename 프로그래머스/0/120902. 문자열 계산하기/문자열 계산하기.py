def solution(my_string):
    s_list = my_string.split(" ")
    answer = int(s_list[0])

    for i in range(1, len(s_list), 2):
        if s_list[i] == "+":
            answer += int(s_list[i+1])
        elif s_list[i] == "-":
            answer -= int(s_list[i+1])

    return answer
