N = int(input())

for i in range(N):
    arr = list(map(int, input().split()))

    peoples = arr[0]
    scores = arr[1:]
    count = 0
    
    avg = sum(scores) / peoples
    
    for score in scores:
        if score > avg:
            count += 1
    result = count / peoples * 100
    
    print(f"{result:.3f}%")