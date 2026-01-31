def dfs(graph, v, visited):
    # 현재 노드를 방문 처리
    visited[v] = True
    print(v, end=' ')

    # 현재 노드와 연결된 다른 노드를 재귀적으로 방문
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)

if __name__ == "__main__":
    # 그래프를 인접 리스트로 표현
    graph = [
        [],         # 노드 0 (사용하지 않음)
        [2, 3, 8], # 노드 1에 연결된 노드
        [1, 7],    # 노드 2에 연결된 노드
        [1, 4, 5], # 노드 3에 연결된 노드
        [3, 5],    # 노드 4에 연결된 노드
        [3, 4],    # 노드 5에 연결된 노드
        [7],       # 노드 6에 연결된 노드
        [2, 6, 8], # 노드 7에 연결된 노드
        [1, 7]     # 노드 8에 연결된 노드
    ]

    # 각 노드가 방문된 정보를 표현하는 리스트 초기화
    visited = [False] * 9

    # DFS 함수 호출
    dfs(graph, 1, visited)