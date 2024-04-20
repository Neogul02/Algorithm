N = int(input())
score = list(map(int, input().split()))
M = max(score)
for i in range(len(score)):
    score[i] = score[i]/M*100

print(sum(score)/len(score))