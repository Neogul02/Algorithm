def solution(priorities, location):
    from collections import deque

    queue = deque([(i, p) for i, p in enumerate(priorities)])
    answer = 0

    while queue:
        current = queue.popleft()
        if any(current[1] < other[1] for other in queue):
            queue.append(current)
        else:
            answer += 1
            if current[0] == location:
                return answer