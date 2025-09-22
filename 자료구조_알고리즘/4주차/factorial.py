# 5! = 5 * 4 * 3 * 2 * 1 = 5 * 4!
# 4! = 4 * 3 * 2 * 1 = 4 * 3!
# 3! = 3 * 2 * 1 = 3 * 2!
# 2! = 2 * 1 = 2 * 1!
# 1! = 1 * 0!
# 0! = 1

# N! = N * (N-1)!

def factorial(n):
    # Base case
    if n == 0:
        return 1
    else:
        return n * factorial(n - 1)

if __name__ == "__main__":
    print(factorial(5))  # 120
    print(factorial(0))  # 1