import sys

N = int(sys.stdin.readline().strip())
N_s = list(map(int, sys.stdin.readline().strip().split()))
M = int(sys.stdin.readline().strip())
M_s = list(map(int, sys.stdin.readline().strip().split()))

n_dic = {}

for n in N_s:
  if n in n_dic:
    n_dic[n] += 1
  else:
    n_dic[n] = 1

for m in M_s:
  if m in n_dic:
    print(n_dic[m], end=' ')
  else:
     print(0, end=' ')