n=int(input())
ans=list()
if n==1:exit(0)
def soinsoo(k):
    for i in range(2, k):
        if k%i==0:
            ans.append(i)
            soinsoo(int(k/i))
            return
    ans.append(k)
soinsoo(n)
ans.sort()
for i in ans:print(i)
