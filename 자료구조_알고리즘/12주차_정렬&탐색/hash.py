# 해시함수 M = 13

M = 13
table = [None] * M

def hash_function(key):
    return key % M

# 탐색, 삽입, 삭제 함수
def insert(key):
    i = hash_function(key) # mod hash value
    cnt = M

    while cnt > 0:
        if table[i] is None: # 빈 슬롯 발견 -> 바로 삽입
            break
        i = (i+1)%M # 선형 조사
        cnt -= 1
    if cnt > 0:
        table[i] = key

def search(key):
    i = hash_function(key)
    cnt = M

    while cnt > 0:
        if table[i] is None: # 빈 슬롯 발견 -> 탐색 실패
            return None
        if table[i] == key: # 탐색 성공
            return i
        i = (i+1) % M
        cnt -= 1
    return None # 탐색 실패

def delete(key):
    i = hash_function(key)
    cnt = M

    while cnt > 0:
        if table[i] is None: # 빈 슬롯 발견 -> 삭제 실패
            return False
        if table[i] == key: # 삭제 성공
            table[i] = None
            return True
        i = (i+1) % M
        cnt -= 1
    return False # 삭제 실패

if __name__ == "__main__":
    data = [45, 27, 88, 99, 71, 60, 46, 38, 26]
    for d in data:
        print(f'h({d:2d}) = {hash_function(d)}', end=' -> ')
        insert(d)
        print(table)
    print('--삽입완료--')


    print('46탐색 -> ', search(46))
    print('99탐색 -> ', search(99))
    print('100탐색 -> ', search(100))
    print('--탐색완료--')

    print('60삭제 -> ', delete(46), table)
    print('46삭제 -> ', delete(46), table)
    print('39삭제 -> ', delete(27), table)
    print('--삭제완료--')


