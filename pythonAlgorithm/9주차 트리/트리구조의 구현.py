class TreeNode():
    def __init__(self):
        self.data = None;
        self.left = None;
        self.right = None;

# print의 위치로 전위 중위 후위 순회를 결정,
def preorder(node): # 전위 순회
    if node == None: 
        return 
    print(node.data, end = '->') # 전위 , print가 앞에 오는걸 기억하자
    preorder(node.left)
    preorder(node.right)

def inorder(node): # 중위 순회
    if node == None:
        return
    inorder(node.left)
    print(node.data, end = '->') # 중위
    inorder(node.right)

def postorder(node): # 후위 순회
    if node == None:
        return
    postorder(node.left)
    postorder(node.right)
    print(node.data, end = '->') # 후위


memory = []
root = None
nameArray = ['블랙핑크','레드벨벳','마마무','에이핑크','걸스데이','트와이스']

node=TreeNode()
node.data = nameArray[0] #root node = 블랙핑크
root = node
memory.append(node) # 블랙핑크를 메모리에 저장

for name in nameArray[1:]:
    node = TreeNode()
    node.data = name # nameArray의 두번째부터 끝까지

    current = root # root를 가리키고 있음
    while True:
        if name < current.data:
            if current.left == None:
                current.left = node
                break
            current = current.left
        else:
            if current.right == None:
                current.right = node
                break
            current = current.right
    memory.append(node) # 노드를 메모리에 저장

findName = '트와이스'
current = root
while True: # findName을 찾을 때까지 무한 반복 
    if findName == current.data:
        print(findName, '을(를) 찾았습니다.')
        break
    elif findName < current.data:
        if current.left == None:
            print(f'{findName}이(가) 트리에 없습니다.')
            break
        current = current.left
    else:
        if current.right == None:
            print(f'{findName}이(가) 트리에 없습니다.')
            break
        current = current.right
        

print('전위 순회 : ', end = '')
preorder(root)
print('끝')

print('후위 순회 : ', end = '')
postorder(root)
print('끝')