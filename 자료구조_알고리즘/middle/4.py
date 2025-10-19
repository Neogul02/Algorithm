# 재귀
# 4-1 시퀀스S에 있는 n개의 원소 중 최댓값을 찾는 재귀 알고리즘
def findMax(S, n):
    # Base case
    if n == 1:
        return S[0]
    else:
        # Recursive case
        return max(S[n - 1], findMax(S, n - 1))

# 4-6 n번째 조화수를 구하는 재귀함수
def harmonic(n):
    # Base case
    if n == 1:
        return 1
    else:
        # Recursive case
        return 1 / n + harmonic(n - 1)

# 4-7 '13531' -> 13,531 정수로 변환하는 재귀
def stringToInt(s, n):
    # Base case
    if n == 1:
        return ord(s[0]) - ord('0')
    else:
        # Recursive case
        return 10 * stringToInt(s, n - 1) + (ord(s[n - 1]) - ord('0'))
# 다른 방법 no use ord " 1 3 2 13 " ->13213
def stringToint2(s, n):
    # Base case
    if n == 0:
        return 0
    else:
        # Recursive case
        return stringToint2(s, n - 1) * 10 + (int(s[n - 1]) if s[n - 1].isdigit() else 0)



if __name__ == "__main__":
    S = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5]
    print(findMax(S, len(S)))  # 9

    print(harmonic(5))  # 2.283333333333333

    s = "13531  "
    print(stringToInt(s, len(s)))  # 13531