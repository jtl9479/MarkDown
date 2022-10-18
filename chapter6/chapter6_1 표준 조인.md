<H1>표준 조인</H1>

<H2>일반집합연산자와 SQL의 비교</H2>
일반집합연산자는 SQL문의 UNION/UNION ALL/INTERSECT/EXCEPT/CROSS JOIN 기능으로 구현되어 있다. 

>일반집합연산자와 SQL문의 기능
>>|일반집합연산자|SQL문|설명|
>>|-------------|-----|----|
>>|UNOIN연산|UNION| <UL>UNION 연산은 수학적으로 합집합을 하는 연산이다.</UL><UL>교집합의 중복을 제거하는 연산을 수행하며 중복 제거로 인해 시스템에 부하가 발생할 수 있다.</UL><UL>UNION ALL 연산은 교집합의 중복을 제거하지 않고 중복된 것을 그대로 보여준다</UL><UL>그로 인해 UNION ALL은 중복 제거에 대한 시스템 부하가 발생하지 않는다.</UL><UL>만일 UNION과 UNION ALL의 출력 결과가 같다면 데이터 정렬 및 중복 제거 작업이 이루어지지 않는 UNION ALL을 사용하는 것이 더 빠르다</UL>|
>>|INTERSECTION연산|INTERSECT| <UL>INTERSECTION은 수학의 교집합을 제공하기 위한 연산이다.</UL><UL>두 집합의 공통 집합을 추출한다.</UL>|
>>|DIFFERENCE연산|EXCEPT(Oracle MINUS)| <UL>DIFFERENCE는 수학의 차집합으로서 첫 번째 집합에서 두 번째 집합과의 공통 집합을 제외한 부분이다.</UL>|
>>|PRODUCT연산|CROSS JOIN|<UL>PRODUCT의 경우는 CROSS PRODUCT라고 불리는 곱집합으로 JOIN 조건이 없는 경우 생길 수 있는 모든 데이터의 조합을 말한다.</UL><UL>양쪽 집합의 M*N건의 데이터 조합이 발생하며, CARTESIAN PRODUCT라고도 표현한다.</UL>|

<H2>순수관계연산자와 SQL의 비교</H2>
순수관계연산자는 SQL문의 WHERE/SELECT/다양한 JOIN 기능으로 구현되어있다.

>순수관계연산자와 SQL문의 기능
>>|순수관계연산자|SQL문|설명|
>>|-------------|-----|----|
>>|SELECT연산|WHERE절| <UL>SELECT 연산은 SQL문에서는 WHERE절 기능으로 구현이 된다.</UL><UL>행들에 대한 부분집합이라고 할 수 있다.</UL>|
>>|PROJECT연신|SELECT절|<UL>PROJECT연산은 SQL문장에서는 SELECT절의 선택 기능으로 구현되어있다.</UL><UL>열들에 대한 부분집합이라고 할 수 있다.</UL>|
>>|NATURAL JOIN연산|다양한 JOIN으로 구현| <UL>JOIN 연산은 보통 WHERE절에 조인 조건을 기재한다.</UL><UL>또한 FROM절에서의 NATURAL JOIN, INNER JOIN, LEFT OUTER JOIN,RIGHT OUTER JOIN, USING 조건절, ON 조건절 등으로 다양하게 발전하였다.</UL>|
>>|DIVIDE연산|사용 안함 | <UL>DIVIDE 연산은 나눗셈과 비슷한 개념으로 특정 집합을 'XZ'로 나누었을 떄, 즉 'XZ'를 모두 가지고 있는 집합이 답이 되는 기능이다.</UL>|

<BR>
<BR>

<H2>조인의 형태</H2>

