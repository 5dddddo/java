# Functional Interface : Function

> 전체 소스코드 [Ex06_LambdaUsingFunction](https://github.com/5dddddo/java/blob/master/0820_Java_SE_programming/Ex06_LambdaUsingFunction.java) 참고

<BR>

 * Function 함수적 인터페이스는 

   입력 매개변수와 리턴값이 있는

   추상 메소드 applyXXX()이 제공 됨

 * 일반적으로, 입력 매개변수와 리턴값의 타입이 다른 경우

   두 변수를 Mapping 시킬 때 사용 됨

 * Function <T,R> func = t->{ return ... };
    * T : 입력 매개 변수의 generic
    * R : 리턴값의 generic

<br>

#### 학생의 이름을 출력하는 예제

-  List<Ex06_Student> student 정의

``` java
private static List<Ex06_Student> student 
= Arrays.asList(new Ex06_Student("홍길동", 50, 54, 13),
                new Ex06_Student("김길동", 30, 41, 80),
                new Ex06_Student("이순신", 90, 94, 93),
                new Ex06_Student("강감찬", 10, 100, 73));
```

- printName() 정의

```java
private static void printName(Function<Ex06_Student, String> function) {
    for (Ex06_Student s : student) {
        System.out.println(function.apply(s));
    }
}
public static void main(String[] args) {
    // 학생 이름을 출력
    printName(t -> {
        return t.getsName();
    });
}
```

<br>

#### 평균 성적을 출력하는 예제

- 일반적인 getAvg() 정의

``` java
private static double getAvg(String subject) {
    int sum = 0;
    if (subject.equals("KOR")) {
        // 국어평균
        for (Ex06_Student s : student)
            sum += s.getKor();
    }
    if (subject.equals("ENG")) {
        // 영어평균
        for (Ex06_Student s : student)
            sum += s.getEng();
    }
    if (subject.equals("MATH")) {
        // 수학평균
        for (Ex06_Student s : student) 
            sum += s.getMath();
    }
    return (double) sum / student.size();
}

public static void main(String[] args) {
    // getAvg라는 static method를 만들어서 다음의 내용을 출력
    // 학생들의 국어 성적 평균
    System.out.println("국어 평균 : " + getAvg("KOR"));
    // 학생들의 영어 성적 평균
    System.out.println("영어 평균 : " + getAvg("ENG"));
    // 학생들의 수학 성적 평균
    System.out.println("수학 평균 : " + getAvg("MATH"));
}
```

<BR>

- ToIntFunction을 통해 getAvg() 정의

``` java
private static double getAvg(ToIntFunction<Ex06_Student> function) {
    for (Ex06_Student s : student) {
        sum += function.applyAsInt(s);
    }
    return (double) sum / student.size();
}
public static void main(String[] args) {
    // getAvg라는 static method를 만들어서 다음의 내용을 출력
    // 학생들의 국어 성적 평균
    System.out.println("국어 평균 : " + getAvg(t -> t.getKor()));
    // 학생들의 영어 성적 평균
    System.out.println("영어 평균 : " + getAvg(t -> t.getEng()));
    // 학생들의 수학 성적 평균
    System.out.println("수학 평균 : " + getAvg(t -> t.getMath()));

}

```

