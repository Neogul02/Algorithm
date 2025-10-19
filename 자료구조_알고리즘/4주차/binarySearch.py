def binary_search(data, target, low, high):
    """ 이진 탐색 알고리즘
    :param data: 찾을 리스트
    :param target: 찾을 값
    :param low: 최소 위치 인덱스
    :param high: 최대 위치 인덱스
    """
    if low > high: # 탐색 실패 = 찾을 수 없다.
        print(f'{target} not found')
        return False
    else:
        mid = (low + high) // 2
        if data[mid] == target: # 탐색 성공 = 찾았다.
            return data[mid], mid
        elif data[mid] > target: # 왼쪽 부분 탐색
            return binary_search(data, target, low, mid - 1)
        else: # 오른쪽 부분 탐색
            return binary_search(data, target, mid + 1, high)


if __name__ == "__main__":
    data = list(range(1,100,3))

    print(binary_search(data, 94, 0, len(data) - 1))  # True
    print(binary_search(data, 4, 0, len(data) - 1))  # False