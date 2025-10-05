import sys

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    
    A, B, C = map(int, line.split())
    gap1 = B - A
    gap2 = C - B
    result = max(gap1, gap2) - 1
    
    print(result)