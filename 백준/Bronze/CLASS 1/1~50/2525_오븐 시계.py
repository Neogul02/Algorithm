h, s = input().split()
time = input()
h = int(h)
s = int(s)
time = int(time)

h = h + (time // 60)
s = s + (time % 60)

if s >= 60:
    h = h+1
    s = s-60
if h >= 24:
    h = h-24

print(f'{h} {s}')
