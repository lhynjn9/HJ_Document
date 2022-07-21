# Subset(부분집합)

> ####  부분집합의 수

- 원소가 n일 때 공집합을 포함한 부분집합의 수는 2^n개
- 각 원소를 포함시키거나 포함시키지 않는 2가지 경우를 모든 원소에 적용한 경우의 수와 같음



> ####  비트 연산자

- 알고리즘 문제에 비트 연산자를 활용하기도 함

  - 비트 연산자는 다른 연산자들에 비해 실행 사이클(CPU)이 짧음
  - 수식을 간결하게 표현

- `&` : 비트 단위로 AND

  ```python
  n = 5
  if n & 1: # 101 & 001 = 001
      print('홀수')
  else: # 101 & 000 = 000
      print('짝수')
  # i & (1 << j) : i의 j번째 비트가 1인지 아닌지를 검사 가능
  ```

- `|` : 비트 단위로 AND

- `a << b` : a의 비트열을 왼쪽으로 b 만큼이동

  ```python
  a = 1 << 5 
  # a는 100000 -> 6번 비트가 1인 값이 됨, 6번 비트는 2^5이므로 a는 결국 32가 됨
  # 1 << n : 2^n 즉, 원소가 n개일 경우의 모든 부분집합의 수를 의미
  ```

- `a >> b` : a의 비트열을 오른쪽으로 b 만큼이동

- 부분집합 생성 코드

  ```python
  arr = [3, 6, 7]
  
  n = len(arr) # n : 원소의 개수
  
  for subset in range(1 << n): # 1 << n : 부분집합의 개수
      # arr의 원소의 수만큼의 자릿수를 가진 비트 subset
      print(subset,'번째 부분집합의 원소 ==>', end=' ')
      for j in range(n):
          # 특정 원소가 현재 부분집합에 포함되어 있다면 1로 표현됨을 이용
          if subset & (1 << j) : # ex) subset의 j번 비트가 1인 경우(arr의 j번째 원소(arr[j])가 포함되어 있는 경우)
              print(arr[j], end=' ') # j번째 원소 출력
      print()
  print()
  ```

  

- 반복구조 부분집합

  ```python
  arr = 'ABCD';
  N = len(arr)
  bits = [0] * N
  
  for i in range(2):
      bits[0] = i
      for j in range(2):
          bits[1] = j
          for k in range(2):
              bits[2] = k
              print(bits)
  ```

  

- 재귀구조 부분집합

  ```python
  arr = 'ABCD'
  N = len(arr)
  bits = [0] * N
  
  def subset(k, n): # k: 함수호출의 깊이, 노드의 높이, for문의 중첩횟수
      if k == n:
          print(bits, end=' ')
          for i in range(n):
              if bits[i]: print(arr[i], end=' ')
          print()
      else:
          bits[k] = 0
          subset(k + 1, n)
          bits[k] = 1
          subset(k + 1, n)
  subset(0, N)
  ```

  
