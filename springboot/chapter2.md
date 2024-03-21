application.properties

Spring Data JPA가 개발에 필요한 것은 단 두 종류의 코드만으로 가능하다.
* jpa를 통해서 관리하게 되는 객체를 위한 엔티티 클래스
* 엔티티 객체들을 처리하는 기능을 가진 Repository
Repository는 spring data jpa에서 저공하는 인터페이스로 설계하는데 스프링 내부에서
자동으로 객체를 생성하고 실행하는 구조라 개발자 입장에서는 단순히 인터페이스를 하나 정의하는
작업만으로도 충분하다.

jpa는 자동으로 생성되는 코드를 이용하므로 단순 crud나 페이지 처리등의 개발에 코드를 개발하지 않아도 된다.

jparepository 인터페이스

jpa에는 여러 종류의 인터페이스의 기능을 통해서 jpa관련 작업을 별도의 코드 없이 처리할 수 있게 지원합니다. 예를 들어 CRUD 작업이나 페이징, 정렬 등의 처리도 인터페이스의 메서드를 호출하는 혀애로 처리하는데 기능에 따라서 상속 구조로 추가적인 기능을 제공합니다.

JpaRepository는 인터페이스이고, Spring Data JPA는 이를 상속하는 인터페이스를 선언하는 것만으로도 모든 처리가 끝난다.

프로젝트 내에 REPOSITORY 패키지를 생성하고 repository 클래스를 만들고 jparepository를 상속 받는다.

insert : save(엔터티 객체)
select : findBId(키 타입), getOne(키 타입)
update : save(엔터티 객체)
delete : deleteById(키 타입), delete(엔터티 객체)

insert, update 작업에 사용하는 메서도는 동일하게 save()를 이용하는데
이는 jpa의 구현체가 메모리상(entity manager라는 존재가 엔티티들을 관리하는 방식)
에서 객체를 비교하고 없다면 insert, 있다면 uodate를 동작시키는 방식으로 동작하기 때문이다.

