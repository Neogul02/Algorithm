def reverse(S, start, stop):
    print(f"S: {S}, start: {start}, stop: {stop}")

    if start < stop - 1:
        S[start], S[stop - 1] = S[stop - 1], S[start]
        reverse(S, start + 1, stop - 1)

if __name__ == "__main__":
    S = [1, 2, 3, 4, 5]
    reverse(S, 0, len(S))

    print(S)