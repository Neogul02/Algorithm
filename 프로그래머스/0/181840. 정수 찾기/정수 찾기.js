function solution(num_list, n) {
    var answer = 0;
    (num_list.indexOf(n)<0 ? answer = 0 : answer = 1)
    return answer;
}