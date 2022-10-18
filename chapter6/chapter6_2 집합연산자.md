<H1>집합연산자</H1>

<H2>집합연산자의종류</H2>
집합연산자는 SELECT문의 결과집합 간의 연산을 수행하는 연산자입니다.
집합연산자의 종류로는 <U>UNION, UNION ALL, INTERSECT, MUNUS</U>가 있다

<BR>

각각의 SQL문의 결과집합을 대상으로 연산을 수행하기 때문에 여러 개의 SQL문을 하나의SQL문으로 만드는 연산자라고 할수 있다.

<BR>

>집합연산자의 종류 및 설명
>>|집합연산자의 종류|설명|
>>|----------------|----|
>>|UNION| <UL> 여러 개 SQL문의 결과에 대한 합집합니다.</UL><UL>중복된 행은 1개의 행으로 출력된다.</UL><UL>중복된 행을 1개의 행으로 출력하는 과정에서 정렬이 발생할 수도 있다.</UL>|
>>|UNION ALL| <UL> 여러 개 SQL문의 결과에 대한 합집합니다.</UL><UL>중복된 행도 그래도 결과로 표시한다.</UL>|
>>|INTERSECT| <UL> 여러 개 SQL문의 결과에 대한 교집합이다.</UL><UL>중복된 행은 1개의 행으로 출력된다.</UL>|
>>|EXCEPT(MINUS)| <UL>위 SQL문의 집합에서 아래 SQL문의 집합을 뺸 결과를 표시한다.</UL>|

<BR>
<BR>

<H2>UNION 연산</H2>

UNION 연산은 여러 개의 SQL문에 대한 합집합이다.<BR>
중복된 행에 대해서는 중복된 행을 제거 후 1개만 출력한다.<BR>

```SQL
--코드 6-19 UNION 연산
SELECT A.INDUTY_CL_CD AS 업종분류코드 --업종분류코드
  FROM TB_INDUTY_CL A --업종분류
 WHERE A.INDUTY_CL_SE_CD = 'ICS001' --대
   AND A.INDUTY_CL_CD = 'Q' --음식
 UNION --UNION 연산자
SELECT A.UPPER_INDUTY_CL_CD AS 상위업종분류코드 --상위업종분류코드
  FROM TB_INDUTY_CL A --업종분류
 WHERE INDUTY_CL_SE_CD = 'ICS002' --중
   AND A.INDUTY_CL_CD LIKE 'Q%' --커피점/카페
;

업종분류코드|
------+
Q     |
```
중복되는 값을 제외한 유일한 값만을 출력한다.

<BR>
<BR>

<H2>UNION ALL 연산</H2>

UNION ALL연산은 여러 개의 SQL문에 대한 합집합이다.<BR>
UNION과는 다르게 UNION ALL 중복된 행 전부를 노출한다.<BR>

```SQL
--코드 6-20 UNION ALL 연산
SELECT A.INDUTY_CL_CD AS 업종분류코드 --업종분류코드
  FROM TB_INDUTY_CL A --업종분류
 WHERE A.INDUTY_CL_SE_CD = 'ICS001' --대
   AND A.INDUTY_CL_CD = 'Q' --음식
UNION ALL
SELECT A.UPPER_INDUTY_CL_CD AS 상위업종분류코드 --상위업종분류코드
  FROM TB_INDUTY_CL A --업종분류
 WHERE INDUTY_CL_SE_CD = 'ICS002' --중
   AND A.INDUTY_CL_CD LIKE 'Q%' --커피점/카페
;
업종분류코드|
------+
Q     |
Q     |
...중간생략
Q     |
Q     |
```
UNION과는 다르게 중복행 전부를 노출하는것을 볼수 있다.<BR>

<BR>
<BR>

<H2>INTERSECT 연산</H2>
INTERSECT 연산은 여러 개의 SQL문에 대한 교집합을 출력한다.<BR>
중복행이 존재 한다면 중복 행을 제거하고 1개의 행을 출력한다.

```SQL
SELECT A.SUBWAY_STATN_NO
     , A.LN_NM
     , A.STATN_NM
  FROM TB_SUBWAY_STATN A
 WHERE A.STATN_NM ='신도림'
INTERSECT
SELECT B.SUBWAY_STATN_NO
     , B.LN_NM
     , B.STATN_NM
  FROM TB_SUBWAY_STATN B
 WHERE B.LN_NM ='2호선'
ORDER BY SUBWAY_STATN_NO
;
SUBWAY_STATN_NO|LN_NM|STATN_NM|
---------------+-----+--------+
000044         |2호선  |신도림     |
```
두 SQL문의 공통 행만을 출력한것을 확인할수 있다.

```SQL
--코드 6-23 INTERSECT 연산과 동일한 결과집합을 도출 
SELECT A.SUBWAY_STATN_NO
     , A.LN_NM
     , A.STATN_NM
  FROM TB_SUBWAY_STATN A
 WHERE A.STATN_NM ='신도림'
   AND A.LN_NM = '2호선'
 ORDER BY SUBWAY_STATN_NO
;

--코드 6-24 INTERSECT 연산과 동일한 결과집합을 도출 
SELECT A.SUBWAY_STATN_NO
     , A.LN_NM
     , A.STATN_NM
  FROM TB_SUBWAY_STATN A
 WHERE STATN_NM ='신도림'
   AND EXISTS (SELECT 1
                 FROM TB_SUBWAY_STATN K
                WHERE K.SUBWAY_STATN_NO = A.SUBWAY_STATN_NO
                  AND K.LN_NM = '2호선'
               )
 ORDER BY SUBWAY_STATN_NO
;
```
하지만 INTERSECT 연산은 위에 있는 2가지 방법으로 인해서 잘 사용하지 않고 있다. 

<BR>
<BR>

<H2>MINUS 연산</H2>
MINUS 연산은 위에 SQL문의 결과집합에서 아래의 SQL문의 집합을 뺸 결과를 표시한다.<BR>
중복된 결과집합은 중복이 제거된 상태로 출력된다.

```SQL
--코드 6-25 MINUS 연산
SELECT A.LN_NM --노노선명
  FROM TB_SUBWAY_STATN A
 WHERE A.STATN_NM ='선릉'
MINUS
SELECT B.LN_NM --노선명
  FROM TB_SUBWAY_STATN B
 WHERE B.STATN_NM ='강남';
;

A 테이블 조회 결과 
LN_NM|
-----+
2호선  |
분당선  |

B테이블 조회 결과
LN_NM|
-----+

2호선  |
MINUS연산 결과
LN_NM|
-----+
분당선  |
```
A테이블 조회 결과 - B테이블 조회 결과 -> MINUS연산 결과인것을 확인할수 있다.



