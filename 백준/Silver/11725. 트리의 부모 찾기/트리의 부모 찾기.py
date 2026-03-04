def bfs():
    queue = [];
    queue.append(1); # 루트 노드 1부터 시작
    visited[1] = True; # 루트 노드 방문 처리
    
    while len(queue) > 0:
        nodeIdx = queue.pop(0); # 큐에서 맨 앞 요소 꺼내기
        
        for next in graph[nodeIdx]: # 현재 노드와 연결된 노드들 탐색
            if visited[next] == True: continue; # 이미 방문한 노드면 건너뛰기
            
            visited[next] = True;
            result[next] = nodeIdx; # 현재 노드가 next의 부모 노드이므로 result에 저장
            
            queue.append(next); # 다음 노드 큐에 추가
            
if __name__ == "__main__":
    N = int(input());
    graph = [[] for _ in range(N + 1)];
    
    visited = [False] * (N + 1); # 방문 여부 체크 배열
    result = [0] * (N + 1); # 부모 노드를 저장할 배열, 1-based index
    
    for _ in range(N - 1): # N-1개 간선 입력
        a, b = map(int, input().split());
        graph[a].append(b);
        graph[b].append(a); # 양방향 그래프 초기화
        
    bfs();
    
    for i in range(2, N + 1): # 2번 노드부터 N번 노드까지 부모 노드 출력
        print(result[i]);