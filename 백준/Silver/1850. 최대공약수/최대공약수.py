import sys
import math

a, b = map(int, sys.stdin.readline().split())
g = math.gcd(a, b)
sys.stdout.write('1' * g + '\n')