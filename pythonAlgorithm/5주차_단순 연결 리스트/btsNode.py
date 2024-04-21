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

def makeSimpleLinkedList(namePhone):
    global memory, head, current, pre
    printNodes(head)


## 전역변수
dataArray = [['지민', '010-1111-1111'], ['정국', '010-1111-2222'],
             ['뷔', '010-1111-3333'], ['슈가', '010-1111-4444'], ['진', '010-1111-5555']]

memory = []  # 무한한 memory
head, current, pre = None, None, None  # head, current, pre 초기화

## 메인함수 ##

if __name__ == "__main__":
    for data in dataArray:
        makeSimpleLinkedList(data)
    printNodes(head)