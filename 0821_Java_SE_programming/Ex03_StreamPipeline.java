package javaStream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * reduction
 * 
 * => 대량의 데이터를 가공해서 축소하는 개념
 * => sum, average, count, max, min
 * 
 * Collection을 사용할 때 Stream을 이용해서 이런 reduction작업을
 * 쉽게 할 수 있어요!
 * 
 * 만약 Collection안에 reduction하기가 쉽지 않은 형태로 데이터가 들어가있으면
 * 중간처리과정을 거쳐서 reduction하기 좋은 형태로 변환.
 * 
 * Stream은 pipeline을 지원(stream을 연결해서 사용할 수 있어요)
 * 
 * 간단한 예제를 통해서 이해해 보아요!
 * 직원객체를 생성해서 ArrayList안에 여러명의 직원을 저장.
 * 이 직원중에 IT에 종사하고 남자인 직원을 추려서 해당 직원들의 연봉 평균을
 * 출력!
 * 
 * 
 */

public class Ex03_StreamPipeline {

	private static List<Exam03_Employee> employees = Arrays.asList(new Exam03_Employee("홍길동", 20, "IT", "남자", 2000),
			new Exam03_Employee("오길동", 30, "Sales", "여자", 3000), new Exam03_Employee("이길동", 40, "IT", "남자", 8000),
			new Exam03_Employee("서길동", 50, "IT", "남자", 7500), new Exam03_Employee("황길동", 60, "Sales", "여자", 6500),
			new Exam03_Employee("정길동", 70, "IT", "여자", 2020), new Exam03_Employee("김길동", 33, "IT", "여자", 5000),
			new Exam03_Employee("홍길동", 20, "IT", "남자", 2000));

	public static void main(String[] args) {
		// 부서가 IT인 사람들 중 남자에 대한 연봉 평균을 구하기
		Stream<Exam03_Employee> stream = employees.stream();
		// stream의 중간 처리와 최종 처리를 이용해서 원하는 작업하기
		// filter () : stream 원소 중 predicate의 조건을 만족(True)하는 원소만 남김
		// 결과값을 가지고 있는 Stream을 생성해서 리턴
		// pipeline으로 처리
//		double avg = stream
//				// 중간처리 과정
//				.filter(t-> t.getDept().equals("IT"))
//				.filter(t-> t.getGender().equals("남자"))
//				.mapToInt(t->t.getSalary())
//				// 최정 처리 : average()
//				.average().getAsDouble();
//		System.out.println("IT 부서의 남자 평균 연봉 : " + avg);

		// Stream이 가지고 있는 method는?
		// 나이가 35살 이상인 직원 중 남자 직원의 이름을 출력하기
		stream
				// 중간처리 과정
				.filter(t -> (t.getAge() >= 35)).filter(t -> t.getGender().equals("남자"))
				.forEach(t -> System.out.println(t.getName()));

		// distinct() : 중복제거를 위한 중간처리
		int tmp[] = { 10, 20, 30, 40, 50, 30, 40, 50 };
		IntStream s = Arrays.stream(tmp);
		s.distinct().forEach(t -> System.out.println(t));

		// 객체에 대한 중복제거
		employees.stream().distinct().forEach(t -> System.out.println(t.getName()));

		// VO 안에서 equals() overriding 을 통해
		// 내용이 같으면 같은 객체로 판단하고 중복제거

		// MapToInt => mapXXX()

		// 정렬 ( 부서가 IT인 사람을 연봉순으로 출력 )
		employees.stream().filter(t -> t.getDept().equals("IT"))
				// sorted() : 정렬 가능한 형태로 넘겨줘야 함
				// VO에서 Comparable interface를 구현해 주어야 함
				// Comparator.reverseOrder()의 인자가 없으면 오름차순 정렬
				// 인자가 있으면 내림차순 정렬
				.sorted(Comparator.reverseOrder()).forEach(t -> System.out.println(t.getName() + " " + t.getSalary()));

		// 반복
		// forEach()를 이용하면 스트림 안의 요소를 반복할 수 있음
		// forEach()는 최종 처리 함수이므로
		// 중간에 위치할 수 없음
		// 항상 맨 마지막에 위치해야 함
		
		// peek() : 중간처리
//		employees.stream()
//				 .peek(t -> System.out.println(t.getName())
//				 .mapToInt(t -> t.getSalary())
//				 .forEach(t -> System.out.println(t));

		// 확인용 최종 처리 함수 : predicate를 이용해서 boolean으로 return 받음
		// 50살 이상인 사람만 추출해서 이름 출력
//		boolean result = employees.stream()
//				 .filter(t->(t.getAge() >=50))
//				 .allMatch(t->(t.getAge() > 55));
//		System.out.println(result);
				
		
		// 최종 확인용 함수로 forEach를 많이 사용했는데
		// forEach 말고 collect() 이용하기
		// 나이가 50살 이상인 사람들의 연봉을 구해서
		// List<Integer> 형태의 ArrayList에 저장해보자
		
//		List<Integer> res = employees.stream()
//				 .filter(t->(t.getAge()>=50))
//				 .map(t->t.getSalary())
//				 .collect(Collectors.toList());
//		System.out.println(res);
		
		Set<Integer> res = employees.stream()
				 .filter(t->(t.getAge()>=50))
				 .map(t->t.getSalary())
				 .collect(Collectors.toCollection(HashSet::new));
		System.out.println(res);
	}

}

class Exam03_Employee implements Comparable<Exam03_Employee> {
	private String name; // 이름
	private int age; // 나이
	private String dept; // 부서
	private String gender; // 성별
	private int salary; // 연봉

	public Exam03_Employee() {

	}

	public Exam03_Employee(String name, int age, String dept, String gender, int salary) {
		super();
		this.name = name;
		this.age = age;
		this.dept = dept;
		this.gender = gender;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// 만약 override 하지 않으면
		// 메모리 주소 가지고 비교

		// 내가 원하는 방식으로 overriding을 해서
		// 특정 조건을 만족하면 같은 객체로 판단하도록 해보자
		boolean result = false;
		Exam03_Employee target = (Exam03_Employee) obj;
		if (this.getName().equals(target.getName()))
			result = true;
		else
			result = false;
		return result;
	}

	@Override
	public int compareTo(Exam03_Employee o) {
		// 정수값을 return
		// 양수가 리턴되면 순서를 바꾸고
		// 0이나 음수가 리턴되면 순서를 바꾸지 않음

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