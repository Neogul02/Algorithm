g, p, t = map(int, input().split())

tests = g * p
group_tests = g + (t * p)

if tests < group_tests:
    print(1)
elif tests > group_tests:
    print(2)
else:
    print(0)