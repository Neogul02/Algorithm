A, B, C = input().split()
AI = int(A)
BI = int(B)
CI = int(C)
print('%d\n%d\n%d\n%d' % (((AI+BI) % CI), ((AI % CI) + (BI % CI)) %
      CI, (AI*BI) % CI, ((AI % CI) * (BI % CI)) % CI))
