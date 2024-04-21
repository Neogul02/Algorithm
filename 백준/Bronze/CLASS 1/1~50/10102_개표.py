# A와 B가 한 오디션 프로의 결승전에 진출했다. 결승전의 승자는 심사위원의 투표로 결정된다.
# 심사위원의 투표 결과가 주어졌을 때, 어떤 사람이 우승하는지 구하는 프로그램을 작성하시오.

# 예제 입력 1 
# 6
# ABBABB

# 예제 출력 1 
# B

userNum = int(input());
# userNum의 갯수만큼 투표를 받는다.
vote = list(str(input()));

A = B = 0;
# A와 B의 투표수를 카운트한다.
for i in vote:
    if i == 'A':
        A += 1;
    elif i == 'B':
        B += 1;
if(A>B):
    print('A');
elif(A<B):
    print('B');
else:
    print('Tie');

