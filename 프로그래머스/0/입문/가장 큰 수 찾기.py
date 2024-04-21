def solution(array):
    max_num = max(array)
    return [max_num,array.index(max_num)]


print(solution([1, 8, 3])) # [8, 1]
