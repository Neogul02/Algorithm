class Empty(Exception):
    # pass
    print('아무것도 없어요!')

class ArrayStack:
    def __init__(self):
        self._data = [] # 스택에 활용될 리스트

    def __len__(self):
        return len(self._data)

    def is_empty(self):
        return len(self._data) == 0

    def push(self, e):
        self._data.append(e)

    def top(self):
        if self.is_empty():
            raise Empty('Stack is empty')
        return self._data[-1]

    def pop(self):
        if self.is_empty():
            raise Empty('Stack is empty')
        return self._data.pop()

    def __str__(self):
        return " ".join([str(i) for i in self._data])

def is_matched(expr):
    lefty = '({['
    righty = ')}]'

    S = ArrayStack()

    for c in expr:
        if c in lefty:
            S.push(c)
        elif c in righty:
            if S.is_empty():
                print('닫는 괄호가 더 많아요')
                return False
            if righty.index(c) != lefty.index(S.pop()):
                print('괄호짝이 맞지 않아요')
                return False
    return S.is_empty()

def is_matched_html(raw):
    S= ArrayStack()
    j = raw.find('<')

    while j != -1:
        k = raw.find('>', j+1) # j 다음 위치부터 '>' 찾기
        if k == -1:
            return False
        tag = raw[j+1:k] # 태그 추출 <body> -> body or </body> -> /body
        if not tag.startswith('/'):
            S.push(tag)
        else: # 닫는 태그
            if S.is_empty():
                return False
            if tag[1:] != S.pop():
                return False
        j = raw.find('<', k+1)
        # print(f'j: {j}')

    return S.is_empty()


if __name__ == "__main__":
    S = ArrayStack()
    S.push(5)
    S.push(3)

    # print(f'len is: {len(S)}') # 2
    # print(f'pop: {S.pop()}') # 3
    # print(f'is empty: {S.is_empty()}') # False
    # print(f'top: {S.top()}') # 5
    # print(f'len is: {len(S)}') # 1
    # print(f'pop: {S.pop()}') # 5
    # print(f'is empty: {S.is_empty()}') # True
    # print(f'pop: {S.pop()}') # Error

    # ----

    txt = '([{()}])'
    print(is_matched(txt)) # True

    txt = '}'
    print(is_matched(txt)) # False

    txt = '([{(]})]'
    print(is_matched(txt))  # False

    # ----

    print(is_matched_html('<body><center><h1>Title</h1></center></body>')) # True
    is_matched_html('<body><center><h1>Title</h1></body></center>') # False
    is_matched_html('<body><center><h1>Title</h1></center>') # False

