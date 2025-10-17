a = [1,2,3,4,5]
b = a
b[0] = 100

print(f'a: {a}, b: {b}')

# 깊은복사
c = a.copy()
c[0] = 100
print(f'a: {a}, b: {b}, c: {c}')

# 위치
print(id(a), id(b), id(c))