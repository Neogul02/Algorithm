def isStackFull(): # 스택이 꽉 찼는지 확인하는 함수
    global SIZE, stack, top;
    if (top>=SIZE-1): # top이 SIZE-1 일때 꽉찬것
        return True;
    else:
        return False;

def isStackEmpty(): # 스택이 비었는지 확인하는 함수
    global SIZE, stack, top;
    if (top==-1): # top 이 -1 이면 비어있는것
        return True;
    else:
        return False;

def push(data): # 스택에 데이터를 넣는 함수
    global SIZE, stack, top;
    if (isStackFull()):
        print("스택이 꽉 찼습니다.");
        return;
    top += 1;
    stack[top] = data;

def pop(): # 스택에서 데이터를 빼는 함수
    global SIZE, stack, top;
    if (isStackEmpty()):
        print("스택이 비었습니다.");
        return None;
    data = stack[top];
    stack[top] = None;
    top -= 1;
    return data;

def peek(): # 스택의 top에 있는 데이터를 확인하는 함수
    global SIZE, stack, top;
    if(isStackEmpty()):
        print("스택이 비었습니다.");
        return None;
    return stack[top];

## push
SIZE = 5;
stack = ['커피', '녹차', '꿀물', '콜라',None];
top= 3;

print (stack);
push("환타");
print (stack);
push("사이다");

## pop
SIZE = 5;
stack = ['coffee', None, None, None, None];
top = 0;

pop();
print(stack);
pop();

## peek
SIZE = 5;
top =2
stack =['coffee', 'green tea', 'honey water', None, None];

print(stack);
print('top의 데이터확인 -> ', peek());
print(stack);

## 응용 peek and push
