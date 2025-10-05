N = int(input())
guguarr = set()
for i in range(10):
    guguarr.add(i)
    for j in range(10):
        guguarr.add(j)
        guguarr.add(i*j)

if N in guguarr:
    print(1)
else: print(0)