>조인의 6가지 형태
>>|조인 형태|설명|
>>|--------|-----|
>>|INNER JOIN(이너 조인)| <UL>INNER JOIN은 JOIN조건에서 동일한 값이 있는 행만 추출한다.</UL><UL>동등(EQUI)조인이라고도 한디.</UL>|
>>|NATURAL JOIN(자연 조인)| <UL>NATURL JOIN은 두 테이블 간의 동일한 이름을 갖는 모든 칼럼에 대해 INNER JOIN을 수행한다.</UL>|
>>|USING 조건절| <UL>FROM절의 USING조건절을 이용하면 같은 이름을 가진 칼럼들 중에서 원하는 칼럼에 대해서만 선택적으로 INNER JOIN을 할 수 있다.</UL>|
>>|ON 조건절|<UL>JOIN 서술부(ON 조건절)와 비 JOIN 서술부(WHERE 조건절)를 분리하여 이해가 쉬우며 칼럼명이 다르더라도 JOIN 조건을 사용할 수 있다.</UL>|
>>|CROSS JOIN|<UL>PRODUCT 개념으로, 테이블 간 JOIN 조건이 없는 경우 생길 수 있는 모든 데이터의 조합을 말한다.</UL>|
>>|OUTER JOUN| <UL>JOIN 조건에서 동일한 값이 없는 행도 결과 집합에 포함시킬 떄 사용한다.</UL>|

<BR>
<BR>

<H2>INNER JOIN</H2>

>INNER JOIN 실습
```SQL
--코드 6-1 INNER JOIN 실습
SELECT A.SUBWAY_STATN_NO
     , A.LN_NM
     , A.STATN_NM
     , B.BEGIN_TIME
     , B.END_TIME
     , CASE WHEN B.TK_GFF_SE_CD = 'TGS001' THEN '승차'
            WHEN B.TK_GFF_SE_CD = 'TGS002' THEN '하차'
             END TK_GFF_SE_NM
     , B.TK_GFF_CNT
  FROM TB_SUBWAY_STATN A
     , TB_SUBWAY_STATN_TK_GFF B
 WHERE A.SUBWAY_STATN_NO = B.SUBWAY_STATN_NO --JOIN 부분
   AND A.SUBWAY_STATN_NO = '000001' --1호선 서울역
   AND B.STD_YM = '202010'
   AND B.BEGIN_TIME = '0800'
   AND B.END_TIME = '0900'
 ORDER BY B.TK_GFF_CNT DESC
;
```
테이블 TB_SUBWAY_STATN,TB_SUBWAY_STATN_TK_GFF JOIN한 쿼리로
WHERE절 A.SUBWAY_STATN_NO = B.SUBWAY_STATN_NO 해당 부분을 추가하여 INNER JOIN을 진행했다.

<BR>
<BR>

<H2>NATURAL JOIN</H2>

 NATURAL JOIN은 두 테이블의 동일한 이름을 가지는 칼럼이 모두 조인된다.

```SQL
SELECT DEPT_CD
     , A.DEPT_NM
     , B.EMP_NO
     , B.EMP_NM
  FROM TB_DEPT_6_1_6 A NATURAL JOIN TB_EMP_6_1_6 B
 ORDER BY DEPT_CD
;
--NATUAL JOIN

SELECT DEPT_CD
     , A.DEPT_NM
     , B.EMP_NO
     , B.EMP_NM
  FROM TB_DEPT_6_1_6 A
     , TB_EMP_6_1_6 B
 WHERE A.DEPT_CD AND B.DEPT_CD
 ORDER BY DEPT_CD
;
-- INNER JOIN

-- 두 JOIN의 결과 집합이 동일하다.
```
TB_DEPT_6_1_6, TB_EMP_6_1_6 테이블에 공통으로 존재하는 칼럼을 자동으로 JOIN하여 노출한다.

<BR>
<BR>

<H2>USING절</H2>

NATURAL JOIN을 사용할 경우 해당 테이블들에게 동시에 존재하는 모든 컬럼에 대해서 조인이 이루어진다.<BR> 
하지만 특정한 컬럼만 지정하고 싶은 경우에는 **USING절을 이용하면 동일한 이름을 가진 컬럼중 원하는 컬럼에 대해서만 선택적으로 JOIN이 가능하다.**<BR>
USING절 또한 INNER JOIN과 결과가 동일하게 추출된다.<BR>

