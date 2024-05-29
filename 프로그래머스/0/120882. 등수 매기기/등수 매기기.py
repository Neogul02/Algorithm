def solution(score):
    avgList =[] # 평균리스트
    
    for i in range(len(score)):
        avgList.append(sum(score[i])/2)

    sorted_avgList = sorted(avgList, reverse=True)  # 평균리스트 큰값 순 정렬

    answer = []
    
    for j in avgList:
        answer.append(sorted_avgList.index(j)+1)

    return answer