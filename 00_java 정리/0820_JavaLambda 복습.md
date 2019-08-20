## Java Lambda 복습 (08/20)

- java 8 이상에서 지원
- 람다식은 익명함수 (이름 없는 함수) 를 만드는 표현식
- 자바는 함수라는 개념이 존재하지 않음
- 객체 안에 method 개념으로 기존에 다른 언어에서의 함수를 사용해야 함
- 자바에서 람다를 도입한 이유
  - 코드의 간결성
  - Stream과의 연동
- 람다의 표현식 : (매개변수1, 매개변수2, ...)->{ 실행 코드 }
  - 실제로는 자바의 익명 class의 instance를 생성
- 람다의 타겟 타입 : 람다식이 대입되는 interface를 지칭

- 람다의 타겟타입이 되려면 인터페이스여야 함
  - 해당 인터페이스 안에 추상 메소드가 반드시 1개만 존재
  - 컴파일 단계에서 특정 interface가 람다의 타겟 타입인지를 확인하는 annotation : @FunctionalInterface
- 람다의 표현식이 case by case
- 람다식 안에서 변수 사용
  - member 사용하는 방식과 local variable
  - this keyword의 의미도 다름

<br>

--------------------------------------------------

- 람다식은 특정 interface type의 변수에 저장이 되는 형태로 사용 됨

- 이런 특정 interface는 직접 만들어 사용할까?

  - 직접 만들 수도 있지만 일반적인 형태는 아님

  - 자바에서는 java.util.function이라는 package를 제공

    이 안에 람다식의 target type으로 활용이 가능한

    interface를 넣어서 제공

  - 5가지 종류 제공

    - Consumer : 인자를 사용하고 리턴이 없는

      ​					  추상 메소드 accept()를 가짐

      들어가는 값만 존재하고 나오는 값이 없는 소모되는 성질

    - Supplier : 사용되는 인자가 없고 리턴이 있는

      ​				  추상 메소드 getXXX() 을 가짐

    - Function

    - Operator

    - Predicate

    <br>

  - Runnable a = () -> {};

    Runnable은 함수적 인터페이스 형태의 interface

    Runnable이 람다식의 target type