import ctypes

class DynamicArray:
    def __init__(self):
        self.n = 0
        self.capacity = 1
        self.A = self.make_array(self.capacity) # 저수준 배열

    def __len__(self):
        return self.n

    def __getitem__(self, k):
        if k < 0 or k >= self.n:
            raise IndexError('invalid index')
        return self.A[k]

    def append(self, obj):
        if self.n == self.capacity:
            self._resize(2 * self.capacity) # 배열 크기 두 배로 늘리기
        self.A[self.n] = obj
        self.n += 1

    def _resize(self, c):
        B = self.make_array(c)
        for k in range(self.n):
            B[k] = self.A[k]
        self.A = B
        self.capacity = c

    def make_array(self, c):
        return (c * ctypes.py_object)()

if __name__ == "__main__":
    arr = DynamicArray()
    for i in range(10):
        arr.append(i)
        print(f"Length: {len(arr)}, Capacity: {arr.capacity}, Elements: {[arr[j] for j in range(len(arr))]}")