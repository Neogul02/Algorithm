# @는 3을 곱하고, %는 5를 더하며, #는 7을 빼는 연산자이다.
# 따라서, 화성에서는 수학 식의 가장 앞에 수가 하나 있고, 그 다음에는 연산자가 있다.

case = int(input())
for _ in range(case):
    mars = list(map(str, input().split(" ")))
    result = 0
    for i in range(len(mars)):
        if i == 0:
            result += float(mars[i])  # index 0 은 first_number
        else:
            if mars[i] == "#":
                result -= 7
            elif mars[i] == "%":
                result += 5
            elif mars[i] == "@":
                result *= 3
    print("%.2f" % result)
