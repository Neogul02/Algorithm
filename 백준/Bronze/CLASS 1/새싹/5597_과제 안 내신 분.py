student_list = list(range(1, 31))

for _ in range(28):
    student_list.remove(int(input()))

print(min(student_list))
print(max(student_list))
