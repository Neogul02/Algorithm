def solution(dots):
    dots.sort()
    x_min, y_min = dots[0]
    x_max, y_max = dots[-1]

    return (x_max-x_min) * (y_max-y_min)