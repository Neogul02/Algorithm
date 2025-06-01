import sys
input = sys.stdin.readline

arr = []
previous_direction = None

while True:
    user_input = input().strip()
    if user_input == "99999":
        break
    first_two_digits = [int(user_input[0]), int(user_input[1])]
    direction_sum = sum(first_two_digits)

    if direction_sum == 0:
        direction = previous_direction
    elif direction_sum % 2 == 0:
        direction = "right"
    else:
        direction = "left"

    steps = int(user_input[2:])
    previous_direction = direction

    print(f'{direction} {steps}')