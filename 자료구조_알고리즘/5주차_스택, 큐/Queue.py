class Empty(Exception):
    pass

class ArrayQueue:

    DEFAULT_CAPACITY = 10    # 클래스 속성

    def __init__(self):
        self.data = [0] * ArrayQueue.DEFAULT_CAPACITY  # 큐에 활용될 리스트
        self.size = 0
        self.front = 0

    def  __len__(self):
        return self.size

    def is_empty(self):
        return self.size == 0

    def first(self):
        if self.is_empty():
            raise Empty('Queue is empty')
        return self.data[self.front]

    def dequeue(self):
        if self.is_empty():
            raise Empty('Queue is empty')
        answer = self.data[self.front]
        self.data[self.front] = None  # 큐에서 제거된 위치를 None으로 설정
        self.front = (self.front + 1) % len(self.data)
        self.size -= 1
        return answer

    def enqueue(self, e):
        if self.size == len(self.data):
            self._resize(2 * len(self.data))
        avail = (self.front + self.size) % len(self.data)
        self.data[avail] = e
        self.size += 1

    def _resize(self, cap):
        old = self.data
        self.data = [0] * cap
        walk = self.front
        for k in range(self.size):
            self.data[k] = old[walk]
            walk = (1 + walk) % len(old)
        self.front = 0