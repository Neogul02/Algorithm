def add_data(friend):
    list.append(None)  # 배열 마지막에 빈칸(None) 추가
    kLen = len(list)  # 배열의 길이는?
    list[kLen-1] = friend  # 마지막 칸에 친구 추가

def insert_data(position, friend):

    if position < 0 or position > len(list):
        print('데이터를 삽입할 범위를 벗어났습니다.')
        return

    list.append(None)
    for i in range(len(list)-1, position, -1):
        list[i] = list[i-1]
        list[i-1] = None

    list[position] = friend    

def delete_data(position): 
    
    if(position < 0 or position >= len(list)):
        print('데이터를 삭제할 범위를 벗어났습니다.')
        return
    
    list[position] = None
    for i in range(position+1, len(list), 1):
        list[i-1] = list[i]
        list[i] = None
    
    del(list[len(list)-1])

# 전역 변수 선언 부분 
list = []  # 빈 배열
select = -1  # 1. 추가, 2. 삽입, 3. 삭제, 4. 종료

## 메인 코드 부분 ##
if __name__ == "__main__":
    while (select != 4):
        select = int(input('선택하세요(1: 추가, 2: 삽입, 3: 삭제, 4: 종료)-->'))

        if (select == 1):
            data = input('추가할 데이터-->')
            add_data(data)
            print(list)

        elif (select == 2):
            pos = int(input('삽입할 위치-->'))
            data = input('추가할 데이터-->')
            insert_data(pos, data)
            print(list)

        elif (select == 3):
            pos = int(input('삭제할 위치-->'))
            delete_data(pos)
            print(list)

        elif (select == 4):
            print(list)
            exit

        else:
            print('1~4 중 하나를 입력하세요.')
            continue