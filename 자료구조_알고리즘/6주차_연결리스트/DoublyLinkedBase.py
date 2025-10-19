class _DoublyLinkedBase:
    class _Node:
        __slots__ = '_element', '_prev', '_next'

        def __init__(self, element, prev, next):
            self._element = element
            self._prev = prev
            self._next = next

    def __init__(self):
        self._header = self._Node(None, None, None) # 더미 헤더 노드
        self._trailer = self._Node(None, None, None) # 더미 트레일러 노드
        self._header._next = self._trailer # 헤더의 다음은 트레일러
        self._trailer._prev = self._header # 트레일러의 이전은 헤더
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def _insert_between(self, e, predecessor, successor):
        new_node = self._Node(e, predecessor, successor)
        predecessor._next = new_node
        successor._prev = new_node
        self._size += 1
        return new_node

    def _delete_node(self, node):
        predecessor = node._prev
        successor = node._next
        predecessor._next = successor
        successor._prev = predecessor
        self._size -= 1
        element = node._element
        node._prev = node._next = node._element = None # 노드의 참조를 끊어줌(가비지 컬렉션)
        return element


class LinkedDeque(_DoublyLinkedBase):

    def first(self):
        if self.is_empty():
            raise Exception("Deque is empty")
        return self._header._next._element

    def last(self):
        if self.is_empty():
            raise Exception("Deque is empty")
        return self._trailer._prev._element

    def insert_first(self, e):
        self._insert_between(e, self._header, self._header._next)

    def insert_last(self, e):
        self._insert_between(e, self._trailer._prev, self._trailer)

    def delete_first(self):
        if self.is_empty():
            raise Exception("Deque is empty")
        return self._delete_node(self._header._next)

    def delete_last(self):
        if self.is_empty():
            raise Exception("Deque is empty")
        return self._delete_node(self._trailer._prev)


if __name__ == "__main__":
    dll = _DoublyLinkedBase()
    print(dll.is_empty())
    n1 = dll._insert_between(10, dll._header, dll._trailer)
    n2 = dll._insert_between(20, n1, dll._trailer)
    n3 = dll._insert_between(30, n2, dll._trailer)

    print(f'len(dll)={len(dll)}')

    print(dll._delete_node(n2))
    print(f'len(dll)={len(dll)}')

    print(dll._delete_node(n1))
    print(f'len(dll)={len(dll)}')

    print(dll._delete_node(n3))
    print(f'len(dll)={len(dll)}')

    print(dll.is_empty())