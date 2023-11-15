# JavaScript

> https://developer.mozilla.org/ko/docs/Web/JavaScript


JavaScript는 동적으로 변경되는 콘텐츠를 만들고, 멀티미디어를 제어하고, 이미지에 애니메이션을 적용하는 등 거의 모든 작업을 수행할 수 있는 스크립팅 언어입니다.
<br>

JS는 <a href="add.md#a"> 인터프리티 </a> 혹은 just-in-time 컴파일 프로그래밍 언어로 <a href="add.md#b"> 일급 함수 </a>를 지원한다.
<br>
<br>

JavaScript는 프로토타입 기반, 다중 패러다임, 단일 스레드, 동적 언어로, 객체지향형, 명령형, 선언형(함수형 프로그래밍 등) 스타일을 지원한다.

<br>
<br>

### 변수

> https://developer.mozilla.org/ko/docs/Learn/JavaScript/First_steps/Variables#the_difference_between_var_and_let

Variables는 여러분이 값을 저장할 수 있는 컨테이너입니다. 변수를 선언할 때 <a href="add.md#c"> var 또는 let 키워드 </a> 뒤에 원하는 어떤 이름을 붙이면 된다.
~~~
let myVariable;
~~~

변수를 선언한 후에, 값을 할당할 수 있다.
~~~
myVariable = "Bob";
~~~

 변수 선언과 값을 주는 작업을 한 줄로 처리할 수 도 있다.
 ~~~
let myVariable = "Bob";
 ~~~

이름으로 변수를 호출하기만 하면 값을 추출할 수 있습니다.
~~~
console.log(myVariable);
~~~

변수에 어떤 값을 준 후, 나중에 변경할 수도 있습니다.
```
let myVariable = 'Bob';
myVariable = 'Steve';
```

변수는 여러 <a href="add.md#d"> 자료형 </a>을 가질 수 있다.

<figure class="table-container"><table>
  <thead>
    <tr>
      <th>변수</th>
      <th>설명</th>
      <th>예시</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>String</td>
      <td>문자열로도 알려진 일련의 텍스트. 그 값이 문자열이라는 것을 나타내기 위해서는 인용부호로 둘러싸야 합니다.</td>
      <td><code>let myVariable = 'Bob';</code></td>
    </tr>
    <tr>
      <td>Number</td>
      <td>숫자. 숫자는 인용부호를 사용하지 않습니다.</td>
      <td><code>let myVariable = 10;</code></td>
    </tr>
    <tr>
      <td>Boolean</td>
      <td>참/거짓 값. <code>true</code>와 <code>false</code>라는 단어는 JS의 특별한 키워드이며, 인용부호가 필요 없습니다.</td>
      <td><code>let myVariable = true;</code></td>
    </tr>
    <tr>
      <td>Array</td>
      <td>여러 값을 하나의 단일 참조(single reference)에 저장할 수 있도록 해주는 구조</td>
      <td><code>let myVariable = [1,'Bob','Steve',10];</code> 해당 배열의 각 멤버는 다음과 같이 참조할 수 있습니다: <code>myVariable[0]</code>, <code>myVariable[1]</code>, etc.</td>
    </tr>
    <tr>
      <td>Object</td>
      <td>기본적으로, 무엇이든. 자바스크립트의 모든 것은 객체(object)이며 어떤 변수에 저장될 수 있습니다. 학습하는 동안 이 점을 기억하세요.</td>
      <td><code>let myVariable = document.querySelector('h1');</code> 위의 모든 예시도 마찬가지입니다.</td>
    </tr>
  </tbody>
</table></figure>

<br>
<br>

### 주석
CSS에서 했던 것처럼 자바스크립트 코드 안에 주석을 넣을 수 있다.

~~~
/*
사이에 있는 모든 것은 주석입니다.
*/
~~~

줄바꿈을 할 필요가 없는 주석이라면, 두 개의 슬래시 뒤에 주석을 놓는 방법도 있다.
~~~
// 이것은 주석입니다
~~~

<br>
<br>

### 연산자
operator는 두 값(또는 변수)로부터 결과를 만들어내는 수학 기호다.

