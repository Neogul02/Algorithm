import collections

def solution(genres, plays):
    genre_total = collections.defaultdict(int)
    genre_songs = collections.defaultdict(list)

    for idx, (g, p) in enumerate(zip(genres, plays)):
        genre_total[g] += p
        genre_songs[g].append((p, idx))

    sorted_genres = sorted(genre_total.keys(), key=lambda g: genre_total[g], reverse=True)

    result = []
    for g in sorted_genres:
        songs = sorted(genre_songs[g], key=lambda x: (-x[0], x[1]))
        for p, idx in songs[:2]:
            result.append(idx)

    return result