class Node:
    def __init__(self):
        self.data = None
        self.link = None

def printNodes(start):
    current = start # 첫번째 노드를 current 로 지정
    if(current == None):
        return
    print(current.data, end=' ')
    while current.link != None:
        current = current.link
        print(current.data , end=' ')
    print()

def insertNodes(findData, insertData):
    global memory, head, current, pre
    
    if head.data == findData: # 처음 노드 앞에 삽입
        node = Node()
        node.data = insertData # 새로운 노드에 삽입할 데이터
        node.link = head
        head = node
        return
    
    current=head
    while current.link != None: # 중간 노드 삽입
        pre = current
        current = current.link
        if current.data == findData: # 이해해야할 부분
            node = Node()
            node.data = insertData
            node.link = current
            pre.link = node
            return

    node = Node() # 마지막 노드 삽입
    node.data = insertData
    current.link = node
    
def deleteNode(deleteData):
    global memory, head, current, pre

    if head.data == deleteData: # 첫번째 노드 삭제
        current = head
        head = head.link
        del(current)
        return
    
    current = head
    while current.link != None: # 중간 노드 삭제
        pre = current
        current = current.link
        if current.data == deleteData:
            pre.link = current.link
            del(current)
            return

    if current.data == deleteData: # 마지막 노드 삭제
        pre.link = None
        del(current)
        return
    
def searchNode(findData):
    global memory, head, current, pre

    current = head
    if current.data == findData:
        return current
    while current.link != None:
        current = current.link
        if current.data == findData:
            return current
    return Node()

   
## 전역변수 ##
memory =[]
pre, current, head = None, None, None
dataArray = ['다현', '정연', '쯔위', '사나', '지효']

## 메인코드 ##
if __name__ == "__main__":

    node = Node() # 첫번째 노드
    node.data = dataArray[0] # 다현
    head = node # 첫번째 노드를 헤드로 지정
    memory.append(node)

    for data in dataArray[1:]:
        pre = node # 이전(첫번쨰) 노드를 pre 로 기억
        node = Node() # 새로운 n번째 노드 생성
        node.data = data # 새로운 노드에 데이터 저장 # 정연을 새로운 노드에 저장
        pre.link = node 
        memory.append(node)

    insertNodes('다현', '화사')
    printNodes(head)