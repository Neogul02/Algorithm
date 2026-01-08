N = int(input())
arr = map(int, input().split())
B, C = map(int, input().split())

answer = N
for a in arr:
    rem = a - B
    if rem > 0:
        answer += (rem + C - 1) // C 

print(answer)