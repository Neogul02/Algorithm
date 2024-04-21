# 첫 번째 줄에 설문조사를 한 사람의 수 N (1 ≤ N ≤ 101, N은 홀수)가 주어진다.
# 다음 N개의 줄에는 각 줄마다 각 사람이 설문 조사에 어떤 의견을 표명했는지를 나타내는 정수가 주어진다. 
# 0은 준희가 귀엽지 않다고 했다는 뜻이고, 1은 준희가 귀엽다고 했다는 뜻이다.

#예제 입력 1 
# 3
# 1
# 0
# 0
# 예제 출력 1 
# Junhee is not cute!

N = int(input()); # 설문조사를 한 사람의 수 N은 홀수여야함

cute = 0;

for i in range(N):
    vote = int(input());
    if(vote == 1):
        cute += 1;
    else:
        cute -= 1;
print('Junhee is cute!') if cute > 0 else print('Junhee is not cute!');