class _Node:
    __slot__ = '_element', '_next' # 노드의 속성을 제한하는걸 설정 __slot__

    def __init__(self, element, next):
        self._element = element
        self._next = next
