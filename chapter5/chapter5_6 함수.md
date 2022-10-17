
<H1>함수</H1>

<H2>단일행 함수</H2>

>**단일행 함수 특징**
>>1. SELECT, WHERE, ORDER BY 절에서 사용 가능하다.
>>2. 각 행들에 대해 개별적으로 작용하여 데이터 값들을 조작하고, 각각의 행에 대한 조작 결과를 리턴한다.
>> 3. 여러 인자를 입력해도 단 하나의 결과만 리턴한다.
>> 4. 함수의 인자로 상수, 변수, 표현식이 사용 가능하고, 하나의 인수를 가지는 경우도 있지만 여러 개의 인수를 가질 수도 있다.
>> 5. 함수의 인자로 함수 호출 자체를 사용하는 함수의 중첩도 가능하다.(함수 안에서 함수를 호출하는 것이 가능하다.)

단일행 함수의 종류<br>
&nbsp;&nbsp;&nbsp;&nbsp;단일행 함수의 종류에는 **문자형, 숫자형, 날짜형, 변환형, NULL 관련** 함수가 있다.<BR><BR>
단일행 함수의 종류<BR>
|종류 | 설명 | 주요 단일행 함수|
|:---|:----|:---|
|문자형 함수 | 문자를 입력하면 문자나 숫자 값을 반환한다.|LOWER, UPPER, SUBSTR, LENGTH, LTRIM, RTIME, TRIM, ASCII|
|숫자형 함수 | 숫자를 입력하면 숫자 값을 반환한다.|ABS, MOD, ROUND, TRUNC, SIGN, CHR, CEIL, FLOOR, EXP, LOG, LN, POWER, SIN, COS, TAN|
|날짜형 함수 | DATE 타입의 값을 연산한다.|SYSDATE, EXTRACT, TO_NUMBER|
|변환형 함수 | 문자, 숫자, 날짜형의 데이터형을 다른 데이터형으로 형변환한다.|TO_NUMBER, TO_CHAR, TO_DATE, CONVERT|
|NULL 관련 함수 | NULL을 처리하기 위한 함수이다.|NVL, NULLIF, COALESCE|

<BR>

단일행 문자형 함수 사용 예시
|SQL|결과|설명|
|:---|:---|:----|
|SELECT|||
|LOWER('SQL Developer')|sql developer|소문자로 변환한다.|
|UPPER('SQL Developer')|SQL DEVELOPER|대문자로 변환한다.|
|ASCII('A')|65|알파벳 'A'의 아스키 코드값을 반환한다.|
|CHR('65')|A|아스키 코드65의 문자를 반환한다.|
|CONCAT('SQL','Developer')|SQLDeveloper|2개의 문자열을 합친다.|
|SUBSTR('SQL Developer',1,3)|SQL|문자열의 첫 번째 문자부터 세 번째 문자까지만 출력한다.|
|LENGTH('SQL')|3|문자열의 길이를 구한다.|
|LTRIM('  SQL  ')|SQL  |왼쪽에 있는 공백을 제거한다.|
|LTRIM('  SQL  ')|  SQL|오른쪽에 있는 공백을 제거한다.|

<BR>

단일행 숫자형 함수 사용 예시
|SQL|결과|설명|
|:---|:---|:----|
|SELECT|||
|ABS(-15)|15|절대 값을 반환한다.|
|SING(1)|1|사인 값을 반환한다.|
|MOD(8,3)|2|8/2 나머지값을 반환한다.|
|CEIL(38.3)|39|무조건 올림한다.(자릿수x)|
|FLOOR(38.7)|38|무조건 내림한다.(자릿수x)|
|ROUND(38.678,2)|38.68|해당 자리수에서 반올림한다.|
|ROUND(38.678,1)|38.7|해당 자리수에서 반올림한다.|
|ROUND(38.678,0)|39|해당 자리수에서 반올림한다.|
|ROUND(38.678,-1)|40|해당 자리수에서 반올림한다.|
|TRUNC(38.678)|38|해당 자리수에서 자른다.|
|TRUNC(38.678, 1)|38.6|해당 자리수에서 자른다.|
|TRUNC(38.678, 2)|38.67|해당 자리수에서 자른다.|
|FROM DUAL;|||

<BR>

단일행 날짜형 함수 사용 예시
|SQL|결과|설명|
|:---|:---|:----|
|SELECT|||
|SYSDATE|2022-10-16 20:19:59.000|현재시간 출력|
|EXTRACT(YEAR FROM SYSDATE)|2022|년 출력|
|EXTRACT(MONTH FROM SYSDATE)|10|월 출력|
|EXTRACT(DAY FROM SYSDATE)|16|일 출력|
|TO_NUMBER(TO_CHAR(SYSDATE,'YYYY'))|2022|년 출력(숫자형)| 
|TO_CHAR(SYSDATE,'YYYY')|2022|년 출력(문자형)| 

<BR>

