def stack_transfer(S,T):
    """스택 S의 모든 원소를 스택 T로 옮긴다."""
    for _ in range(len(S)):
        out = S.pop()
        T.append(out)
    return T

def stack_delete(S): # 재귀적으로
    if len(S) == 0:
        return
    S.pop()
    print(S)
    stack_delete(S)

def reverse_stack(S):
    """스택 S의 모든 원소를 스택 T로 역순으로 옮긴다."""
    T = []
    for i in range(len(S)):
        T.append(S.pop())
    return T

if __name__ == "__main__":
    print(stack_transfer([1,2,3], [4,5,6]))

    print(stack_delete([1,2,3,4,5]))  # []

    print(reverse_stack([1,2,3,4,5]))  # [5,4,3,2,1]

