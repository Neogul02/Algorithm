import sys

def push(x):
    queue.append(x)

def pop():
    return queue.pop(0) if queue else -1

def size():
    return len(queue)

def empty():
    return 1 if not queue else 0

def front():
    return queue[0] if queue else -1

def back():
    return queue[-1] if queue else -1

queue = []

if __name__ == '__main__':
    N = int(sys.stdin.readline().rstrip())
    commands = {
        'push': lambda x: push(x),
        'pop': lambda: print(pop()),
        'size': lambda: print(size()),
        'empty': lambda: print(empty()),
        'front': lambda: print(front()),
        'back': lambda: print(back())
    }
    for _ in range(N):
        command = sys.stdin.readline().rstrip().split()
        if command[0] == 'push':
            commands['push'](command[1])
        else:
            commands[command[0]]()