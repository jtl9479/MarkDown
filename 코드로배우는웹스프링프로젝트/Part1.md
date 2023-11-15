# Chapter1

## 개발을 위한 준비


## 스프링의 특징과 의존성 주입

### 2.1.1 스프링의 주요 특징

1. POJO 기반의 구성
    ->특정기술에 종속되지 않은 순수 자바 객체
2. 의존성 주입을 통한 객체 간의 관계 구성
3. AOP(Aspect-Oriented-Progamming) 지원
4. 편리한 MVC 구조
5. WAS의 종속적이지 않은 개발 환영


### 2.2.2 xml을 이용하는 의존성 주입 설정

    스프링은 클래스에서 객체를 생성하고 객체들의 의존성에 대한 처리 작업까지 내부에서 모든 것이 처리 됩니다.
    
    스프링에서 관리되는 객체를 흔히 '빈(bean)'이라 하고, 이에 대한 설정은 xml과java를 이용해서 처리할 수 있습니다.

    xml설정
    
web.xml
```xml
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>
```
    contextConfigLocation의 param-value경로에 지정되있는 곳이다.

    해당 경로에 context:component-scan을 설정한다.

```xml
	<context:component-scan base-package="org.zerock.sample"></context:component-scan>
```

    java 설정

```java
@ComponentScan(basePackages = {"org.zerock.sample"})
```


## 2.3 스프링이 동작하면서 생기는 일

1. 스프링 프레임워크가 시작되면 먼저 스프링이 사용하면 메모리 영역을 만들게 된다. 이를 컨텍스트라고 하며 스프링에서는 ApplicationContext객체가 생성된다.
2. 스프링은 객체를 생성하고 관리하는 객체들에 대한 설정이 필요하며 이에 대한 설정이 root-context.xml이다.(파일이름은 web.xml에서 변경이 가능)
3. root-context.xml에 설정되어 있는 <context:component-scan>태그의 base-package 경로에 있는 패키지를 스캔한다.
4. base-package 경로에 있는 클래스들 중에서 스프링이 사용하는 @Component라는 어노테이션이 존재하는 클래스의 인스턴스(빈)를 생성한다.

스프링프레임워크 동작 -> 필요한 객체들이 스프링에 등록(@Component) -> 의존성 주입이 필요한 객체는 자동으로 주입(@Autowired)이 되었다.



# Chapter 4 MyBatis와 스프링 연동

### 4.1.1 MyBatis 관련 라이브러리 추가

```xml
<!-- db관련 디펜던시 주입 -->		
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis</artifactId>
		    <version>3.4.6</version>
		</dependency>
        <!-- MyBatis는 SQL과 자바 객체 간의 매핑을 처리하는 데이터 매퍼 프레임워크, 객체-관계 매핑(Object-Relational Mapping, ORM)을 지원하며, SQL을 XML 파일이나 어노테이션을 사용하여 정의할 수 있다 -->
		
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis-spring</artifactId>
		    <version>1.3.2</version>
		</dependency>
        <!-- MyBatis-Spring은 MyBatis와 스프링 프레임워크를 통합하는 모듈입니다. 이 모듈은 MyBatis를 스프링 애플리케이션 컨텍스트에 통합하여 MyBatis의 세션을 스프링 트랜잭션과 함께 사용할 수 있게 해줍니다. 또한 MyBatis와 스프링의 트랜잭션 관리를 통합하여 트랜잭션 설정을 간소화합니다. -->
		
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-tx</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
        <!-- Spring Framework의 spring-tx 모듈은 선언적 트랜잭션 관리를 지원합니다. @Transactional 어노테이션 등을 사용하여 트랜잭션 경계 설정, 롤백 규칙, 격리 수준 등을 설정할 수 있습니다.  -->
		
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>

        <!-- Spring Framework의 spring-jdbc 모듈은 JDBC(Java Database Connectivity)를 간편하게 사용할 수 있도록 지원합니다. JdbcTemplate 등을 제공하여 JDBC 코드의 반복 작업을 최소화하고, 데이터베이스와의 상호 작용을 단순화합니다. 또한 DataSource 관리 및 예외 처리를 통합하여 개발자가 더 편리하게 데이터베이스와 상호 작용할 수 있도록 합니다.
        -->
```


### 4.1.2 SQLSessionFactory

    Mybatis에서 가장 핵심적인 객체는 SQLSession이라는 존재와 SQLSessionFactory이다.
    SQLSessionFactory는 내부적으로 SQLSession이라는 것을 만들어 내는 존재이며ㅡ 개발에서는 SQLSession을 통해서 Connection을 생성하거나 sql을 전달하고, 결과를 리턴 받는 구조로 작성한다.

    SqlSessionFactory는 MyBatis 프레임워크에서 데이터베이스와의 연결을 생성하는 팩토리입니다. 이 팩토리를 통해 SqlSession 객체를 얻어 데이터베이스와의 상호 작용을 수행할 수 있습니다. SqlSessionFactory는 프로젝트의 MyBatis 설정과 데이터베이스 연결 정보를 바탕으로 생성됩니다.

    일반적으로 SqlSessionFactory는 애플리케이션이나 스프링 컨테이너 등에서 한 번 생성되고 애플리케이션의 라이프사이클 동안 재사용됩니다. 이를 통해 데이터베이스와의 연결을 효율적으로 관리할 수 있습니다.


    SqlSession은 MyBatis 프레임워크에서 데이터베이스와의 상호 작용을 담당하는 주요 인터페이스 중 하나입니다. MyBatis는 SQL 매핑 파일(XML 파일이나 어노테이션)을 사용하여 Java 객체와 데이터베이스 간의 매핑을 정의하고, 이를 실행하기 위해 SqlSession을 제공합니다.

