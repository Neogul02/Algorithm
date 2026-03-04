def bfs():
    queue = [(A, 0)];
    
    while queue.__len__() > 0:
        num, depth = queue.pop(0);
        
        if num == B:
            return depth+1;
        
        if num * 2 <= B:
            queue.append((num * 2, depth + 1));
        
        if num * 10 + 1 <= B:
            queue.append((num * 10 + 1, depth + 1));
            
    return -1; # A에서 B로 변환할 수 없는 경우 -1을 반환

if __name__ == "__main__":
    A, B = map(int, input().split());
    print(bfs());