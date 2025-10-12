class ArrayQueue:
    DEFAULT_CAPACITY = 10  # 클래스 변수

    def __init__(self):
        self._data = [None] * ArrayQueue.DEFAULT_CAPACITY  # 고정 크기 리스트로 큐 초기화
        self._size = 0  # 현재 큐에 있는 요소의 개수
        self._front = 0  # 큐의 앞쪽 인덱스

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def first(self):
        if self.is_empty():
            raise Exception('Queue is empty')
        return self._data[self._front]

    def dequeue(self):
        if self.is_empty():
            raise Exception('Queue is empty')
        answer = self._data[self._front]
        self._data[self._front] = None
        self._front = (self._front + 1) % len(self._data)
        self._size -= 1
        return answer

    def enqueue(self, e):
        if self._size == len(self._data):
            self._resize(2 * len(self._data))
        avail = (self._front + self._size) % len(self._data)
        self._data[avail] = e
        self._size += 1

    def _resize(self, capacity):
        old = self._data
        self._data = [None] * capacity
        walk = self._front
        for k in range(len(old)):
            self._data[k] = old[walk]
            walk = (walk + 1) % len(old)
        self._front = 0

if __name__ == "__main__":

    Q = ArrayQueue()
    Q.enqueue(5)
    Q.enqueue(3)
    print(f'len is: {len(Q)}')  # 2
    print(f'first: {Q.first()}')  # 5
    print(f'dequeue: {Q.dequeue()}')  # 5
    print(f'is empty: {Q.is_empty()}')  # False
