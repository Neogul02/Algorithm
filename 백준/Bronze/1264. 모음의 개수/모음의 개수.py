mo = ['a', 'e', 'i', 'o', 'u']

while True:
    word = input()
    if word == '#':
        break
    count = 0
    for char in word:
        if char.lower() in mo:
            count += 1
    print(count)