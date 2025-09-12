import sys
input = sys.stdin.readline

N, M = map(int, input().split())

no_hear_people = set()
no_see_people = set()

for _ in range(N):
    no_hear_people.add(input().strip())

for _ in range(M):
    no_see_people.add(input().strip())
    
no_hear_and_see_people = sorted(no_hear_people & no_see_people)

print(len(no_hear_and_see_people))
for person in no_hear_and_see_people:
    print(person)