<figure class="table-container"><table>
  <thead>
    <tr>
      <th>연산자</th>
      <th>설명</th>
      <th>기호</th>
      <th>예시</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>더하기</td>
      <td>두 수를 합치거나, 또는 두 문자열을 하나로 붙일 때 사용합니다.</td>
      <td><code>+</code></td>
      <td><code>6 + 9; "Hello " + "world!";</code></td>
    </tr>
    <tr>
      <td>빼기, 곱하기, 나누기</td>
      <td>예상하는 바와 같이 기초수학에서 하는 것과 동일하게 동작합니다.</td>
      <td><code>-</code>, <code>*</code>, <code>/</code></td>
      <td><code>9 - 3; 8 * 2; // JS 에서의 곱하기는 별표입니다 9 / 3;</code></td>
    </tr>
    <tr>
      <td>할당</td>
      <td>이것에 대해서는 이미 보았습니다: 값을 어떤 변수에 할당합니다.</td>
      <td><code>=</code></td>
      <td><code>let myVariable = 'Bob';</code></td>
    </tr>
    <tr>
      <td>동등</td>
      <td>두 값이 서로 같은지 테스트하여 <code>true</code>/<code>false</code> (불리언) 결과를 반환합니다.</td>
      <td><code>===</code></td>
      <td><code>let myVariable = 3; myVariable === 4;</code></td>
    </tr>
    <tr>
      <td>부정, 다름</td>
      <td>연산자 뒤쪽의 값에 대해 논리적으로 반대인 값을 반환합니다;<code>true</code>를 <code>false</code>로 바꾸는 등. 동등 연산자와 함께 사용할 경우 부정 연산자는 두 값이 같지 않은지 여부를 테스트합니다.</td>
      <td><code>!</code>, <code>!==</code></td>
      <td>기본 표현은 <code>true</code>이지만 비교는 <code>false</code>를 반환합니다 왜냐하면 우리가 이것을 부정했기 때문입니다:<code>let myVariable = 3; !(myVariable === 3);</code>여기서 테스트하고 있는 것은 "<code>myVariable</code>이 3 과 같지 않은가"입니다. 이것은 <code>false</code>를 반환하는데, <code>myVariable</code>이 3 과 같기 때문입니다.<code>let myVariable = 3;</code> <code>myVariable !== 3;</code></td>
    </tr>
  </tbody>
</table></figure>

<br>
<br>

### 조건문

조건문은 어떤 표현식(expression)이 참인지 아닌지를 테스트하고 그 결과에 따라 선택적으로 코드를 실행할 수 있도록 하는 코드 구조입니다.
~~~
let iceCream = "chocolate";
if (iceCream === "chocolate") {
  alert("Yay, I love chocolate ice cream!");
} else {
  alert("Awwww, but chocolate is my favorite...");
}
~~~

<br>
<br>

### 함수

> https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Grammar_and_types#variable_scope

Functions는 재사용하기를 원하는 기능을 담는 방법입니다. 그 절차(재사용 기능)가 필요할 때 매번 전체 코드를 다시 작성하는 대신 함수의 이름으로 그 함수를 호출할 수 있습니다.

~~~
let myVariable = document.querySelector("h1");

alert("hello!");
~~~
이 함수들, document.querySelector와 alert는 언제든지 사용할 수 있게 브라우저에 내장되어 있습니다.

변수 이름처럼 보이지만 그 뒤에 괄호 — () — 가 있다면 그것은 함수일 것입니다. 함수는 보통 인수(arguments) — 함수가 작동하는데 필요한 일련의 데이터 — 를 가집니다. 인수는 괄호 안으로 들어가고, 하나 이상의 인수가 있다면 콤마로 구분됩니다.

예를 들면, alert() 함수는 브라우저 창에서 팝업창을 만들지만, 그 함수가 그 팝업창에 표시할 문자열을 인자로 주어야 합니다.

<br>
<br>

### 변수 호이스팅

또 다른 JavaScript 변수의 특이한 점은 예외를 받지 않고도, 나중에 선언된 변수를 참조할 수 있다는 것입니다.

<br>
<br>

### 이벤트
웹사이트의 실질적인 상호작용에는 이벤트가 필요합니다. 이벤트는 브라우저에서 발생하는 일을 듣고 그에 대한 반응으로 코드를 실행하는 코드 구조입니다. 가장 확실한 예는 마우스로 무언가를 클릭하면 브라우저가 발생시키는 클릭 이벤트입니다.

```
 document.querySelector("html").onclick = function () {
  alert("Ouch! Stop poking me!");
};
```
```
document.querySelector("html").onclick = function () {};

let myHTML = document.querySelector("html");
myHTML.onclick = function () {};
같다.
```

### 오류의 종류

* 구문 오류: 코드에 잘못된 철자가 있으면 발생하는 오류로, 프로그램이 아예 구동하지 못하거나 중간에 멈춰버리는 현상을 일으키며, 모종의 오류 메시지도 나타납니다. 올바른 도구와, 메시지의 뜻만 파악하고 있으면 그럭저럭 고칠 만합니다!

* 논리 오류: 구문은 올바르지만 의도한 동작과 실제 코드에 차이가 있는 경우입니다. 따라서 프로그램은 성공적으로 돌아가지만 잘못된 결과를 낳습니다. 보통 오류를 직접 가리키는 메시지가 없기 때문에 구문 오류보다 고치기도 힘든 편입니다.



