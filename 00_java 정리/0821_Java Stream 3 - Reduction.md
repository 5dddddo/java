# Stream : Reduction (08/21)

> 전체 소스코드 [Ex03_StreamPipeline](https://github.com/5dddddo/java/blob/master/0821_Java_SE_programming - Stream/Ex03_StreamPipeline.java) 참고

<br>

 * 대량의 데이터를 가공해서 축소하는 개념

   ex) sum, average, count, max, min

   - 위의 method들은 OptionalDouble type으로 return하므로

     getAsDouble()로 실수값으로 변환해주어야 함

 * Collection을 사용할 때 Stream을 이용해서

   reduction 작업을 쉽게 할 수 있음

* Stream은 pipeline을 지원 ( . 으로 stream을 연결해서 사용할 수 있음)

<br>

 * 만약 Collection안에 reduction하기 쉽지 않은 형태로

   데이터가 들어가 있으면 **중간처리** 과정을 거쳐서

   reduction 하기 좋은 형태로 변환

- **최종 처리** 함수는 한 개의 pipeline 내에 한 개만 가능

<br>

- compiler는 **lazy 처리**(효율성을 위해)를 하기 때문에

  reduction하는 최종 처리 과정 ( average )이 있는지

  먼저 확인하고 최종 처리가 있는 것을 확인하면

  위의 중간 처리 작업( filter 작업 )을 진행함

  <br>

 * 예제를 통해 알아보자



<br>

### 추출

#### Stream 객체의 filter() : 중간 처리

- stream 원소 중 predicate의 조건을 만족(True)하는 원소만 남김
- 결과값을 Stream 객체로 생성해서 리턴

<br>

### 평균

#### average() : 최종 처리

- compiler는 **lazy 처리**(효율성을 위해)를 하기 때문에

  reduction하는 최종 처리 과정 ( average )이 있는지

  먼저 확인하고 최종 처리가 있는 것을 확인하면

  위의 중간 처리 작업( filter 작업 )을 진행함

<br>

``` java
// 부서가 IT인 사람들 중 남자에 대한 연봉 평균을 구하는 예제
// Exam03_Employee class 직원 객체를 생성해서
// ArrayList 안에 여러 명의 직원을 저장

public static void main(String[] args) {
    Stream <Exam03_Employee> stream = employees.stream();
    // stream의 중간 처리와 최종 처리를 이용해서 원하는 작업하기
    // filter () : stream 원소 중
    // 			   predicate의 조건을 만족(True)하는 원소만 남김
    // 		   	   결과값을 가지고 있는 Stream을 생성해서 리턴
    // pipeline으로 처리
    // 중간처리
    double avg = stream.filter(t-> t.getDept().equals("IT"))
        .filter(t-> t.getGender().equals("남자"))
        .mapToInt(t->t.getSalary())
        // 최종처리 : average()
        .average().getAsDouble();
    System.out.println("IT 부서의 남자 평균 연봉 : " + avg);
}
```

<br>

```java
// 나이가 35살 이상인 직원 중 남자 직원의 이름을 출력하는 예제 
stream
	// 중간처리 과정
    .filter(t-> (t.getAge() >= 35))
    .filter(t-> t.getGender().equals("남자"))
    // 최종 처리 과정
    .forEach(t->System.out.println(t.getName()));
```
<br>

### 중복제거

####  Stream 객체의 distinct() : 중간처리

- int 값 중복제거

``` java
int tmp[] = {10,20,30,40,50,30,40,50};
IntStream s = Arrays.stream(tmp);
s.distinct().forEach(t->System.out.println(t));
/* 출력 :
    홍길동
    오길동
    이길동
    서길동
    황길동
    정길동
    김길동
    홍길동
*/
```

<br>

- 객체에 대한 중복제거

``` java
employees.stream()
    .distinct()
    .forEach(t->System.out.println(t.getName()));
```

<br>

- 사람이 생각하는 것처럼

  내용이 같은 객체에 대한 중복제거

  - Exam03_Employee class의 equals() overriding

