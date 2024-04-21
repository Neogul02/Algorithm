class Node():
    def __init__(self):
        self.data = None
        self.link = None


# node is cd sdsl
node1 = Node()
node1.data = '다현'

node2 = Node()
node2.data = '정연'
node1.link = node2

node3 = Node()
node3.data = '쯔위'
node2.link = node3

print("야미 : ", node2, node2.data, node2.link)

node4 = Node()
node4.data = '사나'
node3.link = node4

node5 = Node()
node5.data = '지효'
node4.link = node5

print(node1.data, end=' ')
print(node1.link.data, end=' ')
print(node1.link.link.data, end=' ')
print(node1.link.link.link.data, end=' ')
print(node1.link.link.link.link.data, end='\n')

# 노드 삽입
newNode = Node()
newNode.data = '재남' # 1. 새 노드 생성
newNode.link = node2.link # 2. 새 노드의 링크에 node2의 링크를 연결
node2.link = newNode # 3. node2의 링크에 새 노드 연결

## 노드삭제
node2.link = node3.link # node2의 링크에 node3의 링크를 연결
del(node3) # node3 삭제
print()

current = node1  # 현재 태그를 노드 1으로 지정
print(current.data, end=' ')
while current.link != None:
    current = current.link
    print(current.data, end=' ')



