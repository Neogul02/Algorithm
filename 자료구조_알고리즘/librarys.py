import itertools # 조합, 순열, 중복순열
import heapq   # 힙(우선순위 큐)
import bisect  # 이진 탐색
import collections # 덱, 카운터
import math    # 수학 함수

if __name__ == "__main__":
    # 조합, 순열, 중복순열 itertools
    arr = [1, 2, 3]
    print(list(itertools.permutations(arr, 2)))  # 순열 (1,2), (1,3), (2,1), ...
    print(list(itertools.combinations(arr, 2)))      # 조합 (1,2), (1,3), (2,3)
    print(list(itertools.product(arr, repeat=2)))    # 중복순열
    print(list(itertools.combinations_with_replacement(arr, 2))) # 중복조합
    print('---')

    # 힙(우선순위 큐) heapq
    h = []
    heapq.heappush(h, 3)
    heapq.heappush(h, 1)
    heapq.heappush(h, 2)
    print(heapq.heappop(h))  # 1
    print(heapq.heappop(h))  # 2
    print(heapq.heappop(h))  # 3
    print('---')

    # 이진 탐색 bisect
    a = [1, 3, 4, 4, 5]
    print(bisect.bisect_left(a, 4))  # 2 (
    print(bisect.bisect_right(a, 4)) # 4 ( )
    print('---')

    # 덱, 카운터 collections
    dq = collections.deque()
    dq.append(1)       # 오른쪽 추가
    dq.appendleft(2)   # 왼쪽 추가
    print(dq)          # deque([2, 1])
    dq.pop()           # 오른쪽 제거
    print(dq)          # deque([2])
    dq.popleft()       # 왼쪽 제거
    print(dq)          # deque([])
    print('---')

    # 수학 함수 math

    print(math.gcd(12, 15))  # 최대공약수 3 greatest common divisor
    print(math.lcm(12, 15))  # 최소공배수 60 least common multiple
    print(math.ceil(3.7))    # 올림 4
    print(math.floor(3.7))   # 내림 3
    print(math.sqrt(16))     # 제곱근 4.0
    print(math.factorial(5)) # 팩토리얼 120
    print('---')