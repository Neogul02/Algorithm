stack = [None, None, None, None, None];
top = -1;


top += 1;
stack[top] = "커피";
top += 1;
stack[top] = "녹차";
top += 1;
stack[top] = "꿀물";


print("---- 스택 상태 ----");
for i in range(len(stack)-1, -1, -1):
    print(stack[i]);

print("------------------");
data = stack[top];
stack[top] = None;
top -= 1;
print("pop --> ", data);

data = stack[top];
stack[top] = None;
top -= 1;
print("pop --> ", data);

data = stack[top];
stack[top] = None;
top -= 1;
print("pop --> ", data);

print("------------------");

print("---- 스택 상태 ----");
for i in range(len(stack)-1, -1, -1):
    print(stack[i]);

SIZE = 5;
stack = ['커피', '녹차', '꿀물', '콜라','환타'];
top =4;

def isStackFull():
    global SIZE, stack, top;
    if (top >= SIZE-1):
        return True;
    else:
        return False;

print("---- 스택 상태 ----");
for i in range(len(stack)-1, -1, -1):
    print(stack[i]);

print("------------------");

SIZE = 5;
stack= [None, None, None, None, None];
top = -1;

def isStackEmpty():
    global SIZE, stack, top;
    if (top == -1):
        return False;
    else:
        return True;



print("스택이 꽉 찼는지 여부 -->",isStackFull())