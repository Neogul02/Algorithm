N = int(input())
user_input = list(map(int, input().split()))

primes = []
for num in user_input:
    if num > 1:
        for i in range(2, num):
            if num % i == 0:
                break
        else:
            primes.append(num)

print(len(primes))