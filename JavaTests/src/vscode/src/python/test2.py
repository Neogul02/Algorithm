year = 2014
month = 4
day = 2

N = int(input())

def isyoon(year):
    if year % 400 == 0: return True
    if year % 100 == 0: return False
    if year % 4 == 0: return True
    return False
    

for _ in range(N-1):
    day += 1
    
    # 2월
    if(month==2):
        if(isyoon(year)):
            if (day>29):
                day = 1
                month+=1
        else:
            if(day>28):
                day=1
                month+=1
    # 4,6,9,11 월            
    elif(month==4 or month==6 or month==9 or month==11): 
        if(day>30):
            day = 1
            month+=1 
    # 나머지 31일까지의 월
    else: 
        if(day>31):
            day=1
            month+=1
    
    if(month>12):
        month=1
        year+=1

# YYYY-MM-DD
print(f'{year:04d}-{month:02d}-{day:02d}')