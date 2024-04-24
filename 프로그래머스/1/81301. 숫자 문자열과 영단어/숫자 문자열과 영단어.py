def solution(s):
    numstr_list = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    number_list = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    my_str = ''
    answer = ''
    for i in range(len(s)):
        if s[i].isnumeric(): # 숫자인 경우
            answer += s[i]
        else:                # 문자인 경우
            my_str += s[i]
            if my_str in numstr_list:
                answer += number_list[numstr_list.index(my_str)]
                my_str = ''
    return int(answer)