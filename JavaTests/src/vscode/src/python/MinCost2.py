N = int(input())
M = int(input())

graph = [ [] for _ in range(N + 1) ]
for _ in range(M):
    fromCity, toCity, cost = map(int, input().split())
    graph[fromCity].append((toCity, cost))

startCity, endCity = map(int, input().split())


# Dijkstra
INF = 10 ** 18
dist = [INF] * (N + 1)
prev = [0] * (N + 1)

# 시작위치의 가중치는 0
dist[startCity] = 0
pq = []
pq.append((0, startCity)) # (가중치, 도시번호)