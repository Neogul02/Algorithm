function solution(arr, k) {
    let answer = []
    answer = k % 2 == 0 ? arr.map(x=>x+k) : arr.map(x=>x*k)
    return answer;
}