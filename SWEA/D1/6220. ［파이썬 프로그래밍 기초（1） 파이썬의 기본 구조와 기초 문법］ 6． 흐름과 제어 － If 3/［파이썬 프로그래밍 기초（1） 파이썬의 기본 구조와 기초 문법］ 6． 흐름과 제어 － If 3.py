T = int(input())

for tc in range(1,T+1):
    temp = input()
    if(temp.islower()==True):
        print(f'#{tc} {temp} 는 소문자 입니다.')
    else:
        print(f'#{tc} {temp} 는 대문자 입니다.')