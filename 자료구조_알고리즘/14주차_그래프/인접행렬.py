vertex = list('ABCDEFGH')
adtMat = [[0, 1, 1, 0, 0, 0, 0, 0],
          [1, 0, 0, 1, 1, 0, 0, 0],
          [1, 0, 0, 1, 1, 0, 0, 0],
          [0, 1, 1, 0, 0, 1, 0, 0],
          [0, 0, 1, 0, 0, 0, 1, 1],
          [0, 0, 0, 0, 1, 0, 0, 1],
          [0, 0, 0, 0, 1, 0, 1, 0]]

def dfs(vtx, adj, s, visited):
    print(vtx[s], end=' ')
    visited[s] = True

    for v in range(len(vtx)):
        if adj[s][v] != 0:
            if visited[v] == False:
                dfs(vtx, adj, v, visited)

def bfs(vtx, adj, s, visited):
    from queue import Queue
    n = len(vtx)
    visited = [False] * n
    Q = Queue()
    Q.put(s)
    visited[s] = True

    while not Q.empty():
        v = Q.get()
        print(vtx[v], end=' ')
        for v in range(len(vtx)):
            if adj[s][v] != 0:
                if visited[v] == False:
                    Q.put(v)
                    visited[v] = True

if __name__ == "__main__":
    n = len(vertex)
    visited = [False] * n
    print('DFS: ', end='')
    dfs(vertex, adtMat, 0, visited)
    print()

    visited = [False] * n
    print('BFS: ', end='')
    bfs(vertex, adtMat, 0, visited)
    print()