# NumPy 정리 — 정규방정식 / SVD / 유사역행렬 / 최소제곱법

---

## 먼저 알아야 할 배경 — 선형 회귀가 뭔가?

> "키(X)로 몸무게(y)를 예측하는 공식 `y = θ₀ + θ₁x`를 찾고 싶다"
>
> → 여기서 **θ(세타)** 가 공식의 계수(가중치). 이걸 데이터로부터 구하는 게 목표.

선형 회귀를 행렬식으로 쓰면:

$$X\theta = y$$

- $X$: 입력 데이터 행렬 (m개 샘플 × n개 피처)
- $\theta$: 찾아야 할 계수 벡터
- $y$: 정답(레이블) 벡터

문제는 **$X$가 정사각형이 아니라서 그냥 역행렬을 못 구한다**. → 아래 방법들이 필요한 이유!

---

## 핵심 개념 요약

| 방법 | 한글 이름 | 수식 | 왜 쓰나? |
|------|-----------|------|---------|
| **Normal Equation** | 정규방정식 | $\theta = (X^TX)^{-1}X^Ty$ | 가장 직관적인 방법. 단, 역행렬 필요 |
| **SVD** | 특이값 분해 | $A = U\Sigma V^T$ | 어떤 행렬도 3개로 쪼갤 수 있는 범용 도구 |
| **Pseudo-inverse** | 유사역행렬 | $A^+ = V\Sigma^+U^T$ | 역행렬이 없어도 비슷한 역할을 하는 행렬 |
| **Least Squares** | 최소제곱법 | `np.linalg.lstsq` | 실무에서 제일 안전하고 권장되는 방법 |

---

## 용어 / 변수명 줄임말 해설

| 기호·약어 | 풀네임 | 한국어 의미 |
|-----------|--------|------------|
| `linalg` | **Lin**ear **Alg**ebra | 선형대수 |
| `inv` | **Inv**erse | 역행렬 |
| `svd` | **S**ingular **V**alue **D**ecomposition | 특이값 분해 |
| `pinv` | **P**seudo-**Inv**erse | 유사(가짜) 역행렬 |
| `lstsq` | **L**east **Sq**uares | 최소제곱법 |
| `diag` | **Diag**onal | 대각 (행렬의 대각선) |
| `U`, `s`, `Vt` | SVD 결과 행렬 이름 | U·Σ(시그마)·Vᵀ |
| `Σ` (시그마) | **S**ingular values matrix | 특이값들을 모은 대각행렬 |
| `θ` (세타) | **Theta** | 학습할 계수(가중치) 벡터 |
| `rcond` | **R**elative **Cond**ition number | 수치 안정성 기준값 |
| `T` (`.T`) | **T**ranspose | 전치 (행↔열 뒤집기) |
| `@` | Matrix Multiply | 행렬 곱셈 연산자 |
| `c_` | **C**olumn stack | 열 방향으로 쌓기 |
| `ones` | ones array | 1로 가득 찬 배열 |
| `bias` | bias term | 편향항 (절편, y절편 역할) |

---

## 사용된 NumPy 메서드 (왜 쓰는지 포함)

### 배열 생성

```python
np.ones((n, 1))
# ones = "1로 가득 찬 배열"
#
# 왜 쓰나?
#   선형 회귀 공식 y = θ₀ + θ₁x 에서 θ₀ (절편)을 행렬 계산에 포함시키려면
#   X 앞에 1짜리 열을 하나 붙여야 함 → 그게 바로 편향(bias) 항
#   np.ones((100, 1)) → 100행 1열짜리 모두 1인 배열 생성
```

```python
np.c_[A, B]
# c_ = column stack (열 방향으로 이어붙이기)
#
# 왜 쓰나?
#   np.ones(...)으로 만든 편향 열을 기존 X 데이터 앞에 붙여서
#   [1, x₁], [1, x₂], ... 형태의 X_b (bias 포함된 X) 를 만들기 위해
np.c_[np.ones((len(X), 1)), X]
# → X_b = [[1, carat₁], [1, carat₂], ...]
```

```python
np.diag(v)
# diag = diagonal (대각선)
#
# 왜 쓰나?
#   SVD 결과로 나오는 s(특이값)는 1D 배열인데,
#   행렬 곱을 하려면 대각행렬(Σ) 형태가 필요함
#   np.diag([3, 2, 1]) → [[3,0,0],[0,2,0],[0,0,1]]
```

---

### 행렬 연산

```python
A.T
# T = Transpose (전치)
#
# 왜 쓰나?
#   비정사각 행렬 X (m×n) 에 X.T (n×m) 를 곱하면
#   X.T @ X 는 (n×n) 정사각행렬이 됨
#   → 정사각이어야 역행렬을 구할 수 있으므로 필수 과정
```

```python
A @ B
# @ = 행렬 곱셈 연산자 (Python 3.5+)
# np.dot(A, B) 와 동일
#
# 왜 쓰나?
#   수학의 행렬 곱 AB를 코드로 표현. 선형대수 계산 전반에 사용
```

---

