def fibonacci(n):
    """
    O(2^n)
    """
    if n <= 1:
        return n
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)

def linear_fibonacci(k):
    """
    O(n)
    피보나치 수열의 k번째 항을 구하는 함수
    """
    if k<=1:
        return k, 0
    else:
        (a, b) = linear_fibonacci(k - 1)
        return a + b, a


if __name__ == "__main__":
    print(fibonacci(10))  # 55
    print(fibonacci(20))  # 6765

    print(linear_fibonacci(10)) # 55

