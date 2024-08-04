def solution(rny_string):
    answer=[]
    for i in rny_string:
        if i == "m":
            answer.append("rn")
        else: answer.append(i);
        
    return ''.join(answer)