```SQL
SELECT SUBWAY_STATN_NO --USING절을 사용하는 컬럼에 대해서는 별칭사용이 불가하다
     , A.LN_NM
     , A.STATN_NM
     , B.BEGIN_TIME
     , B.END_TIME
     , CASE WHEN B.TK_GFF_SE_CD = 'TGS001' THEN '승차'
            WHEN B.TK_GFF_SE_CD = 'TGS002' THEN '하차'
             END TK_GFF_SE_NM
     , B.TK_GFF_CNT
  FROM TB_SUBWAY_STATN A JOIN TB_SUBWAY_STATN_TK_GFF B
 USING (SUBWAY_STATN_NO) --USING절을 사용하는 컬럼에 대해서는 별칭사용이 불가하다
 WHERE SUBWAY_STATN_NO = '000001' --USING절을 사용하는 컬럼에 대해서는 별칭사용이 불가하다
   AND B.STD_YM = '202010'
   AND B.END_TIME = '0900'
 ORDER BY B.TK_GFF_CNT DESC
;
```

<BR>
<BR>

<H2>ON 절</H2>

ON절을 이용한 조인은 ON절에 JOIN조건을 기재할 수 있습니다.
여러 개의 컬럼에 대한 JOIN 조건을 기재 가능하며 무엇보다
**<U> 조인하는 칼럼의 이름이 서로 달라도 조인을 수행할 수 있다. </U>**

```SQL
SELECT A.SUBWAY_STATN_NO
     , A.LN_NM
     , A.STATN_NM
     , B.BEGIN_TIME
     , B.END_TIME
     , CASE WHEN B.TK_GFF_SE_CD = 'TGS001' THEN '승차'
            WHEN B.TK_GFF_SE_CD = 'TGS002' THEN '하차'
             END TK_GFF_SE_NM
     , B.TK_GFF_CNT
  FROM TB_SUBWAY_STATN A
 INNER JOIN TB_SUBWAY_STATN_TK_GFF B
         ON (A.SUBWAY_STATN_NO = B.SUBWAY_STATN_NO)
 WHERE A.SUBWAY_STATN_NO = '000001' --1호선 서울역
   AND B.STD_YM = '202010'
   AND B.BEGIN_TIME = '0800'
   AND B.END_TIME = '0900'
 ORDER BY B.TK_GFF_CNT DESC
;
--ON절을 이용하여 JOIN을 실행
```

<BR>
<BR>

<H2>3개의 테이블 조인</H2>

테이블 3개를 조인할 경우에 조인 조건은 최소 2개가 필요합니다.<BR>

EX) A,B,C 테이블 3개를 조인한다면 A와 B 테이블 조인 후 AB 테이블의 조인결과와 다시 C테이블을 조인한다.<BR>

>최소 조인 조건 개수
>> 최소 조인 조건 개수 = 조인 테이블 개수 -1

```SQL
   --코드 6-7 3개의 테이블 조인 - 오라클 방식
SELECT A.SUBWAY_STATN_NO
     , A.LN_NM
     , A.STATN_NM
     , B.BEGIN_TIME
     , B.END_TIME
     , C.TK_GFF_SE_NM
     , B.TK_GFF_CNT
  FROM TB_SUBWAY_STATN A
     , TB_SUBWAY_STATN_TK_GFF B
     , TB_TK_GFF_SE C
 WHERE A.SUBWAY_STATN_NO = B.SUBWAY_STATN_NO --JOIN 조건
   AND B.TK_GFF_SE_CD = C.TK_GFF_SE_CD --JOIN 조건
   AND A.SUBWAY_STATN_NO = '000032' --2호선 강남
   AND B.STD_YM = '202010'
   AND B.BEGIN_TIME = '0800'
   AND B.END_TIME = '0900'
 ORDER BY B.TK_GFF_CNT
;

--코드 6-8 3개의 테이블 조인 - ANSI 표준 방식(ON절)
SELECT A.SUBWAY_STATN_NO
     , A.LN_NM
     , A.STATN_NM
     , B.BEGIN_TIME
     , B.END_TIME
     , C.TK_GFF_SE_NM
     , B.TK_GFF_CNT
  FROM TB_SUBWAY_STATN A
 INNER JOIN TB_SUBWAY_STATN_TK_GFF B
         ON (A.SUBWAY_STATN_NO = B.SUBWAY_STATN_NO)
 INNER JOIN TB_TK_GFF_SE C
         ON(B.TK_GFF_SE_CD = C.TK_GFF_SE_CD)
 WHERE A.SUBWAY_STATN_NO = '000032' --2호선 강남
   AND B.STD_YM = '202010'
   AND B.BEGIN_TIME = '0800'
   AND B.END_TIME = '0900'
 ORDER BY B.TK_GFF_CNT
;
```

