def solution(array):
    return sum(str(num).count('7') for num in array)
