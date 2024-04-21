num1, num2 = map(int, input().split())
while num1 != 0 and num2 != 0:
    if num2 % num1 == 0:
        print("factor")
    elif num1 % num2 == 0:
        print("multiple")
    else:
        print("neither")
    num1, num2 = map(int, input().split())
