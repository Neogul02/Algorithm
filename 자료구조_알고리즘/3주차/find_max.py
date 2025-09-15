def fine_max(data):
    biggest = data[0]
    for val in data:
        if val > biggest:
            biggest = val
    return biggest