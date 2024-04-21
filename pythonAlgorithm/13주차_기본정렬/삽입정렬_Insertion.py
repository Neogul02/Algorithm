# def findInsertIdx(arr, data):
#     findIdx = -1
#     for i in range(0, len(arr)):
#         if arr[i] > data:
#             findIdx = i
#             break
#     if findIdx == -1:
#         return len(arr)
#     else:
#         return findIdx

# ## 전역변수 ##
# before = [188, 162, 168, 120, 50, 150, 177, 105]
# after = []

# ## 메인코드 ##
# if __name__ == "__main__":
#     print('정렬 전 -->', before)
#     for i in range(len(before)):
#         data = before[i]
#         insPos = findInsertIdx(after, data)
#         after.insert(insPos, data)
#     print('정렬 후 -->', after)

def insertionSort(arr):

    for end in range(1, len(arr)):
        for cur in range(end, 0, -1):
            if arr[cur-1] > arr[cur]:
                arr[cur-1], arr[cur] = arr[cur], arr[cur-1] # swap (자리 바꾸기)
    return arr

dataArr = [188, 162, 168, 120, 50, 150, 177, 105]

print('정렬 전 -->', dataArr)
dataArr = insertionSort(dataArr)
print('정렬 후 -->', dataArr)