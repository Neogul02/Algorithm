def stack_check():
    T = int(input())

    for _ in range(T):
        stack = []
        parentheses = input()
        for char in parentheses:
            if char == '(':
                stack.append(char)
            elif char == ')':
                if stack:
                    stack.pop()
                else:
                    print("NO")
                    break
        else:
            print("YES" if not stack else "NO")

if __name__ == "__main__":
    stack_check()