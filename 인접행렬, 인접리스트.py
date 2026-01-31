INF = 9999999

# 2차원 리스트(행렬)로 표현한 인접 행렬
graph = [
    [0,7,5],
    [7,0,INF],
    [5,INF,0]
]
print(graph)

# 2차원 리스트(행렬)로 표현한 인접 리스트
graph = [[] for _ in range(3)]
graph[0].append((1,7))
graph[0].append((2,5))
graph[1].append((0,7))
graph[2].append((0,5))
print(graph)
# 노드 0에 연결된 노드 정보
print(graph[0])  # [(1, 7), (2, 5)]
# 노드 0에 연결된 노드의 개수
print(len(graph[0]))  # 2
# 노드 0에 연결된 노드 중에서 두 번째 노드의 번호와 간선 비용
print(graph[0][1])  # (2, 5)