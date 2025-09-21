N = int(input())
max_prize = 0

for _ in range(N):
    dice = list(map(int, input().split()))
    numbers = [0] * 7

    for i in dice:
        numbers[i] += 1

    most_count = max(numbers)
    most_num = numbers.index(most_count)

    if most_count == 4:
        prize = 50000 + most_num * 5000
    elif most_count == 3:
        prize = 10000 + most_num * 1000
    elif most_count == 2:
        if numbers.count(2) == 2:
            nums = [i for i in range(1, 7) if numbers[i] == 2]
            prize = 2000 + nums[0] * 500 + nums[1] * 500
        else:
            prize = 1000 + most_num * 100
    else:
        prize = max(dice) * 100

    max_prize = max(max_prize, prize)

print(max_prize)