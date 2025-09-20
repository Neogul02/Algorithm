import sys

text = sys.stdin.read()
freq = {}

for c in text:
    if c.isalpha():
        freq[c] = freq.get(c, 0) + 1

max_count = max(freq.values())
result = [c for c in sorted(freq) if freq[c] == max_count]
print(''.join(result))