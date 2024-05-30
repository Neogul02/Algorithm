def solution(id_pw, db):

    for i in range(len(db)):
        print(db[i][0])
        if id_pw[0] == db[i][0]:
            if id_pw[1] == db[i][1]:
                return "login"
            return "wrong pw"
    
    return "fail"