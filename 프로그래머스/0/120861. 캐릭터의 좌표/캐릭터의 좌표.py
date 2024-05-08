def solution(keyinput, board):
    user = [0,0]

    for i in range(len(keyinput)):
        if keyinput[i] == 'left' and user[0]-1 >= -(board[0]//2):
            user[0] -= 1
        if keyinput[i] == 'right' and user[0]+1 <= board[0]//2:
            user[0] += 1
        if keyinput[i] == 'up' and user[1]+1 <= board[1]//2:
            user[1] += 1
        if keyinput[i] == 'down' and user[1]-1 >= -(board[1]//2):
            user[1] -= 1

    return user