def bfs(graph, start, visited):
    from collections import deque
    queue = deque([start])
    visited[start] = True

    while queue:
        v = queue.popleft()
        print(v, end=' ')

        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True


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

    # BFS 함수 호출
    bfs(graph, 1, visited)