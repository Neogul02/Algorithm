class CircularQueue:
    class _Node:
        __slots__ = '_element', '_next'
        def __init__(self, element, next):
            self._element = element
            self._next = next

    def __init__(self):
        self._tail = None # head -> _tail._next
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def first(self):
        if self.is_empty():
            raise Exception("Queue is empty")
        head = self._tail._next
        return head._element

    def dequeue(self):
        if self.is_empty():
            raise Exception("Queue is empty")
        oldhead = self._tail._next
        if self._size == 1:
            self._tail = None # 자기 자신을 가리키고 있는 노드이므로, None으로 설정
        else:
            self._tail._next = oldhead._next
        self._size -= 1
        return oldhead._element

    def enqueue(self, e):
        newest = self._Node(e, None)
        if self.is_empty():
            newest._next = newest # 자기 자신을 가리키도록 설정
        else:
            newest._next = self._tail._next # 새 노드의 next를 head로 설정
            self._tail._next = newest # 기존 꼬리 노드의 next를 새 노드로 연결
        self._tail = newest
        self._size += 1

if __name__ == "__main__":
    cq = CircularQueue()
    print(cq.is_empty())
    cq.enqueue(10)
    cq.enqueue(20)
    cq.enqueue(30)

    print(f'cq.first()={cq.first()}')
    print(cq.dequeue())
    print(cq.dequeue())
    print(cq.dequeue())
    print(cq.is_empty())