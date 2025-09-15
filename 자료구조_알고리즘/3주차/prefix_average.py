def prefix_average1(s):
    """
        시간복잡도: O(n^2)
        각 인덱스 j마다, 전체 리스트를 순회(for i in range(n))하여 합을 구함.
        실제로는 s[0]부터 s[j]까지의 합만 필요하지만, 매번 전체 합을 구함(비효율적).
        예시: s = [1, 2, 3]이면, 각 j마다 [1+2+3]을 계속 더함.
    """
    n = len(s)
    A = [0] * n
    for j in range(n):
        total = 0
        for i in range(j+1):
            total += s[i]
        A[j] = total / (j + 1)
    return A

def prefix_average2(s):
    """
        시간복잡도: O(n^2)
        각 인덱스 j마다, sum(s[0:j+1])로 s[0]부터 s[j]까지의 합을 구함.
        슬라이싱과 sum 함수로 인해, 각 단계마다 최대 O(n)의 연산이 필요.
        예시: j=2일 때 sum(s[0:3]) → s[0]+s[1]+s[2].
    """
    n = len(s)
    A = [0] * n
    for j in range(n):
        A[j] = sum(s[0:j+1]) / (j + 1)
    return A

def prefix_average3(s):
    """
        시간복잡도: O(n)
        누적합(total)을 사용하여, 한 번의 순회로 각 접두 평균을 계산.
        total에 s[j]를 더하고, 그 값을 j+1로 나눔.
        효율적이며, 실제로 접두 평균을 구할 때 가장 좋은 방법.
    """
    n = len(s)
    A = [0] * n
    total = 0
    for j in range(n):
        total += s[j]
        A[j] = total / (j + 1)
    return A

if __name__ == "__main__":
    s = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    print(prefix_average1(s))
    print(prefix_average2(s))
    print(prefix_average3(s))