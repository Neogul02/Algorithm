import sys

data = []
n = 30

# 동적 배열의 크기 변화
# 파이썬의 리스트는 공간을 여유롭게 할당한다!
for k in range(n):
    a = len(data)
    b = sys.getsizeof(data)
    print(f'Length: {a:3d}; Size in bytes: {b:4d}')
    data.append(None)