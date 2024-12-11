import sys

def main():
    input = sys.stdin.readline

    N = int(input())
    A = set(map(int, input().split()))

    M = int(sys.stdin.readline())
    B = list(map(int, input().split()))

    for i in B:
        print(1 if i in A else 0)

if __name__ == "__main__":
    main()