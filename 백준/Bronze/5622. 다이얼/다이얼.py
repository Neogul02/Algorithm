word = input()
dial = ['ABC', 'DEF', 'GHI', 'JKL', 'MNO', 'PQRS', 'TUV', 'WXYZ']

time = 0

for i in word:
    for j in range(len(dial)):
        if i in dial[j]:
            time += j + 3
print(time)