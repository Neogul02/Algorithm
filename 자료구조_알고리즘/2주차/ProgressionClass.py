# 수열 클래스

# 수열 -> 수의 나열
# 등차수열, 등비수열, 피보나치 수열

class Progression:
    # 수열 클래스
    def __init__(self, start=0):
        self.current = start

    def _advance(self):
        self.current += 1

    # iterator
    def __next__(self):
        # 요소들을 넘겨주다가, 끝가지 가면 stopIteration Exception 발생
        if self.current is None:
            raise StopIteration
        else:
            answer = self.current # 현재 수열의 값
            self._advance() # 다음 수열로 이동
            return answer

    def __iter__(self):
        return self

    def print_progression(self, n):
        # 수열의 처음 n개 요소를 출력
        print(' '.join(str(next(self)) for _ in range(n)))

# 등차수열 (상속 : Progression)
class ArithmeticProgression(Progression):

    def __init__(self, increment=1, start=0):
        super().__init__(start)
        self._increment = increment

    # 오버라이딩 (상속 받은거 재정의)
    def _advance(self):
        self.current += self._increment

# 등비수열 (상속 : Progression)
class GeometricProgression(Progression):

    def __init__(self, base=2, start=1):
        super().__init__(start)
        self._base = base

    # 오버라이딩 (상속 받은거 재정의)
    def _advance(self):
        self.current *= self._base

# 피보나치 수열 (상속 : Progression)
class FibonacciProgression(Progression):

    def __init__(self, first=0, second=1):
        super().__init__(first)
        self._prev = second - first # 이전 수

    def _advance(self):
        self._prev, self.current = self.current, self._prev + self.current

if __name__ == '__main__':
    p1 = Progression(5)
    p2 = ArithmeticProgression(4, 2)
    p3 = GeometricProgression(5, 1)
    p4 = FibonacciProgression(0, 1)

    p1.print_progression(10)
    p2.print_progression(10)
    p3.print_progression(10)
    p4.print_progression(10)

    # --- 연습문제
    # 2,2 fiobonacci
    p5 = FibonacciProgression(2, 2)
    print(f'2,2 Fibonacci:')
    p5.print_progression(8)

    # 0, 128, how many steps to < 2^63
    p6 = FibonacciProgression(0, 128)
    count = 0
    for x in p6:
        if x < 2**63:
            count += 1
        else:
            count += 1
            break

    print(f'0,128 Fibonacci가 2^63보다 크게 될때 나오는 next 횟수: {count}')


