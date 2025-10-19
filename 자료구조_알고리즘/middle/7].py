# 7-3 단일연결 리스트의 노드 수를 계산하는 재귀
class Node:
    def __init__(self, item):
        self.item = item
        self.next = None

def count_nodes(node):
    if node is None:
        return 0
    else:
        return 1 + count_nodes(node.next)

# 7-4 단일연결 리스트의 두 노드 x,y에 대한 참조가 주어졌을때, 위치를 서로 바꾸는 방법
# 노드의 데이터가 아닌 위치를 바꾸어야함
# x,y의 이전 노드를 찾고
# 이전 노드의 next를 서로 바꾸고
# x,y의 next를 서로 바꾸면됨

# 7-5 순환 연결 리스트에서 노드 수를 계산하는 함수
def count_circular_nodes(head):
    if head is None:
        return 0
    count = 1
    current = head.next
    while current != head:
        count += 1
        current = current.next
    return count

# 7-6 순환연결리스트 노드에 대한 참조 x,y 두 위치가 같은 리스트에 있는지 확인하는 함수
def are_in_same_circular_list(x, y):
    if x is None or y is None:
        return False
    current = x
    while True:
        if current == y:
            return True
        current = current.next
        if current == x:
            return False

# 7-7 이중연결리스트에서 중간 노드를 찾는 함수
def find_middle_node(dll):
    """
    _DoublyLinkedBase 또는 헤더/트레일러 센티널이 있는 리스트 인스턴스에서
    중간 노드를 반환. 비어있으면 None 반환.
    짝수 길이일 경우 왼쪽 중간 노드를 반환함.
    """
    if dll.is_empty():
        return None

    left = dll._header._next     # 첫 실제 노드
    right = dll._trailer._prev   # 마지막 실제 노드

    # left와 right가 같아지거나(홀수) left._next가 right가 될 때(짝수) 반복 종료
    while True:
        if left == right or left._next == right:
            break
        left = left._next
        right = right._prev

    return left  # 노드 객체 반환. 값만 필요하면 ._element 사용
