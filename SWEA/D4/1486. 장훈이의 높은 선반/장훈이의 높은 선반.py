T = int(input())

for tc in range(1, T+1):
    N, B = map(int, input().split())
    H = list(map(int, input().split()))

    ans = [float('inf')]

    def dfs(idx, current_sum):

        if current_sum >= ans[0]:
            pass

        if current_sum >=B:
            ans[0] = min(ans[0], current_sum)
            return

        if idx == N:
            return

        dfs(idx+1, current_sum + H[idx])
        dfs(idx+1, current_sum)
    dfs(0, 0)
    print(f'#{tc} {ans[0]-B}')

