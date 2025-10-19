class LinkedQueue:
    class _Node:
        __slots__ = '_element', '_next'
        def __init__(self, element, next):
            self._element = element
            self._next = next

    def __init__(self):
        self._head = None
        self._tail = None
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def first(self):
        if self.is_empty():
            raise Exception("Queue is empty")
        return self._head._element

    def dequeue(self):
        if self.is_empty():
            raise Exception("Queue is empty")
        answer = self._head._element
        self._head = self._head._next
        self._size -= 1
        if self.is_empty():
            self._tail = None # 큐가 비면 꼬리도 None으로 설정
        return answer

    def enqueue(self, e):
        newest = self._Node(e, None)
        if self.is_empty():
            self._head = newest
        else:
            self._tail._next = newest # 기존 꼬리 노드의 next를 새 노드로 연결
        self._tail = newest
        self._size += 1

    def rotate(self):
        if self.is_empty():
            raise Exception("Queue is empty")
        oldhead = self._head
        self._head = self._head._next
        self._tail._next = oldhead
        oldhead._next = None
        self._tail = oldhead

if __name__ == "__main__":

    lq = LinkedQueue()
    print(lq.is_empty())
    lq.enqueue(10)
    lq.enqueue(20)
    lq.enqueue(30)

    print(f'lq.first()={lq.first()}')
    print(lq.dequeue())
    print(lq.dequeue())
    print(lq.dequeue())
    print(lq.is_empty())