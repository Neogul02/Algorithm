# 자료구조 4주차 실습

### 선형 리스트의 원리 ###

# 1. 배열의
#  마지막에 none을 append
# 2. 하나씩 밀어낸다
# 3. 원하는 위치에 삽입한다.

katok = []  # 빈 배열


def add_data(friend):
    katok.append(None)  # 배열 마지막에 빈칸(None) 추가
    kLen = len(katok)  # 배열의 길이는?
    katok[kLen-1] = friend  # 마지막 칸에 친구 추가


add_data('다현')
add_data('정연')
add_data('쯔위')
add_data('사나')
add_data('지효')

print(katok, '길이는 : %d' % len(katok))

# 데이터 삭제

# 1. 원하는 대상 삭제
# 2. 하나씩 당겨오기
# 3. 마지막 none(빈칸) 삭제

# 데이터 삽입 함수 완성


def insert_data(position, friend):

    if position < 0 or position > len(katok):
        print('데이터를 삽입할 범위를 벗어났습니다.')
        return

    katok.append(None)
    for i in range(len(katok)-1, position, -1):
        katok[i] = katok[i-1]
        katok[i-1] = None

    katok[position] = friend


insert_data(2, '솔라')
print(katok)
insert_data(6, '문별')
print(katok)

# 데이터 삭제 함수 완성
def delete_data(position): 
    
    if(position < 0 or position >= len(katok)):
        print('데이터를 삭제할 범위를 벗어났습니다.')
        return
    
    katok[position] = None
    for i in range(position+1, len(katok), 1):
        katok[i-1] = katok[i]
        katok[i] = None
    
    del(katok[len(katok)-1])

delete_data(1)
print(katok)
delete_data(3)
print(katok)