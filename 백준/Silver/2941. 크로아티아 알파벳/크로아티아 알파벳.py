N = input()
alpha = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']

for a in alpha:
    N = N.replace(a, '*')
print(len(N))