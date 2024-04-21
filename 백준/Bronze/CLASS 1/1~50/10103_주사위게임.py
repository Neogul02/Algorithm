#첫째 줄에 라운드의 수 n (1 ≤ n ≤ 15)가 주어진다. 다음 n개 줄에는 두 정수가 주어진다. 첫 번째 정수는 그 라운드에서 창영이의 주사위에 나타난 숫자, 
# 두 번째 정수는 상덕이의 주사위에 나타난 숫자이다. 두 정수는 항상 1보다 크거나 같고, 6보다 작거나 같다.
max_num = int(input())
man1, man2 = [100,100]
if max_num <= 15 and max_num >= 1:
    for i in range(max_num):
        num1, num2 = map(int, input().split())
        if 1 <= num1 <= 6 and 1 <= num2 <= 6:
            if num1 > num2 :
                man2 -= num1
            elif num2 > num1 :
                man1 -= num2
            else :
                continue
print(f'{man1}\n{man2}')