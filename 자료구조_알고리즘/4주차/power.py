
def power(x,n):
    """
    O(n)
    x의 n번 곱셈을 수행
    """
    if n == 0:
        return 1
    else:
        return x * power(x, n - 1)


def power2(x,n):
    """
    O(log n)
    메소드를 2번 호출하는것보다, 변수를 두 번 호출하는것이 더 효율적이다.
    """
    if n == 0:
        return 1
    elif n % 2 == 0: # n is even
        partial = power2(x, n // 2) # 지수를 반으로 나눈 뒤 제곱
        return partial * partial
    else: # n is odd
        return x * power2(x, n - 1)


if __name__ == "__main__":
    print(power(2, 10))  # 1024
    print(power2(2, 10))  # 1024