``` java
@Override
public boolean equals(Object obj) {
    // 만약 override 하지 않으면
    // 메모리 주소 가지고 비교

    // 내가 원하는 방식으로 overriding을 해서
    // 특정 조건을 만족하면 같은 객체로 판단하도록 해보자
    boolean result = false;
    Exam03_Employee target = (Exam03_Employee)obj;
    if(this.getName().equals(target.getName()))
        result = true;
    else 
        result = false;
    return result;
}

/* 출력 :
    홍길동
    오길동
    이길동
    서길동
    황길동
    정길동
    김길동
*/
```

<br>

### 정렬

####  Stream 객체의 sorted() : 중간처리

- sorted() method를 사용하려면

  비교가 가능한 객체로 넘겨줘야 함

- 정렬할 Class에 Comparable\<T\> interface의

  compareTo() overriding 구현 필수!

```java
// 부서가 IT인 사람을 연봉순으로 출력하는 예제
class Exam03_Employee implements Comparable<Exam03_Employee>
{
...
@Override
	public int compareTo(Exam03_Employee o) {
		// 정수값을 return
		// 양수가 리턴되면 순서를 바꾸고
		// 0이나 음수가 리턴되면 순서를 바꾸지 않음

		// 오름차순 정렬
		int result = 0;
		if (this.salary > o.salary)
			result = -1;
		else if (this.salary == o.salary)
			result = 0;
		else
			result = 1;
		return result;
	}
}
```

<br>

``` java
employees.stream().filter(t -> t.getDept().equals("IT"))
    // sorted() : 정렬 가능한 형태로 넘겨줘야 함
    // VO에서 Comparable interface를 구현해 주어야 함
    // Comparator.reverseOrder()의 인자가 없으면 오름차순 정렬
    // 인자가 있으면 내림차순 정렬
    .sorted(Comparator.reverseOrder()).
    forEach(t -> System.out.println(t.getName() + " " + t.getSalary()));
```

<br>

### 반복

#### 1. Stream 객체의 forEach() : 최종 처리

-  forEach()를 이용하면 스트림 안의 요소를 반복할 수 있음

- forEach()는 최종 처리 함수이므로 중간에 위치할 수 없음

  항상 맨 마지막에 위치해야 함

<br>

#### 2. Stream 객체의 peek() : 중간 처리

- forEach()와 똑같은 기능 수행

- 중간 처리 함수로 pipeline 중간에 위치 가능하지만

  최종 처리 함수가 나와야 실행 됨 => Compiler의 lazy 처리

```java
employees.stream()
		 .peek(t -> System.out.println(t.getName())
		 .mapToInt(t -> t.getSalary())
		 .forEach(t -> System.out.println(t));
```

<br>

### 확인

#### Stream 객체의 allMatch() / anyMatch() : 최종 처리

- 확인용 최종 처리 함수

- predicate를 이용해서 boolean으로 return 받음

- allMatch()

  anyMatch()

``` java
// 50살 이상인 사람만 추출해서 이름 출력
boolean result = employees.stream()
    .filter(t->(t.getAge() >=50))
    .allMatch(t->(t.getAge() > 55));
System.out.println(result);
```

<br>

### Collection 객체로 변환

#### Stream 객체의 collect()

- 다른 collection 객체로 변환하기
- Set, Map, List 등으로 변환 가능

<br>

- List 형태로 변환

``` java
// 최종 확인용 함수로 forEach를 많이 사용했는데
// forEach 말고 collect() 이용하기
// 나이가 50살 이상인 사람들의 연봉을 구해서
// List<Integer> 형태의 ArrayList에 저장해보자

List<Integer> res = employees.stream()
    .filter(t->(t.getAge()>=50))
    .map(t->t.getSalary())
    .collect(Collectors.toList());
System.out.println(res);
```

<br>

- Set 형태로 변환

```java
Set<Integer> res = employees.stream()
    .filter(t->(t.getAge()>=50))
    .map(t->t.getSalary())
    .collect(Collectors.toCollection(HashSet::new));
```

