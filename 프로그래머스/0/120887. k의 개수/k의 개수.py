def solution(i, j, k):
    cnt = 0
    for a in range(i,j+1):
        for b in range(len(str(a))):
            if str(a)[b] == str(k):
                cnt += 1
    return cnt