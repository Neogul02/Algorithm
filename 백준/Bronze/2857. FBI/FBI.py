agents = [input() for _ in range(5)]
isFBI = False
for idx, agentsCode in enumerate(agents):
    if("FBI" in agentsCode):
        isFBI = True
        print(idx+1, end=' ')
        continue

if isFBI==False: print('HE GOT AWAY!')