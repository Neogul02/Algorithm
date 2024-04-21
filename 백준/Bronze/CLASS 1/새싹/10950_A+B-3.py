T = int(input())

for _ in range(T):
    A,B = map(int, input().split())
    if(A>0 and B<10):
        print(A+B)