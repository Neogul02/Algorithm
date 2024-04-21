# 단순 연결 리스트

class Node:
    def __init__(self):
        self.data = None
        self.link = None


node1 = Node() #head
node1.data = '다현'

node2 = Node()
node2.data = '정연'
node1.link = node2

node3 = Node()
node3.data = '쯔위'
node2.link = node3

node4 = Node()
node4.data = "사나"
node3.link = node4

node5 = Node()
node5.data = "지효"
node4.link = node5

print(node1.data, end=' ')
print(node1.link.data, end=' ')
print(node1.link.link.data, end=' ')
print(node1.link.link.link.data, end=' ')
print(node1.link.link.link.link.data, end='\n')


newNode = Node()
newNode.data = '재남'
newNode.link = node2.link #node2의 link를 newNode의 link로 변경
node2.link=newNode

print(node2.link.data)

node2.link = node3.link #node2의 link를 node3의 link로 변경
del(node3) #node3 삭제

current = node1
print(current.data, end=' ')
while current.link != None:
    current = current.link
    print(current.data, end=' ')







