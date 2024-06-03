def solution(common):
    # 등차인 경우
    if common[1] - common[0] == common[-1] - common[-2]:
        return common[-1] + (common[1] - common[0])
    # 등비인 경우
    else:
        return common[-1] * (common[-1] // common[-2])