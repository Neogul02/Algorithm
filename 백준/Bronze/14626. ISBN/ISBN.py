def find_missing_digit(isbn):
    missing_index = isbn.index('*')

    weights = [1 if i % 2 == 0 else 3 for i in range(12)]

    total = 0
    for i in range(12):
        if i == missing_index:
            continue
        total += int(isbn[i]) * weights[i]

    check_digit = int(isbn[-1])
    for missing_digit in range(10):
        if (total + missing_digit * weights[missing_index] + check_digit) % 10 == 0:
            return missing_digit

print(find_missing_digit(input()))