while True:
    n = int(input())
    arr = []

    if n == -1:
        break

    for i in range(1,n):
       if n%i == 0 :
           arr.append(i) # 약수를 리스트에 추가

    if sum(arr) == n: # 약수의 합이 n과 같으면, 완전수
        print(n, '=', ' + '.join(map(str, arr))) # + 구분자로 배열을 합치는 join
    else: # 완전수가 아님
        print(n, 'is NOT perfect.')    