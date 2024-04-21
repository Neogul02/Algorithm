def solution(num, k):

    num = list(str(num))

    for i in num:
        if i == str(k):
            index = num.index(i)+1
            break
        else:
            index = -1

    return index


print(solution(232443, 4)) # 3