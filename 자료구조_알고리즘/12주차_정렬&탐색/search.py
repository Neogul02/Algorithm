# 순차탐색
def sequential_search(A, key, low, high):
    for i in range(low, high + 1):
        if A[i] == key:
            return i
    return -1  # Key not found

# 이진탐색
def binary_search(A, key, low, high):
    if low > high: # 역전 되면 종료 = 값이 없는 경우
        return -1
    middle = (low + high) // 2

    if A[middle] == key: # 탐색 성공
        return middle
    elif A[middle] < key: # 오른쪽 부분 탐색
        return binary_search(A, key, middle + 1, high)
    else: # 왼쪽 부분 탐색
        return binary_search(A, key, low, middle - 1)

# 반복적 이진탐색
def binary_search_iterative(A, key, low, high):
    while low <= high:
        middle = (low + high) // 2
        # middle = int(low+ (high - low) * (key-A[low]) / (A[high]-A[low]))  # 보간 탐색
        if A[middle] == key:
            return middle
        elif A[middle] < key:
            low = middle + 1
        else:
            high = middle - 1
    return -1  # Key not found


if __name__ == "__main__":
    data = [2, 3, 4, 10, 40, 50, 60, 70, 80, 90]
    key = 10

    # 순차탐색 테스트
    result = sequential_search(data, key, 0, len(data) - 1)
    if result != -1:
        print(f"Sequential Search: Element found at index {result}")
    else:
        print("Sequential Search: Element not found")

    # 이진탐색 테스트
    result = binary_search(data, key, 0, len(data) - 1)
    if result != -1:
        print(f"Binary Search: Element found at index {result}")
    else:
        print("Binary Search: Element not found")
