class Node():
    def __init__(self):
        self.data = None
        self.link = None

def printNodes(start):
    current = start;
    if current.link == None:
        return
    print(current.data, end=' ');
    
    while current.link != start:
        current = current.link
        print(current.data, end=' ')  
    print()

def insertNode(findData, insertData):
    global memory, head, current, pre
    
    if head.data == findData: # 첫번째 노드 앞에 삽입
        node = Node()
        node.data = insertData
        node.link = head
        last = head
        while(last.link != head): # 마지막 노드 찾기
            last = last.link;
        last.link = node
        head = node
        return
    
    current = head
    while current.link != head:
        pre = current
        current = current.link
        if current.data == findData: # 중간 노드 삽입   
            node = Node()
            node.data = insertData
            node.link = current
            pre.link = node
            return
        
    node = Node()
    node.data = insertData
    current.link = node
    node.link = head


def deleteNode(deleteData):
    global memory, head, current, pre

    if head.data == deleteData: # 첫번째 노드 삭제
        current = head
        head = head.link
        last = head
        while(last.link != current):
            last = last.link
        last.link = head
        del(current)
        return
    
    current = head
    while current.link != head: # 중간 노드 삭제
        pre = current
        current = current.link
        if current.data == deleteData:
            pre.link = current.link
            del(current)
            return

def findNode(findData):
    global memory, head, current, pre
    
    current = head
    if current.data == findData:
        return current
    while current.link != head: # 마지막 노드까지 검색
        current = current.link
        if current.data == findData:
            return current
    return Node() # 빈 노드 반환

## 전역 변수 선언 부분 ##
memory = []
head, current, pre = None, None, None
dataArray = ['다현', '정연', '쯔위', '사나', '지효']

## 메인 코드 부분 ##

if __name__ == "__main__":
    node = Node()
    node.data = dataArray[0]
    head=node
    node.link = head # 원형 연결 리스트 연결 -> head
    memory.append(node)

    for data in dataArray[1:]: #두번째 이후 노드들
        pre=node
        node = Node()
        node.data = data
        pre.link = node
        node.link = head
        memory.append(node)

    printNodes(head) 

    insertNode('다현', '화사')
    printNodes(head)

    insertNode('사나', '솔라')
    printNodes(head)

    insertNode('재남', '문별')
    printNodes(head)

    deleteNode('다현')
    printNodes(head)

    deleteNode('쯔위')
    printNodes(head)

    deleteNode('지효')
    printNodes(head)

    deleteNode('재남')
    printNodes(head)

    fNode = findNode('화사')
    print(fNode.data)

