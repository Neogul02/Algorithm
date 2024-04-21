def boubleSort(arr):
    n=len(arr)
    for i in range(n-1,0,-1):
        Changed=False
        for j in range(i):
            if arr[j]>arr[j+1]:
                arr[j],arr[j+1]=arr[j+1],arr[j] #swap
                Changed=True
        if not Changed:
            break
    return arr

dataArr = [50,105,200,30,10,40,90,80,100]

if __name__ == '__main__':
    print('정렬 전: ')
    print(dataArr)
    print('정렬 후: ')
    print(boubleSort(dataArr))
