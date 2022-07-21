# Search(검색)

- 저장되어 있는 항목을 찾는 작업
- 목적하는 탐색 키를 가진 항목을 찾는 것
  - 탐색 키(search key) : 자료를 구별할여 인식할 수 있는 키



> #### 종류

- 순차 검색
- 이진 검색
- 해쉬



> #### 순차 검색(Sequential search)

- 일렬로 되어 있는 자료를 순서대로 검색하는 방법
  - 비교적 간단하고 직관적인 검색 방법
  - 알고리즘이 단순하여 구현이 쉽지만, 검색 대상의 수가 많은 경우에는 수행시간이 급격히 증가하여 비효율적임
  
- 정렬되어 있지 않은 경우
  - 검색 과정
  
    - 첫번째 원소부터 순서대로 검색 대상과 키 값이 같은 원소가 있는지 비교
    - 키 값이 동일한 원소를 찾으면 그 원소의 인덱스를 반환
    - 마지막까지 검색 대상을 찾지 못하면 검색 실패
  
  - 찾고자 하는 원소의 순서에 따라 비교 회수가 결정됨
  
  - 정렬되지 않은 자료에서의 순차 검색의 평균 비교 회수
  
    - (1/n) * (1+2+3+...+n) = (n+1)/2
  
  - **시간 복잡도 : O(n)**
  
  - 구현
  
    ```python
    def sequentialSearch(a, n, key): # n : 리스트의 길이, key : 찾으려는 값
        i = 0
        
        while i < n and a[i] != key:
        # i < n : 인덱스 범위 검사**
        # a[i] != key : 배열의 내부 검사
        # 값이 없으면 i에 1을 더해 다음 위치의 값 검사
        	i = i + 1
        if i < n:
            return i
        else:
            return -1
    ```
    
    
  
- 정렬되어 있는 경우

  - 검색 과정

    - 자료가 오름차순으로 정렬된 상태에서 검색을 실시한다고 가정
    - 자료를 순차적으로 검색하면서 키 값을 비교
    - 원소의 키 값이 검색 대상의 키 값보다 크면 찾는 원소가 없다는 것이므로 더 이상 검색하지 않고 검색을 종료

  - 찾고자 하는 원소의 순서에 따라 비교 회수가 결정됨

    - 정렬되어 있으므로, 검색 실패를 반환하는 경우 평균 비교 회수가 반으로 줄어듦

  - **시간 복잡도 : O(n)**

  - 구현

    ```python
    def sequentialSearch2(a, n, key): # n : 리스트의 길이, key : 찾으려는 값
        i = 0
        
        while i < n and a[i] < key:
        # i < n : 인덱스 범위 검사**
        # a[i] < key : 배열의 내부 검사
        # 값이 없으면 i에 1을 더해 다음 위치의 값 검사
        	i = i + 1
        if i < n and a[i] == key:
            return i
        else:
            return -1
    ```

    

> #### 이진 검색(Binary Search)

- 자료의 가운데에 있는 항목의 키 값과 비교하여 다음 검색의 위치를 결정하고 검색을 계속 진행하는 방법
  - 목적 키를 찾을 때 까지 이진 검색을 순환적으로 반복 수행함으로써 검색 범위를 반으로 줄여가면서 보다 빠르게 검색을 수행
  
- 이진 검색을 하기 위해서는 자료가 <u>**정렬된 상태**</u>여야 함

- 검색 과정

  - 자료의 중앙에 있는 원소를 선택
  - 중앙 원소의 값과 찾고자 하는 목표 값을 비교
  - 목표 값이 중앙의 원소의 값보다 작으면 자료의 왼쪽에 대해서 다시 검색을 수행하고, 크다면 자료의 오른쪽에 대해서 다시 검색을 수행
  - 찾고자 하는 값을 찾을 때 까지 위의 과정을 반복
  
- 시간 복잡도  : **O(logn)**

- 구현

  ```python
  # (1)
  def binarySearch(a, N, key)
  	start = 0
      end = N - 1
      while start <= end: # 시작 값이 인덱스 범위에 있어야함
          middle = (start + end) // 2 # 중간 원소의 인덱스 생성
          if a[middel] == key : # 검색 성공
              return true
          elif a[middle] > key: # 목표 값보다 중앙값이 더 큰 경우
              end = middle - 1
          else: # 목표 값보다 중앙값이 더 작은 경우
              strat = middle + 1
      return false # 검색 실패
  ```

  ```python
  # (2)
  def binarySearch2(a, low, high, key)
  	if low > high :# 검색 실패(인덱스 범위를 벗어난 경우)
      	return false
      else:
          middle = (low + high) // 2
          if key == a[middle] ; # 검색 성공
          	return True
          elif key < a[middle]: # 목표 값보다 중앙값이 더 큰 경우
              return binarySearch2(a, low, middle - 1, key)
          elif key > a[middle]: # 목표 값보다 중앙값이 더 작은 경우
              return binarySearch2(a, middle + 1, high, key)
  ```

  - 검색 범위의 시작점과 종료점을 이용하여 검색을 반복 수행
  - 이진 검색의 경우, 자료의 삽입이나 삭제가 발생할 때 배열의 상태를 항상 정렬 상태로 유지하는 추가 작업이 필요

