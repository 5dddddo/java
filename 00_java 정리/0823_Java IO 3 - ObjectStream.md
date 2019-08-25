# Java IO 3 - ObjectStream

> 전체 소스코드 [Ex03_ObjectStream](https://github.com/5dddddo/java/blob/master/0823_Java_SE_programming%20-%20IO/Ex03_ObjectStream.java) 참고

<br>

- 객체 자체를 Object Stream을 통해 파일에 저장

- Object Stream은 메모리 내의 복잡한 내용을 쓰고 읽기에 아주 편리한 기능을 제공

- 일반적으로 네트워크를 통해 객체를 전송하고 받기 위해

  1. Marshaling : 데이터를 문자열 형태로 변환

  2. 변환된 데이터를 전송

  3. Unmarshaling  : 문자열 데이터를 읽어 들여 원래 형태로 변환

  하는 과정을 거침



#### Map 형태의 데이터를 Stream을 통해 파일에 저장하고

#### 저장된 내용을 읽어오는 예제

- Map 형태의 데이터 정의

``` java
Map<String, String> map = new HashMap<String, String>();

map.put("1", "홍길동");
map.put("2", "오길동");
map.put("3", "김길동");
map.put("4", "이길동");
```

<br>

- FileStream을 통해  ObjectStream 생성하고

  파일에 내용을 저장하고

  다시 내용을 읽어옴

```java
// 객체가 저장될 파일에 대한 File 객체 생성
// 해당 파일의 존재 여부는 상관 없음
File file = new File("asset/objectStream.txt");
// 객체가 저장될 File의 OutputStream부터 열기
try {
    // 파일이 존재하면 해당 파일의 통로만 열고
    // 파일이 없으면 파일을 생성하고 통로를 열어줌
    FileOutputStream fos = new FileOutputStream(file);
    ObjectOutputStream oos = new ObjectOutputStream(fos);

    oos.writeObject(map);

    // 1. 진짜 객체를 저장할 수 없기 때문에
    // 내보내려고 하는 map 객체를 마샬링 작업을 통해서 형태를 변환
    // 마샬링 : 객체를 문자열로 표현하기 위해서 하는 변화 작업

    // 저장하는 코드는 close를 제대로 해줘야 함
    // 역순으로 close
    oos.close();
    fos.close();

    // 객체가 저장된 파일을 open해서 해당 객체를 프로그램으로 읽어들임
    // 파일에서 데이터를 읽기 위해 InputStream이 필요
    FileInputStream fis = new FileInputStream(file);
    ObjectInputStream ois = new ObjectInputStream(fis);

    // 객체 읽어들임
    Object obj = ois.readObject();

    // 문자열로 표현된 객체를 읽어들여서 원래 객체로 복원
    HashMap<String, String> result = null;

    // generic type은 상관하지 않고 Map의 객체인지 확인
    if (obj instanceof Map<?, ?>) {
        // Map 형태의 객체이면 Map 형태로 Downcasting
        result = (HashMap<String, String>) obj;
    }
    // key값이 3인 요소 출력해서 확인해보기
    System.out.println(result.get("3"));
    
} catch (IOException e) {
    e.printStackTrace();
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}

```

