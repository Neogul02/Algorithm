def solution(numlist, n):
    result = sorted(numlist,key = lambda x : (abs(x-n), n-x))
    return result