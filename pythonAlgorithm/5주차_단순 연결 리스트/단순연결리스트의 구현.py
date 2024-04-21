class Node():
    def __init__(self):
        self.data = None
        self.link = None


def printNodes(start):
    current = start  # 첫번째 노드를 current 로 지정
    if (current == None):
        return
    print(current.data, end=' ')
    while current.link != None:
        current = current.link
        print(current.data, end=' ')
    print()


def insertNode(findData, insertData):
    global memory, head, current, pre

    if head.data == findData:  # 처음 노드 앞에 삽입
        node = Node()
        node.data = insertData  # 새로운 노드에 삽입할 데이터
        node.link = head
        head = node
        return

    current = head
    while current.link != None:  # 중간 노드 삽입 # 마지막 노드까지
        pre = current
        current = current.link
        if current.data == findData:  # 이해해야할 부분
            node = Node()
            node.data = insertData
            node.link = current
            pre.link = node
            return

    node = Node()  # 마지막 노드 삽입
    node.data = insertData
    current.link = node


def deleteNode(deleteData):
    global memory, head, current, pre

    if (head.data == deleteData):
        current = head
        head = head.link
        del (current)

        return

    current = head
    while current.link != None:
        pre = current
        current = current.link
        if current.data == deleteData:
            pre.link = current.link
            del (current)
            return


def searchNode(searchData):
    global memory, head, current, pre

    current = head
    if current.data == searchData:
        return current
    while current.link != None:
        current = current.link
        if current.data == searchData:
            return current
    return Node()
    
## 전역변수 선언 ##

memory = []  # 무한한 memory
head, current, pre = None, None, None  # head, current, pre 초기화

dataArray = ['다현', '정연', '쯔위', '사나', '지효']

## 메인함수 ##

if __name__ == '__main__':
    node = Node()  # 첫번째 노드
    node.data = dataArray[0]
    head = node
    memory.append(node)

    for data in dataArray[1:]:
        pre = node
        node = Node()
        node.data = data
        pre.link = node
        memory.append(node)

printNodes(head)

print(searchNode('다현').data)

