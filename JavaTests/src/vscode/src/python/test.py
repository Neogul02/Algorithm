
# N^2번의 연산, 1초에 10^8번 수행 가능
# N^2 <= 10^8 이면 Accepted
N = int(input())

if N * N <= 10**8:
    print('Accepted')
else:
    print('Time limit exceeded')

