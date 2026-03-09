def find(x):
    # 루트 찾기
    root = x
    while parents[root] != root:
        root = parents[root]
    
    # 경로 압축
    while parents[x] != root:
        parents[x], x = root, parents[x]
    
    return root

def union(a, b):
    a = find(a)
    b = find(b)
    if a < b:
        parents[b] = a
    else:
        parents[a] = b

N, M = map(int, input().split())

parents = [i for i in range(N + 1)]
cmds = [list(map(int, input().split())) for _ in range(M)]

for cmd in cmds:
    if cmd[0] == 0:
        union(cmd[1], cmd[2])
    else:
        print("YES" if find(cmd[1]) == find(cmd[2]) else "NO", end="\n")