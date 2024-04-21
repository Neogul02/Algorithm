T = int(input())

for _ in range(T):
    school = []
    drink = []
    N = int(input())

    for _ in range(N):
        S, L = input().split()
        school.append(S)
        drink.append(int(L))
    
    max_drink_idx = 0

    for i in range(1, len(drink)): # 1부터 시작하는 이유는 0번째 인덱스를 max_drink_idx로 두고 시작하기 때문
        if drink[i] > drink[max_drink_idx]:
            max_drink_idx = i

    print(school[max_drink_idx])