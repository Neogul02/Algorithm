def solution(array, commands):
    answer = []
    for i, j, k in commands:
        
        one = array[i-1:j]
        two = sorted(one)
        three = two[k-1]
        answer.append(three)
        
    return answer