katok = ['다현', '정연', '쯔위', '사나', '지효']
katok.append(None)

katok[5] = '모모'
print(katok)

katok.append(None)
katok[6] = katok[5]
katok[5] = None

print(katok)

# 배열의 맨 끝위치는 len(katok)-1

katok = []  # 빈 배열
katok.append(None)
katok[len(katok)-1] = '다현'
print(katok)

katok.append(None)
katok[len(katok)-1] = '정연'
print(katok)

# == def


def add_data(friend):
    katok.append(None)
    katok[len(katok)-1] = friend  # 끝자리에 new friend 추가

print(katok)
