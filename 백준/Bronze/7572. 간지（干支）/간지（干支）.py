N = int(input())
easy = "ABCDEFGHIJKL"

base_year = 2013
base_ji = 5
base_gan = 9

diff = N - base_year

ji = (base_ji + diff) % 12
gan = (base_gan + diff) % 10

print(f"{easy[ji]}{gan}")
