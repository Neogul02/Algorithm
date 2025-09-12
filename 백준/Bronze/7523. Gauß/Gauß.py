for i in range(int(input())):
  n, m = map(int, input().split()) 
  print(f'Scenario #{i + 1}:\n{(m - n + 1) * (n + m) // 2}\n')