# 1. 람다 표현식

## 람다 표현식이란

- 람다 표현식은 익명 클래스처럼 이름이 없는 함수이면서 메서드를 인수로 전달할 수 있으므로 일단은 람다 표현식이 익명 클래스와 비슷하다고 생각하자
- 메서드로 전달할 수 있는 익명 함수를 단순화한 것

```java
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
(람다 파라미터) -> 람다 바디 
```

## 함수형 인터페이스

- 오직 하나의 추상 메서드만 가지는 인터페이스
- 많은 디폴트 메서드가 있더라도 추상 메서드가 오직 하나면 함수형 인터페이스다

## 함수 디스크립터

- 함수형 인터페이스의 추상 메서드 시그니처는 람다 표현식의 시그니처를 가리킨다.
- 람다 표현식의 시그니처를 서술하는 메서드를 함수 디스크립터라고 부른다.
    - 예를 들어 Runnable 인터페이스의 유일한 추상 메서드 run은 인수와 반환 값이 없으므로 Runnable 인터페이스는 인수화 반환 값이 없는 시그니처로 생각할 수 있다.

## @FunctionalInterface

- 함수형 인터페이스임을 가리키는 어노테이션이다
- @FunctionalInterface를 선언했지만, 함수형 인터페이스가 아니면 컴파일러가 에러를 발생시킨다.
- 예를 들어, 추상 메서드가 한 개 이상이라면 "Multiple Nonoverriding abstract methods found in interface Foo" 같은 에러를 발생할 수 있다.

| 함수형 인터페이스           | 함수 디스크립터          | 기본형 특화                                                                                                                                                                                                                                                                             |
|---------------------|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Predicate<T>        | T -> boolean      | IntPredicate, <br/>LongPredicate, <br/>DoublePredicate                                                                                                                                                                                                                             |
| Consumer<T>         | T -> void         | IntConsumer,<br/>LongConsumer,<br/>DoubleConsumer                                                                                                                                                                                                                                  |
| Function<T, R>      | T -> R            | IntFuction<R>,<br/>IntToDoubleFunction,<br/>IntToLongFunction,<br/>LongFunction<R>,<br/>LongToDoubleFunction,<br/>LongToIntFuction,<br/>DoubleFunction<R>,<br/>DoubleToIntFunction,<br/>DoubleToLongFunction,<br/>ToIntFunction<T>,<br/>ToDoubleFunction<T>,<br/>ToLongFunction<T> |
| Supplier<T>         | () -> T           | BooleanSupplier,<br/>IntSupplier,<br/>LongSupplier,<br/>DoubleSupplier                                                                                                                                                                                                             |
| UnaryOperator<T>    | T -> T            | IntUnaryOperator,<br/>LongUnaryOperator,<br/>DoubleUnaryOperator                                                                                                                                                                                                                   |
| BinaryOperator<T>   | (T, T) -> T       | IntBinaryOperator,<br/>LongBinaryOperator,<br/>DoubleBinaryOperator                                                                                                                                                                                                                |
| BiPredicate<L, R>   | (T, U) -> boolean |                                                                                                                                                                                                                                                                                    |
| BiConsumer<T, U>    | (T, U) -> void    | ObjIntConsumer<T>,<br/>ObjLongConsumer<T>,<br/>ObjDoubleConsumer<T>                                                                                                                                                                                                                |
| BiFunction<T, U, R> | (T, U) -> R       | ToIntBiFunction<T, U>,<br/>ToLongBiFunction<T, U>,<br/>ToDoubleBiFunction<T, U>                                                                                                                                                                                                    |

