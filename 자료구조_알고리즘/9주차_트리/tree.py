class Tree:
    # 중첩 포지션 클래스
    class Position:
       """단일 원소의 포지션을 나타내는 추상화"""

       def element(self):
           """포지션이 담고 있는 원소를 반환"""
           raise NotImplementedError("Must be implemented by subclass")
       
       def __eq__(self, other):
            """두 포지션이 동일한지 여부 반환"""
            raise NotImplementedError("Must be implemented by subclass")
       
       def __ne__(self, other):
           """두 포지션이 동일하지 않은지 여부 반환"""
           return not (self == other)
       
    # 상속하여 구현할 하위 클래스가 구현해야 할 추상 메서드
    def root(self):
        """트리의 루트 포지션 반환"""
        raise NotImplementedError("Must be implemented by subclass")
    
    def parent(self, p):
        """포지션 p의 부모 포지션 반환"""
        raise NotImplementedError("Must be implemented by subclass")
    
    def num_children(self, p):
        """포지션 p의 자식 수 반환"""
        raise NotImplementedError("Must be implemented by subclass")
    
    def children(self, p):
        """포지션 p의 자식 포지션들 반환"""
        raise NotImplementedError("Must be implemented by subclass")
    
    def __len__(self):
        """트리의 원소 수 반환"""
        raise NotImplementedError("Must be implemented by subclass")
    
    # 직접 구현된 메소드들
    def is_root(self, p):
        """포지션 p가 루트인지 여부 반환"""
        return self.root() == p
    
    def is_leaf(self, p):
        """포지션 p가 단말 노드인지 여부 반환"""
        return self.num_children(p) == 0
    
    def is_empty(self):
        """트리가 비어있는지 여부 반환"""
        return len(self) == 0
    
    def depth(self, p):
        """포지션 p의 깊이 반환"""
        if self.is_root(p):
            return 0
        else:
            return 1 + self.depth(self.parent(p))
        
    def _height2(self, p):
        """포지션 p를 루트로 하는 서브트리의 높이 반환"""
        if self.is_leaf(p):
            return 0
        else:
            # 자식을 가져와서 c로 순회하고 그 높이를 구함, 그 중 최댓값을 반환
            return 1 + max(self._height2(c) for c in self.children(p)) # 자식 노드들의 높이 중 최댓값
        
    def height(self, p=None):
        """트리의 높이 반환"""
        if p is None:
            p = self.root()
        return self._height2(p)

    
    

class BinaryTree(Tree):
    """이진 트리 추상 클래스"""
    
    def left(self, p):
        """포지션 p의 왼쪽 자식 포지션 반환"""
        raise NotImplementedError("Must be implemented by subclass")
    
    def right(self, p):
        """포지션 p의 오른쪽 자식 포지션 반환"""
        raise NotImplementedError("Must be implemented by subclass")
    
    def sibling(self, p):
        """포지션 p의 형제 포지션 반환"""
        parent = self.parent(p)
        if parent is None:
            return None  # 루트 노드인 경우 형제가 없음
        else:
            if p == self.left(parent):
                return self.right(parent)
            else:
                return self.left(parent)

    def children(self, p):
        """포지션 p의 자식 포지션들 반환"""
        if self.left(p) is not None:
            yield self.left(p)
        if self.right(p) is not None:
            yield self.right(p)

