from collections import deque

def dfs_recursive_trace(graph, start):
    visited, order = set(), []
    def _dfs(v, depth=0):
        print(f"{'  '*depth}enter {v}")
        visited.add(v)
        order.append(v)
        for nxt in graph.get(v, []):
            print(f"{'  '*depth}check {v}->{nxt} visited={nxt in visited}")
            if nxt not in visited:
                _dfs(nxt, depth+1)
        print(f"{'  '*depth}exit {v}")
    _dfs(start)
    print("DFS(rec) order:", order)

def dfs_iterative_trace(graph, start):
    visited, order, stack, step = set(), [], [start], 0
    while stack:
        print(f"step {step:02d} stack={stack}")
        v = stack.pop()
        if v in visited:
            print(f"  skip {v} (visited)"); step += 1; continue
        print(f"  visit {v}"); visited.add(v); order.append(v)
        for nxt in reversed(graph.get(v, [])):  # 재귀와 비슷한 순서
            if nxt not in visited:
                print(f"  push {nxt}"); stack.append(nxt)
        step += 1
    print("DFS(iter) order:", order)

def bfs_trace(graph, start):
    visited, order, q, step = {start}, [], deque([start]), 0
    while q:
        print(f"step {step:02d} queue={list(q)}")
        v = q.popleft(); print(f"  visit {v}"); order.append(v)
        for nxt in graph.get(v, []):
            print(f"  see edge {v}->{nxt} visited={nxt in visited}")
            if nxt not in visited:
                visited.add(nxt); q.append(nxt); print(f"    enqueue {nxt}")
        step += 1
    print("BFS order:", order)

def bfs_shortest_path_trace(graph, start, goal):
    parent, q, step = {start: None}, deque([start]), 0
    while q:
        print(f"step {step:02d} queue={list(q)}")
        v = q.popleft()
        if v == goal:
            print(f"  reached {goal}"); break
        for nxt in graph.get(v, []):
            if nxt not in parent:
                parent[nxt] = v; print(f"  parent[{nxt}] <- {v}"); q.append(nxt)
        step += 1
    if goal not in parent: print("no path"); return None
    path = []; cur = goal
    while cur is not None: path.append(cur); cur = parent[cur]
    path.reverse(); print("shortest path:", " -> ".join(path)); return path

if __name__ == "__main__":
    graph = {
        "A": ["B", "C"],
        "B": ["A", "D", "E"],
        "C": ["A", "F"],
        "D": ["B"],
        "E": ["B", "F"],
        "F": ["C", "E"]
    }

    print("=== DFS recursive trace ==="); dfs_recursive_trace(graph, "A")
    print("\n=== DFS iterative trace ==="); dfs_iterative_trace(graph, "A")
    print("\n=== BFS trace ==="); bfs_trace(graph, "A")
    print("\n=== BFS shortest path A->F ==="); bfs_shortest_path_trace(graph, "A", "F")
