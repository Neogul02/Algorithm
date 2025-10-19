class CreditCard:
    # 생성자
    def __init__(self, customer:str, bank:str, account:str, limit:int, balance:int=0):
        """
        :param customer: 신용카드 소지자
        :param bank: 카드 은행
        :param account: 카드 계좌 번호
        :param limit: 신용 한도
        :param balance: 초기 잔액 (기본값 0)

        _varname -> private variable
        __varname -> strongly private variable (name mangling)
        """
        self.customer = customer
        self.bank = bank
        self._account = account
        self._limit = limit
        self._balance = balance # 초기 잔액 0

    # getter
    def get_customer(self):
        return self.customer

    def get_bank(self):
        return self.bank

    def get_account(self):
        return self._account

    def get_limit(self):
        return self._limit

    def get_balance(self):
        return self._balance

    # method
    def charge(self, price):

        # 타입/값 검사
        if not isinstance(price, (int, float)):
            raise TypeError("price는 숫자(int 또는 float)여야 합니다")
        if price < 0:
            raise ValueError("price는 음수일 수 없습니다")

        # 한도가 충분한 경우 결제
        # 결제가 성공하면 True, 실패하면 False
        if price + self._balance > self._limit:
            return False
        else:
            self._balance += price
            return True

    def make_payment(self, amount):
        # 카드 대금 지불
        self._balance -= amount

if __name__ == '__main__':
    a = CreditCard('Choe', 'Shinhan', '1111 2222 3333 4444', 10000000)
    print(a.get_balance(), a.get_limit())
    print(a.charge(30000))
    print(a.get_balance(), a.get_limit())