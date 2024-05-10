def solution(polynomial):
    answer = ''
    sum = 0
    num = 0
    polynomial = polynomial.replace(' ', '').split('+')
    for e in polynomial :
        if e[-1] == 'x':
            num += 1 if len(e) == 1 else int(e[:-1:])
        else :
            sum += int(e)
    if num>0:
        answer = (str(num) if num>1 else '')+'x'+(' + ' if sum>0 else '')
    answer += (str(sum) if sum>0 else '')
    return answer