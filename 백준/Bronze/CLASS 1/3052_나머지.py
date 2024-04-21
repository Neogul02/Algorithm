arr = [int(input()) for i in range(10)]
print(len(set([i % 42 for i in arr])))