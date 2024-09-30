N = int(input())
person = list(map(int,input().split()))
T, P = map(int,input().split())

Tsum = 0
for i in person:
    if i !=0 :
        if i%T == 0:
            Tsum += (i//T)
        else:
            Tsum += (i//T)+1

print(Tsum)
print(N//P,N%P)