<H2>OUTER JOIN</H2>

A와 B 테이블이 조인 될 때, A테이블을 기준으로 조인한다면 A 테이블이 OUTER 집합이 되고, B 테이블은 INNER 집합이 된다.<BR>
OUTER JOIN은 'OUTER 집합을 기준으로 조인한다' 라는 뜻이며 OUTER 쪽의 테이블은 모두 출력되고 INNER 집합은 매칭되는 집합만 보여준다.<BR>


>OUTER JOIN의 종류 및 설명
>>|OUTER JOIN 종류| 설명|
>>|--------------|-----|
>>|LEFT OUTER JOIN| <UL>FROM절에 기재한 테이블을 중심으로 <U>왼쪽에 기재한 테이블이 OUTER 집합이 되고 오른쪽에 기재한 테이블이 INNER집합이 된다.</U></UL><UL>왼쪽 OUTER는 다 나오고, 오른쪽 INNER는 공통부분만 추출된다.</UL>|
>>|RIGHT OUTER JOIN| <UL>FROM절에 기재한 테이블을 중심으로 <U>오른쪽에 기재한 테이블이 OUTER 집합이 되고 왼쪽에 기재한 테이블이 INNER집합이 된다.</U></UL><UL>오른쪽 OUTER는 다 나오고, 왼쪽 INNER는 공통부분만 추출된다.</UL>|
>>|FULL OUTER JOIN|<UL>FROM절에 기재한 테이블에 대해서 LEFT OUTER JOIN의 결과와 RIGHT OUTER JOIN의 결과 그리고 INNER JOIN의 결과가 모두 출력된다.</UL>|

<H2>LEFT OUTER JOIN</H2>
FROM절 기준으로 왼쪽에 기재한 테이블이 OUTER집합이 되고 오른쪽에 기재한 테이블이 INNER집합이 된다.<BR>
쿼리 결과는 왼쪽 테이블의 집합은 무조건 다 추출되며, 오른쪽 테이블의 집합은 조인으로 매칭된 집합만 추출된다.

<BR>
<BR>

```SQL
--코드 6-10 LEFT OUTER JOIN - 오라클 DBMS 방식
SELECT NVL(A.DEPT_CD, '(Null)') AS A_DEPT_CD
     , NVL(A.DEPT_NM, '(Null)') AS A_DEPT_NM
     , NVL(B.EMP_NO , '(Null)') AS B_EMP_NO
     , NVL(B.EMP_NM , '(Null)') AS B_EMP_NM
     , NVL(B.DEPT_CD, '(Null)') AS B_DEPT_CD
  FROM TB_DEPT_6_1_10 A, TB_EMP_6_1_10 B
 WHERE A.DEPT_CD = B.DEPT_CD(+) --왼쪽 OUTER 오른쪽 INNER
 ORDER BY A.DEPT_CD
;

--코드 6-11 LEFT OUTER JOIN - ANSI 방식
SELECT NVL(A.DEPT_CD, '(Null)') AS A_DEPT_CD
     , NVL(A.DEPT_NM, '(Null)') AS A_DEPT_NM
     , NVL(B.EMP_NO , '(Null)') AS B_EMP_NO
     , NVL(B.EMP_NM , '(Null)') AS B_EMP_NM
     , NVL(B.DEPT_CD, '(Null)') AS B_DEPT_CD
  FROM TB_DEPT_6_1_10 A
  LEFT OUTER JOIN TB_EMP_6_1_10 B
               ON (A.DEPT_CD = B.DEPT_CD)--왼쪽 OUTER 오른쪽 INNER
 ORDER BY A.DEPT_CD
;

D001	데이터팀	E001	이경오	D001
D001	데이터팀	E002	이수지	D001
D002	영업팀	E003	김영업	D002
D002	영업팀	E004	박영업	D002
D003	IT개발팀	E005	최개발	D003
D003	IT개발팀	E006	정개발	D003
D004	4차산업혁명팀	(Null)	(Null)	(Null)
D005	AI연구팀	(Null)	(Null)	(Null)
```