| 사용 사례      | 람다 예제                                                            | 대응 함수형 인터페이스                                                           |
|------------|------------------------------------------------------------------|------------------------------------------------------------------------|
| 불리언 표현     | (List<String> list) -> list.isEmpty()                            | Predicate<List<String>>                                                |
| 객체 생성      | () -> new Apple(10)                                              | Supplier<Apple>                                                        |
| 객체에서 소비    | (Apple a) -> System.out.println(a.getWeight())                   | Consumer<Apple>                                                        |
| 객체에서 선택/추출 | (String s) -> s.length()                                         | Function<String, Integer> 또는<br/>ToIntFunction<String>                 |
| 두 값 조합     | (int a, int b) -> a * b                                          | IntBinaryOperator                                                      |
| 두 객체 비교    | (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) | BiFunction<Apple, Apple, Integer> 또는<br/>ToIntBiFunction<Apple, Apple> |

## 특별한 보이드 호환 규칙

```java
 Predicate<String> p = s -> listOfString.add("abc");
 
 Consumer<String> b = s -> listOfString.add("abc");
 
 // 원래는 아래와 같이 만들어야 하지만 리턴해야할 타입이 void일 경우에는 바로 위의 코드처럼 {}를 제외하고 사용할 수 있다.
 Consumer<String> b = s -> {
    listOfString.add("abc");
 }
```

## 형식 추론

```java
// 같은 코드, 형식을 추론하기 때문에 String 타입 명시 제외 가능
 forEach(listOfString, (String s) -> System.out.println(s));
 forEach(listOfString, s -> System.out.println(s));
```

## 지역 변수 사용

- 람다 표현식에서는 익명 함수가 하는 것처럼 자유 변수(람다의 파라미터로 넘겨진 변수가 아닌 외부에서 정의된 변수)를 활용할 수 있다. 이와 같은 동작을 람다 캡쳐링이라고 한다.

```java
int portNumber = 8080;  // <- 자유 변수
Runnable r = () -> System.out.println(portNumber);
```

- 자유 변수에는 제약이 있다. effecitvely final 이거나 final한 변수만 사용이 가능하다.
- 정확히는 primitive 타입이 effectively final 하지 않거나 final이 아니면 사용할 수 없다.
- 인스턴스 변수는 힙에 저장되고, 지역변수는 스택에 위치하기 때문에
- 람다에서 지역 변수에 바로 접근할 수 있다는 가정하에 람다가 스레드에서 실행된다면 변수를 할당한 스레드가 사라져서 변수 할당이 해제되었는데도 람다를 실행하는 스레드에서는 해당 변수에 접근하려 할 수 있다. 따라서
  람다에서는 지역 변수의 복사본을 제공한다.

## 메서드 참조

- 가독성 증가

| 람다                                                                        | 메서드 참조 단축 표현                              |
|---------------------------------------------------------------------------|-------------------------------------------|
| (Apple apple) -> apple.getWeight()                                        | Apple::getWeight                          |
| () -> Thread.currentThread().dumpStack()                                  | Thread.currentThread()::dumpStack         |
| (str, i) -> str.substring(i)                                              | String::substring                         |
| (String s) -> System.out.println(s)<br/>(String s) -> this.isValidName(s) | SYstem.out::println<br/>this::isValieName |

1. 정적 메서드 참조
    - Integer.parseInt() : Integer::parseInt
2. 다양한 형식의 인스턴스 메서드 참조
    - String::length
3. 기존 객체의 인스턴스 메서드 참조
    - expensiveTrasaction.getValue() : expensiveTransaction::getValue

```java
(String s) -> s.toUpperCase()
String::toUpperCase
```
![image](https://github.com/hwibaski/study-modern-java-in-action/assets/85930725/c634a1c2-5e9b-49fb-b694-9ed17808e6c8)


- 출처 : https://dev-kani.tistory.com/38

## 생성자 참조

```java
Supplier<Apple> a1 = Apple:new;

new Apple(color, weight)
BiFunction<Color, Integer, Apple> a2 = Apple::new;

// 파라미터가 세 개 이상인 생성자를 메서드 참조하려면 커스텀하게 함수형 인터페이스를 만들어서 사용해야 메서드 참조 사용 가능하다.
```

```java
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new; 
```