### 선형대수 (`np.linalg` = Linear Algebra)

```python
np.linalg.inv(A)
# inv = Inverse (역행렬)
#
# 왜 쓰나?
#   정규방정식 θ = (X^T X)^{-1} X^T y 에서 (X^T X)^{-1} 부분을 계산
#   역행렬 A⁻¹은 수학에서 분수의 "÷A" 역할을 함
#
# 주의: 정방행렬(정사각)이고 역행렬이 존재할 때만 동작
#        역행렬이 없으면 LinAlgError 발생 → pinv 나 lstsq 써야 함
```

```python
U, s, Vt = np.linalg.svd(X, full_matrices=False)
# svd = Singular Value Decomposition (특이값 분해)
#
# 왜 쓰나?
#   어떤 행렬 A든 A = U @ Σ @ Vᵀ 세 행렬의 곱으로 쪼갤 수 있음
#   → 쪼개면 역행렬 계산, 데이터 압축, 유사역행렬 등에 활용 가능
#   마치 숫자를 소인수분해 하듯, 행렬을 의미 있는 요소로 분해하는 것
#
# 반환값:
#   U  (m×r): 좌특이벡터 행렬. "데이터의 방향 정보 (행 관점)"
#   s  (r,) : 특이값(Singular Values). 각 방향의 중요도(크기). 내림차순
#   Vt (r×n): 우특이벡터 행렬의 전치. "피처 공간의 방향 정보"
#
# full_matrices=False → 필요한 부분만 계산 (경제적 SVD, 속도 빠름)
#
# 복원 검증: X ≈ U @ np.diag(s) @ Vt
```

```python
np.linalg.pinv(A)
# pinv = Pseudo-Inverse (유사역행렬)
#
# 왜 쓰나?
#   inv()는 역행렬이 없으면 에러가 나는데,
#   pinv()는 내부적으로 SVD를 써서 역행렬이 없어도 "최대한 비슷한" 역행렬을 만들어줌
#   → 직사각 행렬, 다중공선성 있는 행렬 모두 대응 가능
#
# 수식: A⁺ = V Σ⁺ Uᵀ
#   (Σ⁺ = 특이값의 역수로 채운 대각행렬, 0은 그대로 0)
```

```python
theta, residuals, rank, s = np.linalg.lstsq(X, y, rcond=None)
# lstsq = Least Squares (최소제곱법)
#
# 왜 쓰나?
#   "오차의 제곱합이 최소가 되는 θ를 찾아라" 는 문제를 수치적으로 안전하게 풀어줌
#   내부적으로 SVD + 유사역행렬을 사용 → 역행렬 없어도 OK
#   실무에서 정규방정식 대신 이걸 쓰는 게 표준
#
# 반환값:
#   theta     : 찾은 계수 벡터 [θ₀(절편), θ₁(기울기)]
#   residuals : 잔차 제곱합 Σ(y - ŷ)². 작을수록 예측이 정확
#   rank      : X 행렬의 랭크(유효 차원 수). 피처 수보다 작으면 다중공선성 의심
#   s         : 특이값 배열. 0에 가까운 값이 있으면 수치적으로 불안정
#
# rcond=None → NumPy가 알아서 기준값 결정 (경고 없애는 관용구)
```

---

## 흐름 비교

```
[정규방정식] 가장 직관적, 하지만 역행렬 필요
X → X.T @ X → inv(X.T @ X) → θ = inv(X.T @ X) @ X.T @ y
※ 역행렬 없으면 실패 ✗

[SVD + 유사역행렬] 역행렬 없어도 동작
X → svd(X) → U, s, Vt → pinv(X) = Vt.T @ diag(1/s) @ U.T → θ = pinv(X) @ y
※ 항상 동작 ✓

[lstsq] 실무 표준 (내부적으로 위 과정을 자동으로 수행)
θ, residuals, rank, s = lstsq(X, y)
※ 가장 안전하고 편리 ✓✓
```

---

## 다중공선성 (Multicollinearity)

> "피처(입력) 끼리 선형 관계가 있을 때" 발생하는 문제

예: 두 번째 열 = 첫 번째 열 × 2

$$
X = \begin{bmatrix} 1 & 2 \\ 2 & 4 \\ 3 & 6 \end{bmatrix}
\Rightarrow \det(X^TX) = 0 \Rightarrow \text{역행렬 없음}
$$

→ `inv()` 실패, **`pinv()` 또는 `lstsq()` 필요**

---

## 한눈에 보는 `np.linalg` 비교

| 함수 | 풀네임 | 쓰는 상황 | 역행렬 필요 | 다중공선성 대응 |
|------|--------|----------|:-----------:|:---------------:|
| `inv` | Inverse | 역행렬 직접 계산 | ✓ 있어야 함 | ✗ |
| `pinv` | Pseudo-Inverse | 역행렬 없어도 계산 | ✗ | ✓ |
| `lstsq` | Least Squares | 실무 선형회귀 풀이 | ✗ | ✓ |
| `svd` | Singular Value Decomposition | 행렬 분해·분석 | ✗ | ✓ |

