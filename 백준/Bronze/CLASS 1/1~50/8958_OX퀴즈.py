max_num = int(input())
n = 0
while n < max_num:
    result, grade = 0, 0
    score_list = list(input())
    for i in score_list:  # ['O', 'X', 'X', 'O', ...]
        if i == 'O':
            grade += 1 
            result += grade
        else:
            grade = 0
    print(result)
    n += 1
