A = list(map(int, input().split()))
B = list(map(int, input().split()))
score = 0
for i in range(10):
  if A[i] > B[i]:
    score += 1
  elif B[i] > A[i]:
    score -= 1

if score > 0:
  print('A')
elif score < 0:
  print('B')
else:
  print('D')
