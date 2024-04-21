# queue =[None,None,None,None,None]
# front = rear = -1

# rear+=1 # 큐에 데이터 삽입
# queue[rear] = '감자' 

# rear+=1
# queue[rear] = '고구마'

# rear+=1
# queue[rear] = '양파'


# print(' -- 큐 상태 --')
# print('[출구] <--', end=' ')
# for i in range(len(queue)):
#     print(queue[i], end=' ')

# print('<-- [입구]')

queue = ['감자','고구마','양파',None,None]
front = -1
rear = 2

def Qstate():
    print(' -- 큐 상태 --')
    print('[출구] <--', end=' ')
    for i in range(0, len(queue)):
        print(queue[i], end=' ')
    print('<-- [입구]')
    print('-----------------')

Qstate()

for i in range(0, rear+1):
    front += 1
    data = queue[front]
    queue[front] = None
    print('dequeue -->', data)

Qstate()