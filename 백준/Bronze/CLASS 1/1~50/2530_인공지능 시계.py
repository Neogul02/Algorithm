H, M, S = map(int, input().split())
sec = int(input())
S += sec % 60
if S >= 60:
    S -= 60
    M += 1
M += (sec//60) % 60
if M >= 60:
    M -= 60
    H += 1
H += ((sec//60)//60) % 24
if H >= 24:
    H -= 24
print(H, M, S)
