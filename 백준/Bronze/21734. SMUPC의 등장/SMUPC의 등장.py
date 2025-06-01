import sys
input = sys.stdin.readline

user_input = input()

for i in user_input:
    repeat = 0
    for j in str(ord(i)):
        repeat += int(j)
    print(repeat*i)