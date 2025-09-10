# ===== 코딩테스트 필수 문법 모음 =====

# 1. 입력 처리
N = int(input())                    # 정수 하나
A, B, C = map(int, input().split()) # 여러 정수를 한 줄에
arr = list(map(int, input().split())) # 리스트로 입력받기
matrix = [list(map(int, input().split())) for _ in range(N)] # 2차원 배열

# 2. 문자열 처리
s = input().strip()                 # 문자열 (공백 제거)
words = input().split()             # 단어별로 분리
char_list = list(input())           # 문자 하나씩 리스트로

# 3. 리스트 컴프리헨션
squares = [i**2 for i in range(10)]            # 제곱수 리스트
evens = [i for i in range(20) if i % 2 == 0]   # 짝수만
matrix2d = [[0]*5 for _ in range(3)]            # 2차원 리스트 초기화

# 4. 자주 쓰는 함수들
# min, max, sum, len, sorted, reversed
arr = [3, 1, 4, 1, 5, 9]
print(min(arr), max(arr), sum(arr))    # 최솟값, 최댓값, 합
print(sorted(arr))                     # 정렬된 새 리스트
print(sorted(arr, reverse=True))       # 내림차순
arr.sort()                             # 원본 리스트 정렬

# 5. 딕셔너리 활용
from collections import defaultdict, Counter
count_dict = defaultdict(int)          # 기본값이 0인 딕셔너리
counter = Counter(arr)                 # 개수 세기

# 6. 수학 관련
import math
print(math.gcd(12, 8))                 # 최대공약수
print(math.lcm(12, 8))                 # 최소공배수 (Python 3.9+)
print(math.ceil(7/3), math.floor(7/3)) # 올림, 내림

# 7. 비트 연산
a, b = 5, 3
print(a & b, a | b, a ^ b)             # AND, OR, XOR
print(bin(a), oct(a), hex(a))          # 진법 변환

# 8. 정렬 관련
# 튜플 정렬 (첫 번째 요소 기준, 같으면 두 번째 요소 기준)
points = [(1, 3), (2, 1), (1, 2)]
points.sort()                          # 기본 정렬
points.sort(key=lambda x: (x[1], x[0])) # y좌표 우선, 같으면 x좌표

# 9. 큐와 스택
from collections import deque
dq = deque()
dq.append(1)       # 오른쪽 추가
dq.appendleft(2)   # 왼쪽 추가
dq.pop()           # 오른쪽 제거
dq.popleft()       # 왼쪽 제거

stack = []         # 리스트로 스택 구현
stack.append(1)    # push
stack.pop()        # pop

# 10. 조합과 순열
from itertools import combinations, permutations, product
arr = [1, 2, 3]
print(list(combinations(arr, 2)))      # 조합 (1,2), (1,3), (2,3)
print(list(permutations(arr, 2)))      # 순열 (1,2), (1,3), (2,1), ...
print(list(product(arr, repeat=2)))    # 중복순열

# 11. 범위와 반복
for i in range(5):          # 0부터 4까지
    pass
for i in range(1, 6):       # 1부터 5까지
    pass
for i in range(0, 10, 2):   # 0부터 8까지 2씩 증가
    pass

# 12. 문자열 조작
s = "hello world"
print(s.upper(), s.lower())            # 대소문자 변환
print(s.replace("world", "python"))    # 문자열 치환
print(s.count("l"))                    # 문자 개수
print("".join(["a", "b", "c"]))        # 리스트를 문자열로

# 13. 집합 연산
set1 = {1, 2, 3}
set2 = {2, 3, 4}
print(set1 & set2)         # 교집합
print(set1 | set2)         # 합집합
print(set1 - set2)         # 차집합

# 14. 자주 쓰는 조건문 패턴
# 최솟값/최댓값 찾기
INF = float('inf')
min_val = INF
max_val = -INF

# 15. 이진 탐색
def binary_search(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1

# 16. DFS, BFS 기본 틀
def dfs(graph, start, visited):
    visited[start] = True
    for neighbor in graph[start]:
        if not visited[neighbor]:
            dfs(graph, neighbor, visited)

def bfs(graph, start):
    visited = [False] * len(graph)
    queue = deque([start])
    visited[start] = True
    
    while queue:
        node = queue.popleft()
        for neighbor in graph[node]:
            if not visited[neighbor]:
                visited[neighbor] = True
                queue.append(neighbor)

# 17. 동적 계획법 기본 패턴
def dp_example(n):
    dp = [0] * (n + 1)
    dp[0] = dp[1] = 1
    for i in range(2, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]

# 18. 자주 쓰는 출력 형식
print(*arr)                # 리스트 요소를 공백으로 구분해서 출력
# print("YES" if condition else "NO")  # 조건부 출력 예시

# 19. 추가 팁들
# 무한대 표현
INF = float('inf')
INF2 = int(1e9)

# 방향 벡터 (상하좌우)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 8방향
dx8 = [-1, -1, -1, 0, 0, 1, 1, 1]
dy8 = [-1, 0, 1, -1, 1, -1, 0, 1]

# 알파벳
import string
print(string.ascii_lowercase)  # abcdefg...
print(string.ascii_uppercase)  # ABCDEFG...

# 아스키 코드
print(ord('A'))  # 65
print(chr(65))   # A

