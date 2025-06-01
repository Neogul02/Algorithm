def decimal_to_base(n, b):
    digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    result = ""

    while n > 0:
        result = digits[n % b] + result
        n //= b

    return result
n, b = map(int, input().split())
print(decimal_to_base(n, b))