해당 쿼리를 조회할 경우 모든 부서의 결과가 추출되었다.
TB_EMP_6_1_10테이블에 존재하지 않는 4차산업혁명팀, AI연구팀도 모두 결과집합에 포함된 것을 알수 있다.

<BR>
<BR>

<H2>RIGHT OUTER JOIN</H2>
FROM절 기준으로 오른쪽에 기재한 테이블이 OUTER집합이 되고 왼쪽에 기재한 테이블이 INNER집합이 된다.<BR>
쿼리 결과는 오른쪽 테이블의 집합은 무조건 다 추출되며, 왼쪽 테이블의 집합은 조인으로 매칭된 집합만 추출된다.

<BR>
<BR>

```SQL
 --코드 6-12 RIGHT OUTER JOIN - 오라클 DBMS 방식
SELECT NVL(A.DEPT_CD, '(Null)') AS A_DEPT_CD
     , NVL(A.DEPT_NM, '(Null)') AS A_DEPT_NM
     , NVL(B.EMP_NO , '(Null)') AS B_EMP_NO
     , NVL(B.EMP_NM , '(Null)') AS B_EMP_NM
     , NVL(B.DEPT_CD, '(Null)') AS B_DEPT_CD
  FROM TB_DEPT_6_1_10 A
     , TB_EMP_6_1_10 B
 WHERE A.DEPT_CD(+) = B.DEPT_CD --왼쪽 INNER 오른쪽 OUTER
;

--코드 6-13 RIGHT OUTER JOIN - ANSI 방식
SELECT NVL(A.DEPT_CD, '(Null)') AS A_DEPT_CD
     , NVL(A.DEPT_NM, '(Null)') AS A_DEPT_NM
     , NVL(B.EMP_NO, '(Null)') AS B_EMP_NO
     , NVL(B.EMP_NM, '(Null)') AS B_EMP_NM
     , NVL(B.DEPT_CD, '(Null)') AS B_DEPT_CD
  FROM TB_DEPT_6_1_10 A
 RIGHT OUTER JOIN TB_EMP_6_1_10 B
               ON (A.DEPT_CD = B.DEPT_CD) --왼쪽 INNER 오른쪽 OUTER
 ORDER BY A.DEPT_CD
;

A_DEPT_CD|A_DEPT_NM|B_EMP_NO|B_EMP_NM|B_DEPT_CD|
---------+---------+--------+--------+---------+
D001     |데이터팀     |E002    |이수지     |D001     |
D001     |데이터팀     |E001    |이경오     |D001     |
D002     |영업팀      |E004    |박영업     |D002     |
D002     |영업팀      |E003    |김영업     |D002     |
D003     |IT개발팀    |E006    |정개발     |D003     |
D003     |IT개발팀    |E005    |최개발     |D003     |
(Null)   |(Null)   |E008    |차인턴     |(Null)   |
(Null)   |(Null)   |E007    |석신입     |(Null)   |
(Null)   |(Null)   |E009    |강회장     |D000     |
```
TB_EMP_6_1_10 테이블에 들어있는 모든 컬럼값이 추출되었다.

<BR>
<BR>

<H2>FULL OUTER JOIN</H2>

FULL OUTER JOIN은 INNER JOIN뿐만 아니라 LEFT OUTER JOIN, RIGHT OUTER JOIN의 집합 모두 출력이 된다.<BR>
실무에서는 데이터 검증 작업을 진행할 때 자주 쓰이고 있는 조인의 형태라고 할 수 있다.<BR>
해당 조인 형태는 오라클에서는 존재 하지 않으며 ANSI표준 방직으로만 존재한다.

