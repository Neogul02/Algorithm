H, M = map(int, input().split())

if M < 45:
    H = H - 1
    M = M - 45 + 60
elif M >= 45:
    M = M - 45

if H < 0:
    H = H + 24
elif H == 24:
    H = 0

print(H, M)
