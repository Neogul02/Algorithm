# 2-1 사람 생명에 치명적일수 있는 소프트웨어
# 비행기 조종, 원자력 발전소, 자율주행 자동차

# 2-2 적응성이 중요한 소프트웨어
# 유튜브 - 네트워크 상태에 따라 화질 조정

# 2-4 꽃의 이름, 꽃잎 수, 꽃의 가격을 나타내는 Flower 클래스를 정의하라.
class Flower:
    def __init__(self, name:str, petal_count:int, price:float):
        self.name = name
        self.petal_count = petal_count
        self.price = price

    def set_name(self, name:str):
        self.name = name
    def get_name(self):
        return self.name

    def set_count(self, petal_count:int):
        self.petal_count = petal_count
    def get_count(self):
        return self.petal_count

    def set_price(self, price:float):
        self.price = price
    def get_price(self):
        return self.price

# 2-5 클래스 상속 구조가 깊을때의 문제점
# 유지보수가 어렵다. 상속 구조가 깊어질수록 변경의 영향 범위가 커진다.

# 2-6 클래스 상속구조가 매우 얕은경우의 단점
# 코드의 재사용성이 떨어진다. 상속 구조가 얕으면 중복 코드가 많아질 수 있다.