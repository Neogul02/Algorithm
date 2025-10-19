def bad_fibonacci(n):
    """
    O(2^n)
    """
    if n <= 1:
        return n
    else:
        return bad_fibonacci(n - 1) + bad_fibonacci(n - 2)

def good_fibonacci(n):
    if n<=1:
        return (n,0)
    else:
        (a, b) = good_fibonacci(n - 1)
        return (a + b, a)


if __name__ == "__main__":
    print(bad_fibonacci(10))  # 55
    print(bad_fibonacci(20))  # 6765

    print(good_fibonacci(10))  # (55, 34)
    print(good_fibonacci(20))  # (6765, 4181)


