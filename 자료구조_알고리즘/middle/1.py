import random

# 1-1
def is_multiple(n, m):
    if m % n == 0:
        return True
    else:
        return False

# 1-2 n곱셈 모듈러 나눗셈 연산없이 짝수 홀수를 반환하는 함수
def is_even(n):



# 1-3 : 시퀀스를 받아 최솟값과 최댓값을 튜플로 반환 no use of min() and max()
def minmax(data):
    if len(data) == 0:
        return None
    min_val = data[0]
    max_val = data[0]
    for num in data:
        if num < min_val:
            min_val = num
        if num > max_val:
            max_val = num
    return (min_val, max_val)

# 1-4
def sum_of_squares(n):
    sum = 0
    for i in range(1, n):
        sum += i * i
    return sum

# 1-5 : 1-4 use list comprehension and sum function
def sum_of_squares_lc(n):
    return sum([i * i for i in range(1, n)])

# 1-6 : n 홀수 sum
def sum_of_odd(n):
    sum = 0
    for i in range(1, n, 2): # 1,3,5,...
        sum += i
    return sum

# 1-7 : 1-6 use list comprehension and sum function
def sum_of_odd_lc(n):
    return sum([i for i in range(1, n, 2)])

# 1-8
###
# 파이썬에서는 음의 정수를 문자열 같은 시퀀스의 인덱스로 사용할 수 있다. 문자열
# s의 길이가 n이고 n<K< 0을 만족하는 K에 대해 표현식 S[K]가 있다고 하자.
# 이 때 0보다 같거나 큰 j를 인덱스로 한 표현식 S[j]가 s[K]와 동일한 원소를 가리킨다면
# j와 k는 어떤 관계인가?

# -> 인덱싱 결과가 같은 요소 객체/자리
# j = n + k
# j = len(s) + k
###

# 1-9
test = range(50, 81, 10) # 50, 60, 70, 80
print(f'test: {list(test)}')

# 1-10
test = range(8, -9, -2) # 8, 6, 4, 2, 0, -2, -4, -6, -8
print(f'test: {list(test)}')

# 1-11 : use list comprehension to [1,2,4,8,16,32,64,128,256]
test = [2**i for i in range(9)]
print(f'test: {test}')

# 1-12 :
def choice(data):
    if len(data) == 0:
        return None
    index = randrange(len(data))
    return data[index]

# 1-13
def reverse(data):
    # pseudocode
    left = 0
    right = len(data) - 1
    while left < right:
        data[left], data[right] = data[right], data[left]
        left += 1
        right -= 1
    return data

# 1-14
def isodd(int_arr):
    odd_arr = []
    for i in int_arr:
        if i % 2 != 0:
            odd_arr.append(i)
    if len(odd_arr) >= 2:
        return True
    else:
        return False

# 1-15
def iseverydiff(int_arr):
    isarr = []
    for i in int_arr:
        if i in isarr:
            return False
        else:
            isarr.append(i)

# 1-16 17 is 58p

# 1-18 [0,2,6,12,20,30,42,56,72,90]
test = [i*(i+1) for i in range(10)]
print(f'test: {test}')

# 1-19 ['a', 'b', 'c', ... 'z']
test = [chr(i) for i in range(ord('a'), ord('z')+1)]
print(f'test: {test}')

# 1-20 use randint
def suffle(arr):
    n = len(arr)
    for i in range(n-1, -1, -1): # 5 4 3 2 1 0
        j = random.randint(0, n - 1)
        arr[i], arr[j] = arr[j], arr[i]
    return arr
print(f'suffle: {suffle([1,2,3,4,5])}')

# 1-21 EOF error
def reverse_input():
    lines = []
    while True:
        try:
            line = input()
            lines.append(line)
        except EOFError:
            break
    for line in reversed(lines):
        print(line)
print("Enter lines (Ctrl+D to end):")

# 1-22
def abc(a, b):
    c = []
    for i in range(len(a)):
        c.append(a[i] * b[i])
    return c
print(f'abc: {abc([1,2,3], [4,5,6])}')

# 1-23
def set_item(lst, index, value):
    try:
        lst[index] = value
    except IndexError:
        print("Don't try buffer overflow attacks in Python!")

# 1-24 모음개수 aeiou
def count_vowels(s):
    vowels = 'aeiouAEIOU'
    count = 0
    for char in s:
        if char in vowels:
            count += 1
    return count

# 1-25
def remove_dot(s):
    result = ''
    for ch in s:
        if ch.isalpha() or ch==' ':  # 알파벳과 공백만 유지
            result += ch
    return result

print(remove_dot("Let's try, Mike"))

# 1-26
def sansul(a,b,c):
    if a+b==c or a+c==b or a*b==c:
        return True
    else:
        return False

print(f'sansul: {sansul(2,3,5)}')

# 1-27 76p

# 1-28: p-norm
def norm(v):
    sum = 0
    for i in v:
        sum += i * i
    return sum ** 0.5

print(f'norm: {norm([3,4])}')

# 1-29: 'catdog'
def catdog():
    letters = ['c', 'a', 't', 'd', 'o', 'g']
    for i in letters:
        for j in letters:
            print(i + j)

# 1-30
def divied_2(n):
    cnt = 0
    while True:
        if n < 2:
            break
        n = n / 2
        cnt += 1
    return cnt

print(f'divied_2: {divied_2(40)}')

# 1-30 : 거스름돈
def make_change(how, money):

    change = {}
    amount = money - how
    if amount > 50000:
        amount = amount % 50000
        change[50000] = amount // 50000
    if amount > 10000:
        amount = amount % 10000
        change[10000] = amount // 10000
    if amount > 5000:
        amount = amount % 5000
        change[5000] = amount // 5000
    if amount > 1000:
        amount = amount % 1000
        change[1000] = amount // 1000
    if amount > 500:
        amount = amount % 500
        change[500] = amount // 500
    if amount > 100:
        amount = amount % 100
        change[100] = amount // 100
    if amount > 50:
        amount = amount % 50
        change[50] = amount // 50
    if amount > 10:
        amount = amount % 10
        change[10] = amount // 10

    return change

print(f'make_change: {make_change(87680, 200000)}')
# main
