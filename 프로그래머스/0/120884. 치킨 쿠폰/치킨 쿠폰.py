def solution(chicken):
    answer = 0
    while chicken >= 10:
        service = chicken // 10
        rest_coupon = chicken % 10
        
        answer += service
        chicken = service + rest_coupon
        
    return answer

solution(1081)