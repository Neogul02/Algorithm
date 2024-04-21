# 상근이의 남자 친구의 수와 여자 친구의 수가 주어졌을 때, 친구는 총 몇 명인지 구하는 프로그램을 작성하시오.
num1, num2 = map(int, input().split())
while num1 != 0 and num2 != 0:
    print(num1+num2)
    num1, num2 = map(int, input().split())
