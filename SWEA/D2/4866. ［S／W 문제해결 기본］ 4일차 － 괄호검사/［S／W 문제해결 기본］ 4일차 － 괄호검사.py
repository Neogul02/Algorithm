T = int(input())

for tc in range(1, T + 1):
    arr = input()
    stack = []
    isValid = True
    
    for token in arr:
        if token == '(' or token == '{':
            stack.append(token)
        
        if token == ')':
            if len(stack) == 0:
                isValid = False
                break
            
            if stack.pop() != '(':
                isValid = False
                break
            
        if token == '}':
            if len(stack) == 0:
                isValid = False
                break
            
            if stack.pop() != '{':
                isValid = False
                break
    
    if isValid and len(stack) == 0:
        print(f'#{tc} 1')
    else:
        print(f'#{tc} 0')