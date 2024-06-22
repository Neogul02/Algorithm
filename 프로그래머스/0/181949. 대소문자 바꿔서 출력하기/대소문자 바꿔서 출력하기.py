str = input()
x=[]
for i in str:
    if(ord(i)>=97):
        x.append(i.upper())
    elif(ord(i)<=90):
        x.append(chr(ord(i)+32))
print(''.join(x))