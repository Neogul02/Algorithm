def solution(s):
    open = '(['
    close = ')]'

    stack = []
    for char in s:
        if char in open:
            stack.append(char)
        elif char in close:
            if not stack:
                return False
            if open.index(stack.pop()) != close.index(char):
                return False

    return len(stack) == 0