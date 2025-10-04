def F(x):
    x_str = str(x)
    first_digit = int(x_str[0])
    digit_count = len(x_str)
    return first_digit * digit_count

def is_FA_number(x):
    seen = set()
    current = x
    
    while current not in seen:
        seen.add(current)
        current = F(current)
        
        if current < 10:
            return current == current
    
    return True

if __name__ == "__main__":
    x = int(input())
    if is_FA_number(x):
        print('FA')
    else:
        print('NFA')