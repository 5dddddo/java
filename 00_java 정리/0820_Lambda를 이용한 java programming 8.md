# Functional Interface : Predicate

> 전체 소스코드 [Ex08_LambdaUsingPredicate](https://github.com/5dddddo/java/blob/master/0820_Java_SE_programming - Lambda/Ex08_LambdaUsingPredicate.java) 참고

<br>

 * Predicate는 입력 매개변수가 있고

    boolean type으로 리턴함

 * 추상 메소드 testXXX()를 사용함

 * 입력 매개변수를 조사하여 true, false를 리턴해야 하는 경우

 * Function의 부분집합 느낌

<br>

#### 성별에 따른 특정 과목의 평균을 구할 수 있도록 method 작성

- 학생 객체를 만들어서 List로 유지

  static method를 만들어서 람다식으로 인자를 넘겨줌

``` java
public class Ex08_LambdaUsingPredicate {

	private static List<Exam08_StudentVO> students =
        Arrays.asList(new Exam08_StudentVO("홍길동", 10, 20, 30, "남자"),
                      new Exam08_StudentVO("박길동", 20, 90, 60, "남자"),
                      new Exam08_StudentVO("신사임당", 30, 30, 90, "여자"),
                      new Exam08_StudentVO("유관순", 80, 80, 100, "여자"),
                      new Exam08_StudentVO("이순신", 30, 10, 10, "남자"));
    
	private static double getAvg(Predicate<Exam08_StudentVO> predicate,
                                 ToIntFunction<Exam08_StudentVO> func) {
		int sum = 0;
		int count = 0;
		for (Exam08_StudentVO student : students) {
			if (predicate.test(student)) {
				sum += func.applyAsInt(student);
				count++;
			}
		}
		return (double) sum / count;
	}

	public static void main(String[] args) {
		System.out.println("남자 수학 성적 평균: " +
                           getAvg(t -> t.getGender().equals("남자"), t -> t.getMath()));
		System.out.println("여자 영어 성적 평균: " +
                           getAvg(t -> t.getGender().equals("여자"), t -> t.getEng()));
	}
}
```

