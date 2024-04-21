arr = [6, 20, 3, 8, 15, 10]

max_index = 0
# 리스트를 순회하면서 최댓값의 인덱스를 찾음
for i in range(1, len(arr)):
    if arr[i] > arr[max_index]:
        max_index = i

min_index = 0
# 리스트를 순회하면서 최솟값의 인덱스를 찾음
for j in range(1, len(arr)):
    if arr[j] < arr[min_index]:
        min_index = j

print(f'인덱스:{max_index}, 최댓값: {arr[max_index]}')  # 인덱스 : 1, 최댓값 : 20
print(f'인덱스:{min_index}, 최솟값: {arr[min_index]}')  # 인덱스 : 2, 최솟값 : 3
