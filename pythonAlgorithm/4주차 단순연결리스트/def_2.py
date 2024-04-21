list = []  # empty array


def add_data(friend):  # 데이터 추가
    list.append(None)
    list[len(list)-1] = friend  # 끝자리에 new friend 추가


add_data('다현')
add_data('정연')
add_data('쯔위')
add_data('사나')
add_data('지효')

print(list)

# 데이터 삽입


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
    list[position] = None
    if position > len(list) or position < 0:
        print('데이터를 삭제할 범위를 벗어났습니다.')
        return

    for i in range(position+1, len(list)):
        list[i-1] = list[i]
        list[i] = None

    del (list[len(list)-1])


insert_data(2, '솔라')  # [2]
print(list)
insert_data(6, '문별')  # [6]
print(list)
delete_data(2)  # [3]
print(list)
delete_data(5)  # [6]
print(list)
