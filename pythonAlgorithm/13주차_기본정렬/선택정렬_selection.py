def selectionSort(arr):
    for i in range(len(arr)-1): #len(arr) = 8 
        minIdx = i # 처음 index를 최솟값이라고 가정
        for k in range(i+1, len(arr)): # 두번째 요소부터 끝까지
            if arr[minIdx] > arr[k]:
                minIdx = k
        temp = arr[i]
        arr[i] = arr[minIdx]
        arr[minIdx] = temp

    return arr

dataArr = [188, 162, 168, 120, 50, 150, 177, 105]
print(len(dataArr))

print('정렬 전 -->', dataArr)

dataArr = selectionSort(dataArr)
print('정렬 후 -->', dataArr)