# Linked Binary Tree 구현  Tree -> BinaryTree -> LinkedBinaryTree
class LinkedBinaryTree(BinaryTree):
    class Node:
        __slots__ = 'element', 'parent', 'left', 'right'
        
        def __init__(self, element, parent=None, left=None, right=None):
            self.element = element
            self.parent = parent
            self.left = left
            self.right = right
    
    class Position(BinaryTree.Position):
        def __init__(self, container, node):
            self._container = container
            self._node = node
        
        def element(self):
            return self._node.element
        
        def __eq__(self, other):
            return type(other) is type(self) and other._node is self._node
          
    def _validate(self, p): # 포지션 p가 유효한지 검사
        if not isinstance(p, self.Position):
            raise TypeError("p must be proper Position type")
        if p._container is not self:
            raise ValueError("p does not belong to this container")
        if p._node.parent is p._node:  # convention for deprecated nodes
            raise ValueError("p is no longer valid")
        return p._node
    
    def _make_position(self, node):
        return self.Position(self, node) if node is not None else None
    
    # 이진트리 생성자
    def __init__(self):
        self._root = None
        self._size = 0

    # 공개 접근 메서드들
    def __len__(self):
        return self._size
    
    def root(self):
        return self._make_position(self._root)

    def parent(self, p):
        node = self._validate(p)
        return self._make_position(node.parent)
    
    def left(self, p):
        node = self._validate(p)
        return self._make_position(node.left)
    
    def right(self, p):
        node = self._validate(p)
        return self._make_position(node.right)
    
    def num_children(self, p):
        node = self._validate(p)
        count = 0
        if node.left is not None: # 왼쪽 자식이 있으면 count 증가
            count += 1
        if node.right is not None: # 오른쪽 자식이 있으면 count 증가
            count += 1
        return count
    
    def _add_root(self, e):
        """트리에 루트 노드를 추가"""
        if self._root is not None:
            raise ValueError("Root exists")
        self._root = self.Node(e)
        self._size = 1
        return self._make_position(self._root)
    
    def _add_left(self, p, e):
        """포지션 p에 왼쪽 자식 노드를 추가"""
        node = self._validate(p)
        if node.left is not None:
            raise ValueError("Left child exists")
        node.left = self.Node(e, parent=node)
        self._size += 1
        return self._make_position(node.left)
    
    def _add_right(self, p, e):
        """포지션 p에 오른쪽 자식 노드를 추가"""
        node = self._validate(p)
        if node.right is not None:
            raise ValueError("Right child exists")
        node.right = self.Node(e, parent=node)
        self._size += 1
        return self._make_position(node.right)
    
    def _replace(self, p, e):
        """포지션 p의 원소를 e로 교체"""
        node = self._validate(p)
        old = node.element
        node.element = e
        return old
    
    def _delete(self, p):
        """포지션 p의 노드를 삭제하고 그 원소를 반환"""
        node = self._validate(p)
        if self.num_children(p) == 2:
            raise ValueError("p has two children")
        child = node.left if node.left else node.right  # 자식 노드
        if child is not None:
            child.parent = node.parent  # 자식의 부모를 갱신
        if node is self._root:
            self._root = child  # 루트 노드 갱신
        else:
            parent = node.parent
            if node is parent.left: # 왼쪽이 자식 노드인 경우
                parent.left = child
            else:
                parent.right = child # 오른쪽이 자식 노드인 경우
        self._size -= 1
        node.parent = node  # convention for deprecated node
        return node.element
    
    def _attach(self, p, t1, t2):
        """포지션 p에 두 개의 서브트리 t1, t2를 첨부"""
        node = self._validate(p)

        if not self.is_leaf(p):
            raise ValueError("position must be leaf")
        if not type(self) is type(t1) is type(t2): # 세 노드의 타입이 동일한지 검사
            raise TypeError("Tree types must match")
        self._size += len(t1) + len(t2)

        if not t1.is_empty():
            t1_root = t1._root
            t1_root.parent = node
            node.left = t1_root
            t1._root = None
            t1._size = 0
        if not t2.is_empty():
            t2_root = t2._root
            t2_root.parent = node
            node.right = t2_root
            t2._root = None
            t2._size = 0
    

if __name__ == "__main__":
    tree = LinkedBinaryTree()
    root = tree._add_root(1)
    left = tree._add_left(root, 2)
    right = tree._add_right(root, 3)

    tree._add_left(left, 4)
    tree._add_right(left, 5)
    tree._add_left(right, 6)
    tree._add_right(right, 7)
    print("트리의 높이:", tree.height())
    print("노드 2의 깊이:", tree.depth(left))
    print("노드 3의 형제 노드 원소:", tree.sibling(right).element())

