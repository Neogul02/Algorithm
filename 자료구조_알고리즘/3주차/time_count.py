from time import time

def measure_time():
    start_time = time()
    total = 0
    for i in range(1, 10000001):
        total += i
    end_time = time()
    print("1부터 1000만까지의 합:", total)
    print("걸린 시간:", end_time - start_time, "초")

if __name__ == "__main__":
    measure_time()