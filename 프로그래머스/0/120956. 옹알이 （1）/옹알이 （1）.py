def solution(babbling):
    answer = 0
    for b in babbling:
        for word in ["aya", "ye", "woo", "ma"]:
            if word in b:
                b = b.replace(word, ' ')
        if len(b.strip()) == 0:
            answer += 1
    return answer