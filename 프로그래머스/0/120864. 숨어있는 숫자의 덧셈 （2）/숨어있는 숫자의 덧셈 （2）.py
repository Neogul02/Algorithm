def solution(my_string):
    s = ''

    for i in my_string:
        if i.isdigit():
            s += i
        else:
            s += ' '
    print(s.split())
    
    return sum(map(int, s.split()))