#5-7 1...n-1의 정수가 들어있는 배열에서 중복된 정수를 찾는 알고리즘

def findDuplicate(S):
    seen = set()
    for i in S:
        if i in seen:
            return i
        seen.add(i)
        print(f"seen: {seen}")

if __name__ == "__main__":
    S = [6, 1, 4, 2, 3, 3]
    print(findDuplicate(S))  # 3