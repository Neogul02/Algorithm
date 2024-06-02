def solution(A, B):
    answer = 0
    arr = list(A)
    last_list = []
    if A == B:
        return 0
    for i in range(len(A)):

        last_list.append(arr.pop(-1))
        if ''.join(list(reversed(last_list))+arr) == B:
            return i+1

    return -1