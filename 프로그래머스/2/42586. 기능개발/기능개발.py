from math import ceil

def solution(progresses, speeds):
    days = [ceil((100 - p) / s) for p, s in zip(progresses, speeds)]

    result = []
    cur = days[0]
    count = 1

    for d in days[1:]:
        if d <= cur:
            count += 1
        else:
            result.append(count)
            cur = d
            count = 1

    result.append(count)
    return result
