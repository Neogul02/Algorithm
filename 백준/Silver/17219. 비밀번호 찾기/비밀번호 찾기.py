import sys

N, M = map(int, sys.stdin.readline().strip().split())

password_dict = {}
for _ in range(N):
    site, password = sys.stdin.readline().strip().split()
    password_dict[site] = password

for _ in range(M):
    query_site = sys.stdin.readline().strip()
    print(password_dict[query_site])