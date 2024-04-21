word = input().upper()  # mississipi
max_num = 0

for i in set(word):     # {'I', 'P', 'M', 'S'}
    if word.count(i) > max_num:
        max_num = word.count(i)
        max_word = i

    elif word.count(i) == max_num:
        max_word = '?'

print(max_word)
