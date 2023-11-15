# Chapter5 스프링 MVC의 기본구조

## 5.1 스프링 mvc프로젝트의 내부 구조

    스프링 mvc프로젝트를 구성해서 사용한다는 의미는 내부적으로 
    root-context.xml로 사용하는 일반 java영역과
    servlet-context.xml로 설정하는 Web 관련 영역을 같이 연동해서 구동하게 된다.

## 5.2 예제 프로젝트의 로딩 구조

    프로젝트 구동 시 관여하는 xml은 web.xml, root-context.xml(java), servlet-context.xml(servlet)파일이 있다.
    이중 web.xml은 스프링 관련 설정이고 나머지는 스프링과 관련된 설정이다.

    프로잭트의 구동은 web.xml에서 시작한다.
    web.xml 상단에 있는 Context Listener가 가장먼저 구동된다.

```xml

	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
```



### 5.3 스프링 mvc의 기본 사상


### 5.4 모델 2와 스프링 mvc

    모델 2방식은 쉽게 말해서 '로직과 화면을 분리' 하는 스타일의 개발 방식이다.
    
    모델 2방식에서 사용자의 Request는 특별한 상황이 아니면 먼저 Controller를 호출한다.
    이렇게 설계하는 가장 중요한 이유는 나중에 view를 교체하더라도 사용자가 호출하는 url자체에 변화가 없게 만들어 주기 떄문이다.


## 스프링 mvc의 기본 구조

1. 사용자의 request는 front-Controller인 DispatchcerServlet을 통해 처리한다.
web.xml을 보면 모든 request를 dispatcherServlet이 받도록 처리하고 있다.