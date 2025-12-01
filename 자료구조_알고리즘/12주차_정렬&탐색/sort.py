def print_step(arr, val):
    print(f"Step {val:2d}: {arr}")

# 선택 정렬 알고리즘
# 최소값을 찾아서 앞으로 보내고 -> 반복
def selection_sort(A):
    n = len(A)
    for i in range(n-1): # i = 0, 1, 2 ... n-2
        least = i
        for j in range(i+1, n): # j = 1, 2, ... n-1
            if A[j] < A[least]: # i랑 j 비교
                least = j
        A[i], A[least] = A[least], A[i]
        print_step(A, i+1)
    return A

# 삽입 정렬 알고리즘
# 앞에서부터 차례대로 정렬된 부분과 비교하여 삽입
def insertion_sort(A):
    n = len(A)
    for i in range(1, n): # i = 1, 2, ... n-1
        key = A[i]
        j = i-1 # 하나 앞의 인덱스
        while j >= 0 and A[j] > key:
            A[j+1] = A[j]
            j -= 1
        A[j+1] = key
        print_step(A, i)
    return A

if __name__ == "__main__":
    data = [5, 3, 8, 4, 9, 1, 6, 2, 7]
    print("Original array:", data)
    sorted_data = selection_sort(data)
    print("Sorted array:", sorted_data)

    data = [5, 3, 8, 4, 9, 1, 6, 2, 7]
    print("\nOriginal array:", data)
    sorted_data = insertion_sort(data)
    print("Sorted array:", sorted_data)