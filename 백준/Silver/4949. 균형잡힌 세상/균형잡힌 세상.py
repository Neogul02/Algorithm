while True:
    s = input()
    if s == '.':
        break

    stack = []
    balanced = True

    for char in s:
        if char in '([':
            stack.append(char)
        elif char == ')':
            if not stack or stack.pop() != '(':
                balanced = False
                break
        elif char == ']':
            if not stack or stack.pop() != '[':
                balanced = False
                break

    if stack:
        balanced = False

    print('yes' if balanced else 'no')