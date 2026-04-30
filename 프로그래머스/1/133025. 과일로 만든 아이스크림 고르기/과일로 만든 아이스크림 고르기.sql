-- 코드를 입력하세요
-- 출하 번호, 아이스크림 맛, 상반기 아이스크림 총주문량
SELECT f.FLAVOR
FROM FIRST_HALF f
JOIN ICECREAM_INFO i ON (i.FLAVOR = f.FLAVOR)
WHERE TOTAL_ORDER > 3000 AND INGREDIENT_TYPE = 'fruit_based';