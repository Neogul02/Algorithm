class TreeNode:
    def __init__(self, data=None):
        self.data = data
        self.left = None
        self.right = None

# 전위 순회
def preorder(node):
    if node is not None:
        print(node.data, end='->')  # 전위
        preorder(node.left)
        preorder(node.right)

# 중위 순회
def inorder(node):
    if node is not None:
        inorder(node.left)
        print(node.data, end='->')  # 중위
        inorder(node.right)

# 후위 순회
def postorder(node):
    if node is not None:
        postorder(node.left)
        postorder(node.right)
        print(node.data, end='->')  # 후위

# 메인 코드 부분
# 트리 구조 생성
root = TreeNode("A")
root.left = TreeNode("B")
root.right = TreeNode("C")
root.left.left = TreeNode("D")
root.left.right = TreeNode("E")
root.right.left = TreeNode("F")
root.right.right = TreeNode("G")

# 전위 순회
print("전위 순회:")
preorder(root)
print()

# 중위 순회
print("중위 순회:")
inorder(root)
print()

# 후위 순회
print("후위 순회:")
postorder(root)
print()
