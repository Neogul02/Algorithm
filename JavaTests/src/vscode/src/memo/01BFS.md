## 0-1 BFS

- Deque 자료구조를 활용
- BFS인데 가중치가 0인 간선과 1인 간선이 섞여있는 경우에 최단거리를 구하는 알고리즘
- 최단거리 = 가중치가 적은 경로 = 가중치가 0인 간선을 최대한 이용하는 경로
- 가중치가 0인 정점은 Front에 add, 가중치가 1인 정점은 Back에 add

- 이렇게 앞으로 가중치가 0인 정점만 넣어두면 나중에 pollfirst만 해서 최단거리를 구할 수 있다.


```java
class ZeroOneBFS {
    public static void main(String[] args) {
        // 0-1 BFS 알고리즘 구현
    }
}
```


```python
# 0-1 BFS 알고리즘 구현
from collections import deque
def zero_one_bfs(graph, start):
    dist = {node: float('inf') for node in graph}
    dist[start] = 0
    dq = deque([start])

    while dq:
        node = dq.popleft()
        for neighbor, weight in graph[node]:
            new_dist = dist[node] + weight
            if new_dist < dist[neighbor]:
                dist[neighbor] = new_dist
                if weight == 0:
                    dq.appendleft(neighbor)
                else:
                    dq.append(neighbor)

    return dist


```