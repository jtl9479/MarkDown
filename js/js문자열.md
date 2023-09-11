# 문자열

## String as Objects

> JavaScript의 모든 것이 객체이다.
변수가 문자열 객체 인스턴스되면, 수많은 속성과 메소드가 사용할 수 있다.

### 문자열의 길이 찾기(length)
~~~
var browserType = "mozilla";
browserType.length;

결과는 7을 리턴한다.
~~~

### 특정 문자열 찾기 
대괄호 표기법을 이용해서 문자열 안의 문자를 구할 수 있다.
~~~
var browserType = "mozilla";
첫 번째 문자를 구할 수 있다.
browserType[0];
~~~

### 문자열 내부의 하위 문자열 찾기 indexOf()
~~~
var browserType = "mozilla";

browserType.indexOf("zilla");
결과는 2가 나온다. zilla문자열의 시작 위치를 반환한다.

browserType.indexOf("vanilla");
vanilla문자열을 찾지 못해 결과 -1를 반환한다.

이와 같이 사용할수 있다.
if (browserType.indexOf("mozilla") !== -1) {
  // do stuff with the string
}
~~~

### 문자열 시작과 끝 알기 slice()
~~~
var browserType = "mozilla";

첫 번째 파라메터는 추출을 시작할 문자 위치이고 두 번째 파라메터는 추출할 문자의 갯수이다.
browserType.slice(0, 3);
"moz"를 반환합니다 - 

 문자 뒤에 문자열의 나머지 문자를 모두 추출하려는 경우 두 번째 매개 변수를 포함하지 않고 문자열에서 나머지 문자를 추출할 위치의 문자 위치만 포함하면 된다.
browserType.slice(2);
"zilla"를 반환한다.
~~~

### 대/소문자 변경 toLowerCase(), toUpperCase()
~~~
var radData = "My NaMe Is MuD";
문자열을 소문자로 변경한다.
radData.toLowerCase();
"my name is mud"를 반환한다.

문자열을 대문자로 변경한다.
radData.toUpperCase();
"MY NAME IS MUD"를 반환한다.
~~~

### 문자열 일부 변경 replace()
> 문자열 내의 특정 문자열을 다른 문자열로 바꿀 수 있다.
~~~
var browserType = "mozilla";

browserType.replace("moz", "van");

"vanilla"을 반환한다.
~~~

# 배열

> 배열이란 일반적으로 "리스트같은 객체(list-like objects)"라고 기술된다. <br>
> 배열은 보통 리스트에 저장된 다수의 값들을 포함하고 있는 하나의 객체이다.

### 배열 만들기

> 배열은 대괄호로 구성되며 쉼표로 구분 된 항목들을 포함한다.

~~~
var shopping = ["bread", "milk", "cheese", "hummus", "noodles"];

shopping;
['bread', 'milk', 'cheese', 'hummus', 'noodles']
라고 노출된다.
~~~

위의 예제에서, 각 원소는 문자열이지만, 우리는 다양한 데이터 유형을 배열에 저장할 수 있습니다 (문자열, 숫자, 개체, 다른 변수, 심지어 다른 배열). 동일한 형태의 항목만 넣거나 (아래 sequence 처럼) 다양한 형태의 항목을 함께 넣을 수 (아래 random 처럼) 있습니다. 모두 숫자, 문자열 등일 필요는 없습니다.

~~~
var sequence = [1, 1, 2, 3, 5, 8, 13];

서로 다른 형태의 데이터타입을 넣을수 있다.
var random = ["tree", 795, [0, 1, 2]];
~~~

### 배열 항목의 접근과 수정

>  문자열의 문자에 접근했던 것과 같은 방법으로 괄호 표기법을 사용하여 배열의 개별 항목에 접근 할 수 있다.
~~~
var shopping = ["bread", "milk", "cheese", "hummus", "noodles"];

shopping[0];
// returns "bread"
~~~

> 배열 내부의 배열을 다중배열(multidimensional array)이라고 합니다. 대괄호 두개를 함께 연결하여 다른 배열 안에있는 배열 내부의 항목에 접근 할 수 있다.
~~~
var random = ["tree", 795, [0, 1, 2]];

