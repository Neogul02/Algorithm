N = int(input())
numbers = map(int, input().split())

max_streak = 0
current_streak = 0

for num in numbers:
    if num != 0:
        current_streak += 1
        max_streak = max(max_streak, current_streak)
    else:
        current_streak = 0

print(max_streak)  