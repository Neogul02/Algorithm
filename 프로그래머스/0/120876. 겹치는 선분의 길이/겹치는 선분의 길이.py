def solution(lines):
    arr = [0]*200
    for i in lines:
        for j in range(i[0]+100, i[1]+100):
            
            if arr[j] == 1 or arr[j] == 2:
                arr[j] = 2
            else:
                arr[j] = 1

    print(f'2의 개수: {arr.count(2)}')
    return arr.count(2)