random[2][2];
// return 2
~~~

### 배열의 갯수 구하기
> length 속성을 사용해서, 문자열의 (문자의) 길이를 알아낸 것과 정확히 같은 방식으로 배열의 길이를 알아낼 수 있습니다. 

~~~
var sequence = [1, 1, 2, 3, 5, 8, 13];

sequence.length;
// return 7

배열의 크기를 구해 
var sequence = [1, 1, 2, 3, 5, 8, 13];
for (var i = 0; i < sequence.length; i++) {
  console.log(sequence[i]);
}

다음과 같이 for문으로 사용할 수도 있다.
~~~

### 문자열을 배열로, 배열을 문자열로 변환하기

> 프로그램을 만들다보면 종종 긴 문자열로 이루어진 원시 데이터를 제공받게 될 것이고, 원시 데이터를 정제하여 더 유용한 데이터를 추출해 테이블 형태로 표시하는 등 작업을 수행해야 합니다. 이러한 작업을 위해 split() 메서드를 사용할 수 있습니다. split() 메서드는 사용자가 원하는 매개변수로 문자열을 분리하여 배열로 표현해줍니다.

~~~
var myData = "Manchester,London,Liverpool,Birmingham,Leeds,Carlisle";

var myArray = myData.split(",");
myArray;

['Manchester', 'London', 'Liverpool', 'Birmingham', 'Leeds', 'Carlisle']
값으로 리턴된다.
~~~

> join() 메서드를 사용하여 배열을 다시 문자열로 만들 수 있다.
~~~
var myNewString = myArray.join(",");
myNewString;

'Manchester,London,Liverpool,Birmingham,Leeds,Carlisle'
값으로 리턴된다.
~~~
> join뿐 아니라 toString() 메소드를 사용할 수도 있다.
> toString() 은 join() 과 달리 매개변수가 필요없어서 더 간단하지만, 더 많은 제한이 있습니다. join() 을 사용하면 다른 구분자를 지정할 수 있지만, toString() 은 항상 콤마를 사용합니다. 
~~~
var dogNames = ["Rocket", "Flash", "Bella", "Slugger"];
dogNames.toString(); //Rocket,Flash,Bella,Slugger
~~~

### 배열에 원소를 추가하고 제거하기
~~~
var myArray = [
  "Manchester",
  "London",
  "Liverpool",
  "Birmingham",
  "Leeds",
  "Carlisle",
];
~~~

~~~
push()을 사용하여 배열을 추가할 수 있다.
myArray.push("Cardiff");
myArray;
['Manchester', 'London', 'Liverpool', 'Birmingham', 'Leeds', 'Carlisle', 'Cardiff']
myArray.push("Bradford", "Brighton");
myArray;
['Manchester', 'London', 'Liverpool', 'Birmingham', 'Leeds', 'Carlisle', 'Cardiff', 'Bradford', 'Brighton']

값을 리턴한다.
~~~

~~~
pop()을 사용하여 마지막 원소를 제거할 수 있다.
myArray.pop();
['Manchester', 'London', 'Liverpool', 'Birmingham', 'Leeds', 'Carlisle', 'Cardiff', 'Bradford', 'Brighton']
기존의 9개에서 마지막 배열 값 "Brighton" 제거 되었다.
['Manchester', 'London', 'Liverpool', 'Birmingham', 'Leeds', 'Carlisle', 'Cardiff', 'Bradford']
~~~

unshift() 와shift()는 push() 와 pop()과 완전히 동일하게 동작합니다. 다만, 배열의 맨 끝이 아닌 제일 처음 부분의 원소를 추가하거나 제거합니다.

~~~
myArray.unshift("Edinburgh");
myArray;

['Edinburgh', 'Manchester', 'London', 'Liverpool', 'Birmingham', 'Leeds', 'Carlisle', 'Cardiff', 'Bradford']

myArray.shift();
['Manchester', 'London', 'Liverpool', 'Birmingham', 'Leeds', 'Carlisle', 'Cardiff', 'Bradford']


~~~