날짜형 데이터 연산
|SQL|결과|설명|
|:---|:---|:----|
|SELECT|||
|SYSDATE|2022-10-16 20:19:59.000|현재시간 출력|
|SYSDATE-1|2022-10-15 20:19:59.000|현재시간에서 하루 빼기|
|SYSDATE-(1/24)|2022-10-16 19:18:59.000|현재시간에서 1시간 빼기|
|SYSDATE-(1/24/60)|2022-10-16 20:17:59.000|현재시간에서 1분 빼기
|SYSDATE-(1/24/60/60)*10|2022-10-16 20:19:49.000|현재시간에서 10초 빼기|

<BR>

<H2>데이터 형변환의 방식</H2>
**데이터 형변환의 방식**

|종류|설명|
|----|---|
|명시적 형변환|데이터 형변환 함수로 데이터형을 변환하도록 명시해 주는 경우(수동)|
|암시적 형변환|DBMS가 자동으로 데이터형을 변환하는 경우(수동)|

<u>명시적 형변환은 TO_CHAR, TO_NUMBER, TO_DATE등의 함수를 이용해서 명시적으로 데이터형을 변환하는 것</u>

<u>암시적 형변환은 DBMS가 자동으로 데이터형을 변환하는것</u>

<br>

날짜형 데이터 연산
|SQL|결과|설명|
|:---|:---|:----|
|SELECT|||
|TO_CHAR(SYSDATE,'YYYY/MM/DD')|2022/10/16|날짜형을 문자형으로 변환|
|TO_CHAR(SYSDATE,'YYYY/MM/DD HH24:MI:SS')|2022/10/16 23:34:28|날짜형을 문자형으로 변환|
|TO_CHAR(10.25,'$999,999,999,999')|$10.25|숫자형을 문자형으로 변환|

<br>

<h3>단일행 case 표현의 종류</h3>

특정 값에 대해서 조건에 따라 각기 다른 값을 리턴하도록 하는 것을 CASE표현이라고 합니다.
<br>

CASE 표현의 종류
|SQL|설명|
|:---|:---|
|**CASE WHEN** 조건 **THEN** 값 혹은 SQL문 **ELSE** 값 혹은 SQL문 **END**| 조건이 맞으면 THEN절을 수행하고 그렇지 않으면 ELSE절을 실행한다.|
|DECODE(조건1, 값1, 조건2, 값2, 디폴트 값)|조건1이 TRUE이면 값1을 가져오고, 그렇지 않고 조건2가 TRUE이면 값2를 가져고오, 그렇지 않으면 디폴트 값을 노출한다.|

<BR>

**CASE WHEN문을 이용한 CASE 표현**
		
```SQL
 SELECT 
 	CASE WHEN A.INDUTY_CL_SE_CD = 'ICS001'
 		 THEN '대'
 		 WHEN A.INDUTY_CL_SE_CD = 'ICS002'
 		 THEN '중'
 		 WHEN A.INDUTY_CL_SE_CD = 'ICS003'
 		 THEN '소'
 		 ELSE ''
 	END
   FROM TB_INDUTY_CL_SE A;
```
<br>

**DECODE문을 이용한 CASE 표현**
		
```SQL
  SELECT DECODE(A.INDUTY_CL_SE_CD
                ,'ICS001', '대'
				,'ICS002', '중'
				,'ICS003', '소'
				, '')
  FROM TB_INDUTY_CL_SE A;
```

<br>

<h3>NULL 연산 결과</h3>

<u>NULL이란 "어떠한 값도 가지고 있지 않다" 라는 뜻을 가진다</u><br>
NULL은 빈칸과 같은 개념으로 이용할수 없는, 할당되지 않은 적용 불가능을 의미한다.<br>
NULL이 표함된 산술식의 결과는 언제나 NULL이 된다.
|연산|결과|
|---|---|
|NULL+2|NULL|
|NULL-2|NULL|
|NULL*2|NULL|
|NULL/2|NULL|

<h3>NULL 관련 함수 사용</h3>

NULL이 가지는 특수성으로 인해 적절한NULL처리는 SQL문 작성 시 매우중요하다<br>
그러므로 NULL 관련 함수를 사용하여 적절한 NULL값 처리가 필요하다.<br>

NULL처리 함수 사용 예시
|SQL|결과|설명|
|:---|:---|:---|
|SELECT|||
|NULLIF('SQLD','SQLP')|SQLD|두 문자열이 다르면 첫 번째 문자열 출력|
|NULLIF('SQLD','SQLD')|NULL|두 문자열이 같으면 NULL 출력|
|NVL(NULLIF('SQLD','SQLD'),'같음)|같음|두 문자열 비교후 결과값이 NULL인 경우 '같음' 출력|
|COALESCE(NULL,NULL,'SQLD')|SQLD|NULL이 아닌 첫번째 인자 출력|
|COALESCE(NULL,'SQLP','SQLD')|SQLP|NULL이 아닌 첫번째 인자 출력|
|COALESCE('SQL','SQLP','SQLD')|SQLP|SQL 아닌 첫번째 인자 출력|

<br>










