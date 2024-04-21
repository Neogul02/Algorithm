max_num = int(input())
n, Q1_Value, Q2_Value, Q3_Value, Q4_Value, Axis_Value = [0]*6

while n != max_num:
    x, y = map(int, input().split())
    if x == 0 or y == 0:  # axis
        Axis_Value += 1
    elif x > 0 and y > 0:  # + + 1
        Q1_Value += 1
    elif x < 0 and y > 0:  # - + 2
        Q2_Value += 1
    elif x < 0 and y < 0:  # - - 3
        Q3_Value += 1
    elif x > 0 and y < 0:  # + - 4
        Q4_Value += 1
    n += 1

print("Q1:", Q1_Value)
print("Q2:", Q2_Value)
print("Q3:", Q3_Value)
print("Q4:", Q4_Value)
print("AXIS:", Axis_Value)