```java
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

// SqlSessionFactory를 사용하여 SqlSession을 생성
SqlSession sqlSession = sqlSessionFactory.openSession();

try {
    // SqlSession을 사용하여 SQL 실행
    MyObject result = sqlSession.selectOne("namespace.selectById", 1);

    // 트랜잭션 커밋
    sqlSession.commit();
} finally {
    // SqlSession 닫기
    sqlSession.close();
}
```

    SqlSessionFactory:
    SqlSessionFactory는 데이터베이스와의 연결을 생성하는 팩토리입니다.
    MyBatis 설정 파일 (mybatis-config.xml)과 SQL 매핑 파일(XML 또는 어노테이션)의 정보를 기반으로 생성됩니다.
    SqlSessionFactory는 애플리케이션의 라이프사이클 동안 한 번 생성되고 재사용됩니다.
    SqlSessionFactory를 통해 SqlSession을 생성할 수 있습니다.
    
    SqlSession:
    SqlSession은 데이터베이스와의 연결을 나타내며, 개발자가 데이터베이스와 상호 작용을 수행할 때 사용됩니다.
    SqlSession은 SQL을 실행하고, 트랜잭션을 관리하며, 데이터베이스 연결을 닫는 등의 작업을 수행합니다.
    개발자는 SqlSession을 사용하여 SQL을 실행하고 그 결과를 받을 수 있습니다.
    SqlSession은 MyBatis가 제공하는 다양한 메서드를 사용하여 데이터베이스와의 상호 작용을 처리합니다.
    SqlSession은 SqlSessionFactory를 통해 생성되며, 사용이 끝나면 close() 메서드를 호출하여 연결을 닫아야 합니다.


```xml
	<!-- 스프링에서 sqlsessionfactory등록하는 작업 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
```
    스프링에서 sqlsessionfactory를 등록하기 위해선 sqlsessionfactorybean을 이용한다. 
    패키지명을 보면 mybatis의 패키지가 아니라 스프링 연동 작업을 처리하는 mybatis=spring라이브러리의 클래스임이 확인이 가능하다




## 4.3 log4jdbc-log4j2 설정

    Mybatis는 내부적으로 JDBC의 ProparedStatement를 ㅣ용해서 sql을 처리한다.
    따라서sql에 전달되는 파라미터는 jdbc와 같이 ? 로 치환되어서 처리됩니다.
    복잡한 sql의 경우 ? 값이 제대로 나오는지 확인하기가 쉽지 않기 때문에
    PreparedStatement에 사용된 ?가 어떤 값으로 처리되었는지 확인하는 기능을
    log4jdbc-log4j2를 통하여 추가한다.

    설정하는 방법 [https://kimvampa.tistory.com/63]


    로그 [https://logging.apache.org/log4j/2.x/manual/customloglevels.html]
        [https://sharonprogress.tistory.com/198]
    







# 스프링 설정 

## context:component-scan

    빈으로 등록 될 준비를 마친 클래스들을 스캔하여, 빈으로 등록해주는 것이다.

```
component-scan 동작 과정
ConfigurationClassParser 가 Configuration 클래스를 파싱한다.
@Configuration 어노테이션 클래스를 파싱하는 것이다.
                   ⬇
ComponentScan 설정을 파싱한다.
base-package 에 설정한 패키지를 기준으로
ComponentScanAnnotationParser가 스캔하기 위한 설정을 파싱한다.
                   ⬇
base-package 설정을 바탕으로 모든 클래스를 로딩한다.
                   ⬇
ClassLoader가 로딩한 클래스들을 BeanDefinition으로 정의한다.
생성할 빈의 대한 정의를 하는 것이다.
                   ⬇
생성할 빈에 대한 정의를 토대로 빈을 생성한다.
```

[https://velog.io/@hyun-jii/%EC%8A%A4%ED%94%84%EB%A7%81-component-scan-%EA%B0%9C%EB%85%90-%EB%B0%8F-%EB%8F%99%EC%9E%91-%EA%B3%BC%EC%A0%95]



## <mybatis-spring:scan>

    <mybatis-spring:scan>은 MyBatis-Spring 프레임워크에서 사용되는 XML 네임스페이스입니다. 이를 통해 MyBatis-Spring이 매퍼 인터페이스를 스캔하고 자동으로 MyBatis의 구성에 추가할 수 있습니다.