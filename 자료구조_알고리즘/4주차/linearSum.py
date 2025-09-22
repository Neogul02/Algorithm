def linear_sum(A, n):
    print(f"A: {A}, n: {n}")
    if n == 1:
        return A[0]
    else:

        return linear_sum(A, n - 1) + A[n - 1]

if __name__ == "__main__":
    A = [1, 2, 3, 4, 5]
    print(linear_sum(A, len(A)))  # 15