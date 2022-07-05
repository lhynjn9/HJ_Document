# Generic

- 다양한 타입의 객체를 다루는 메서드, 컬렉션 클래스에서 컴파일 시에 타입 체크
  - 미리 사용할 타입을 명시해서 형 변환을 하지 않아도 되게 함
  - 객체의 타입에 대한 안전성 향상 및 형 변환의 번거로움 감소

- <u>객체를 만들때 타입을 결정할 수 있는 방법</u>

- 타입에 대해서 키워드로 정했다가 객체를 생성할때, 그 클래스가 사용할 타입을 결정할 수 있는 방법

- 변수 쪽과 생성 쪽의 타입은 반드시 같아야 함

  ```java
  // 1.
  Class_Name<String> generic = new Class_Name<Sting>();
  
  // 2. T 부분이 String이 됨
  class GenericBox<T>{
      private T some;
      
      public T getSome(){
          return some;
      }
      
      public void setSome(T some){
          this.some = some;
      }
  }
  ```

- 표현

  - 클리스 또는 인터페이스 선언 시 `<>`에 타입 파라미터 표시

    - ClassName : Raw Type
    - ClassName<T> : Generic Type

  - 타입 파라미터 : 특별한 의미의 알파벳 보다는 단순히 임의의 참조형 타입을 말함

  - T : refernece Type

  - E : Element

  - K : Key

  - V : Value

    ![캡처](https://user-images.githubusercontent.com/97647987/176495978-1a19fb01-97eb-43d7-b0b8-d2f4815303f6.JPG)





- Type parameter의 제한

  - 필요에 따라 구체적인 타입 제한 필요

  - 계산기 프로그램 구현 시 Number 이하의 타입(Byte, Short, Integer..)로만 제한

  - type parameter 선언 뒤 extends와 함께 상위 타입 명시

    ```java
    class NumberBox<T extends Number>{
        // 보다 구체적인 내용 작성 가능
    }
    ```

  - 인터페이스로 제한할 경우도 extends 사용

  - 클래스와 함께 인터페이스 제약 조건을 이용할 경우 `&`로 연결

    ```java
    class TypeRestrict<T extends Cloneable>{}
    ```

    