def solution(spell, dic):
    
    for d in range(len(dic)):
        if sorted(dic[d]) == sorted(spell):
            return 1
        
    return 2