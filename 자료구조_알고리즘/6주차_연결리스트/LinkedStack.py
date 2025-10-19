class LinkedStack:
    class _Node:
        __slot__ = '_element', '_next'  # 노드의 속성을 제한하는걸 설정 __slot__

        def __init__(self, element, next):
            self._element = element
            self._next = next

    def __init__(self):
        self._head = None
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def push(self, e):
        self._head = self._Node(e, self._head) # 새로운 노드를 생성하여 스택의 맨 위에 추가
        self._size += 1

    def top(self):
        if self.is_empty():
            raise Exception("Stack is empty")
        return self._head._element # 스택의 맨 위 원소 반환

    def pop(self):
        if self.is_empty():
            raise Exception("Stack is empty")
        answer = self._head._element
        self._head = self._head._next
        self._size -= 1
        return answer

if __name__ == "__main__":
    ls = LinkedStack()
    print(ls.is_empty())
    ls.push(10)
    ls.push(20)
    ls.push(30)

    print(ls.top())
    print(ls.pop())
    print(ls.pop())
    print(ls.pop())
    print(ls.is_empty())



