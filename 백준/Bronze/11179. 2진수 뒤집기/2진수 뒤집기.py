N = format(int(input()), 'b')
reversed_N = ''
for i in range(1,len(N)+1):
    reversed_N = reversed_N + N[-i]

print(int(reversed_N, 2))