```SQL
--코드 6-14 FULL OUTER JOIN
SELECT NVL(A.DEPT_CD, '(Null)') AS A_DEPT_CD
     , NVL(A.DEPT_NM, '(Null)') AS A_DEPT_NM
     , NVL(B.EMP_NO , '(Null)') AS B_EMP_NO
     , NVL(B.EMP_NM , '(Null)') AS B_EMP_NM
     , NVL(B.DEPT_CD, '(Null)') AS B_DEPT_CD
  FROM TB_DEPT_6_1_10 A
  FULL OUTER JOIN TB_EMP_6_1_10 B
               ON (A.DEPT_CD = B.DEPT_CD)
;

A_DEPT_CD|A_DEPT_NM|B_EMP_NO|B_EMP_NM|B_DEPT_CD|
---------+---------+--------+--------+---------+
D001     |데이터팀     |E001    |이경오     |D001     |
D001     |데이터팀     |E002    |이수지     |D001     |
D002     |영업팀      |E003    |김영업     |D002     |
D002     |영업팀      |E004    |박영업     |D002     |
D003     |IT개발팀    |E005    |최개발     |D003     |
D003     |IT개발팀    |E006    |정개발     |D003     |
(Null)   |(Null)   |E007    |석신입     |(Null)   |
(Null)   |(Null)   |E008    |차인턴     |(Null)   |
(Null)   |(Null)   |E009    |강회장     |D000     |
D004     |4차산업혁명팀  |(Null)  |(Null)  |(Null)   |
D005     |AI연구팀    |(Null)  |(Null)  |(Null)   |
```

결과를 보면 RIGHT OUTER JOIN 뿐만 아니라 LEFT OUTER JOIN의 결과 값도 도출된 것을 확인할수 있다.

<BR>
<BR>

<H2>CROSS JOIN</H2>
2개의 테이블을 기재한 후 어떠한 조건도 선언하지 않을 경우
CROSS JOIN이 발생한다.
CROSS JOIN이란 CARTESIAN PRODUCT(곱집합)라고도 불린다.<BR>

```SQL
--코드 6-15 CROSS JOIN - 조인 조건 없는 방식
SELECT ROWNUM AS RNUM
     , NVL(A.DEPT_CD, '(Null)') AS A_DEPT_CD
     , NVL(A.DEPT_NM, '(Null)') AS A_DEPT_NM
     , NVL(B.EMP_NO , '(Null)') AS B_EMP_NO
     , NVL(B.EMP_NM , '(Null)') AS B_EMP_NM
     , NVL(B.DEPT_CD, '(Null)') AS B_DEPT_cD
  FROM TB_DEPT_6_1_10 A
     , TB_EMP_6_1_10 B
 ORDER BY RNUM
;

--코드 6-16 CROSS JOIN - ANSI 표준 방식
SELECT ROWNUM AS RNUM
     , NVL(A.DEPT_CD, '(Null)') AS A_DEPT_CD
     , NVL(A.DEPT_NM, '(Null)') AS A_DEPT_NM
     , NVL(B.EMP_NO , '(Null)') AS B_EMP_NO
     , NVL(B.EMP_NM , '(Null)') AS B_EMP_NM
     , NVL(B.DEPT_CD, '(Null)') AS B_DEPT_CD
  FROM TB_DEPT_6_1_10 A
 CROSS JOIN TB_EMP_6_1_10 B
 ORDER BY RNUM
;
RNUM|A_DEPT_CD|A_DEPT_NM|B_EMP_NO|B_EMP_NM|B_DEPT_CD|
----+---------+---------+--------+--------+---------+
   1|D001     |데이터팀     |E001    |이경오     |D001     |
   2|D001     |데이터팀     |E002    |이수지     |D001     |
   3|D001     |데이터팀     |E003    |김영업     |D002     |
....중간생략
  42|D005     |AI연구팀    |E006    |정개발     |D003     |
  43|D005     |AI연구팀    |E007    |석신입     |(Null)   |
  44|D005     |AI연구팀    |E008    |차인턴     |(Null)   |
  45|D005     |AI연구팀    |E009    |강회장     |D000     |
```
해당 결과를 보면 TB_DEPT_6_1_10 테이블 5행 TB_EMP_6_1_10 테이블9행에 대한 모든 조건(5 * 9)이 도출된것을 확인할수 있다.


