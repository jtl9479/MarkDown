<h1 id="a">인터프리티 언어</h1>

~~~
참고 링크
https://ko.wikipedia.org/wiki/%EC%9D%B8%ED%84%B0%ED%94%84%EB%A6%AC%ED%84%B0
~~~

> 인터프리티 언어란 프로그래밍 언어의 소스 코드를 바로 실행하는 컴퓨터 프로그래밍을 말한다.

<br>
인터프리티 언어는 기계어 명령어들이 만들어지는 컴파일 단계를 거칠 필요가 없다. 컴파일 과정은 만약 원시 프로그램의 크기가 크다면, 상당한 시간이 걸릴 수 있다. 이와는 달리 인터프리터는 고급 프로그램을 즉시 실행시킬 수 있다. 

<br>
<br>

<h1 id="b">일급 함수</h1>

프로그래밍 언어는 해당 언어의 함수들이 다른 변수처럼 다루어질 때 일급 함수를 가진다고 합니다.
<br>
<br>
함수가 변수처럼 다뤄진다는 것은 아래와 같습니다.
 * 함수를 특정 변수에 할당할 수 있습니다.
 * 함수 호출의 인자로 함수를 넘길 수 있습니다.
 * 함수를 반환할 수 있습니다.

 ```
const a = () => {
  return "ABC";
}; // 변수에 함수를 할당

setTimeout(() => {
  console.log("HELLO");
}, 1000); // 함수의 인자로 함수를 넣음

const getPrintStringsFn = (str1) => {
  return (str2) => {
    console.log(str1 + str2);
  };
}; // 반환 값이 함수임

const helloToSomeoneFn = getPrintStringsFn("HELLO, ");
helloToSomeoneFn("Jake Seo"); // HELLO, Jake Seo
 ```

<br>
<br>
<br>

 <h1 id="c">var,let 차이</h1>

>https://www.freecodecamp.org/korean/news/var-let-constyi-caijeomeun/

Scope of var
<br>
범위는 기본적으로 변수를 사용할 수 있는 위치를 의미합니다. var 선언은 전역 범위 혹은 함수 범위로 지정됩니다.

var변수가 함수 외부에서 선언될 때의 범위는 전역입니다. 즉, 함수 블록 외부에서 var를 사용하여 선언된 모든 변수를 전체 윈도우 상에서 사용할 수 있는 것이죠.

var가 함수 내에서 선언될 때는 함수 범위로 지정됩니다. 즉, 해당 함수 내에서만 사용하고 접근할 수 있습니다.

Let

블록 범위 let
블록은 {}로 바인딩된 코드 청크인데요. 하나의 블록은 중괄호 속에서 존재하며, 중괄호 안에 있는 것은 모두 블록 범위입니다.

let으로 선언된 변수는 해당 블록 내에서만 사용가능하다.

let은 업데이트될 수 있지만, 재선언은 불가능하다.
var와 마찬가지로 let으로 선언된 변수는 해당 범위 내에서 업데이트될 수 있습니다. 하지만, var와 달리 let 변수는 범위 내에서 다시 선언할 수 없습니다. 아래의 기능이 작동하는 동안:


Const

const로 선언된 변수는 일정한 상수 값을 유지합니다. const 선언은 let 선언과 몇 가지 유사점을 공유합니다.

블록 범위 const
let 선언처럼 const 선언도 선언된 블록 범위 내에서만 접근 가능합니다.

const는 업데이트도, 재선언도 불가능하다
const로 선언된 변수의 값이 해당 범위 내에서 동일하게 유지됨을 의미합니다. 업데이트하거나 다시 선언할 수가 없는 것이죠. const로 변수를 선언한 경우에는 다음과 같은 두 작업을 수행할 수 없습니다:


 * var 선언은 전역 범위 또는 함수 범위이며, let과 const는 블록 범위이다.
 * var 변수는 범위 내에서 업데이트 및 재선언할 수 있다. let 변수는 업데이트할 수 있지만, 재선언은 할 수 없다. const 변수는 업데이트와 재선언 둘 다 불가능하다.
 * 세 가지 모두 최상위로 호이스팅된다. 하지만 var 변수만 undefined(정의되지 않음)으로 초기화되고 let과 const 변수는 초기화되지 않는다.
 * var와 let은 초기화하지 않은 상태에서 선언할 수 있지만, const는 선언 중에 초기화해야한다.

<br>
<br>
<br>

<h1 id="d">자료형</h1>

> https://developer.mozilla.org/ko/docs/Web/JavaScript/Data_structures#%EC%9B%90%EC%8B%9C_%EA%B0%92

JavaScript의 타입과 자료구조

### 동적 타입

JavaScript는 느슨한 타입(loosely typed)의 동적(dynamic) 언어입니다. JavaScript의 변수는 어떤 특정 타입과 연결되지 않으며, 모든 타입의 값으로 할당 (및 재할당) 가능합니다.

```
let foo = 42; // foo가 숫자
foo = "bar"; // foo가 이제 문자열
foo = true; // foo가 이제 불리언
```

### JavaScript의 타입

JavaScript 언어의 타입은 원시 값과 객체로 나뉩니다.

* 원시 값 (언어의 최고 로우레벨에서 직접 표현되는 불변 데이터)
    * Boolean 타입
    * Null 타입
    * Undefined 타입
    * Number 타입
    * BigInt 타입
    * String 타입
    * Symbol 타입
* 객체 (속성의 컬렉션)    

<br>
<br>
<br>

<h1 id="e">문서 객체 모델(DOM)</h1>

> https://developer.mozilla.org/ko/docs/Web/API/Document_Object_Model/Introduction

<br>

문서 객체 모델(The Document Object Model, 이하 DOM) 은 HTML, XML 문서의 프로그래밍 interface 이다. DOM은 문서의 구조화된 표현(structured representation)을 제공하며 프로그래밍 언어가 DOM 구조에 접근할 수 있는 방법을 제공하여 그들이 문서 구조, 스타일, 내용 등을 변경할 수 있게 돕는다. DOM 은 nodes와 objects로 문서를 표현한다. 이들은 웹 페이지를 스크립트 또는 프로그래밍 언어들에서 사용될 수 있게 연결시켜주는 역할을 담당한다.

### BOM 과 DOM의 차이

> https://yeji-power.tistory.com/m/36
> https://off-dngw.github.io/posts/DOMBOM%EC%B0%A8%EC%9D%B4/#dom%EA%B3%BC-bom-%EC%B0%A8%EC%9D%B4

DOM은 document, 웹 문서에 대한 제어와 변경을 하고 BOM은 window 속성에 속하여 document가 아닌 window를 제어를 합니다.