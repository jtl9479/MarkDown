<H1>그룹함수</H1>

<h2>그룹 함수란</h2>

그룹함수를 사용하면 특정 집합의 소계/중계/합계/총 합계를 구할 수 있다.

>  그룹 함수의 종류
> |종류 | 설명 |
> |----|------|
> |ROLLUP| <UL>소그룹 간의 소계를 계산하는 기능이다.</UL><UL>ROLLUP 함수 내에 인자로 지정된 그룹화 컬럼은 소계를 생성하는 데 사용된다.</UL><UL>그룹화 컬럼의 수가N 이라고 했을 때 N+1의 소계가 발생한다.</UL><UL>ROLLUP 함수 내의 인자 순서가  바뀌면 결과도 바뀌게 된다.(ROLLUP은 계층구조이다.)</UL>|
> |CUBE| <UL>다차원적인 소계를 계산하는 기능이다.</UL>|
> |GROUPING SETS| <UL>특정 항목에 대한 소계를 계산하는 기능이다.</UL>|
>> 위 표에서와 같이 그룹 함수의 종류는 ROLLUP, CUBE, GROUPING SETS 이 있다.

https://www.youtube.com/watch?v=cy9Xiim1X94&ab_channel=SQL%EC%A0%84%EB%AC%B8%EA%B0%80%EC%A0%95%EB%AF%B8%EB%82%98

```SQL
--코드 6-60 합계 데이터 출력 - ROLLUP 함수 사용

SELECT A.AGRDE_SE_CD
     , SUM(A.POPLTN_CNT) AS POPLTN_CNT
  FROM TB_POPLTN A
 WHERE A.STD_YM = '202010'
   AND A.POPLTN_SE_CD = 'T'
 GROUP BY ROLLUP(A.AGRDE_SE_CD)
 ORDER BY A.AGRDE_SE_CD
;

AGRDE_SE_CD|POPLTN_CNT|
-----------+----------+
000        |   4005030|
010        |   4818481|
020        |   6802784|
030        |   6900752|
040        |   8312221|
050        |   8655292|
060        |   6669780|
070        |   3687451|
080        |   1714191|
090        |    250335|
100        |     21699|
           |  51838016|
--마지막 ROW에 합산결과가 나온다.           

--코드 6-61 합계 데이터 출력 - ROLLUP 함수안 했을 경우
SELECT A.AGRDE_SE_CD
     , A.POPLTN_SE_CD
     , SUM(A.POPLTN_CNT) AS POPLTN_CNT
  FROM TB_POPLTN A
 WHERE A.STD_YM = '202010'
   AND TO_NUMBER(AGRDE_SE_CD) < 040 
   AND A.POPLTN_SE_CD IN ('M', 'F')
 GROUP BY A.AGRDE_SE_CD, A.POPLTN_SE_CD
 ORDER BY A.AGRDE_SE_CD
;
AGRDE_SE_CD|POPLTN_SE_CD|POPLTN_CNT|
-----------+------------+----------+
000        |F           |   1951273|
000        |M           |   2053757|
010        |F           |   2325821|
010        |M           |   2492660|
020        |F           |   3229935|
020        |M           |   3572849|
030        |F           |   3346230|
030        |M           |   3554522|

--코드 6-61 합계 데이터 출력 - ROLLUP 함수 사용
SELECT A.AGRDE_SE_CD
     , A.POPLTN_SE_CD
     , SUM(A.POPLTN_CNT) AS POPLTN_CNT
  FROM TB_POPLTN A
 WHERE A.STD_YM = '202010'
   AND TO_NUMBER(AGRDE_SE_CD) < 040 
   AND A.POPLTN_SE_CD IN ('M', 'F')
 GROUP BY ROLLUP(A.AGRDE_SE_CD, A.POPLTN_SE_CD)
 ORDER BY A.AGRDE_SE_CD
;

-- (GROUP BY AGRDE_SE_CD,POPLTN_SE_CD) + (GROUP BY AGRDE_SE_CD + 합계)를 노출한다.
ROLLUP은 순서에 따라 값이 변경될수 있다. 


AGRDE_SE_CD|POPLTN_SE_CD|POPLTN_CNT|
-----------+------------+----------+
000        |F           |   1951273|
000        |M           |   2053757|
000        |            |   4005030|
010        |F           |   2325821|
010        |M           |   2492660|
010        |            |   4818481|
020        |F           |   3229935|
020        |M           |   3572849|
020        |            |   6802784|
030        |F           |   3346230|
030        |M           |   3554522|
030        |            |   6900752|
           |            |  22527047|

-> ROLLUP 함수를 사용할 경우 그룹의 합산을 구한다.      

```

<BR>
<BR>

<H2>ROLLUP + GROUPING 함수를 이용한 합계 </H2>

GROUPING -> 칼럼 값이 있을 경우는 '0' 없을 경우는 '1'을 표출한다.

```SQL
SELECT CASE WHEN GROUPING(A.AGRDE_SE_CD) = 0
            THEN A.AGRDE_SE_CD
            ELSE '전체합계' END AS AGRDE_SE_CD
     , CASE WHEN GROUPING(A.POPLTN_SE_CD) = 0
            THEN A.POPLTN_SE_CD
            ELSE '연령대별남녀합계' END AS POPLTN_SE_CD
     , SUM(A.POPLTN_CNT) AS POPLTN_CNT
  FROM TB_POPLTN A
 WHERE A.STD_YM = '202010'
   AND TO_NUMBER(AGRDE_SE_CD) < 040 
   AND A.POPLTN_SE_CD IN ('M', 'F')
 GROUP BY ROLLUP(A.AGRDE_SE_CD, A.POPLTN_SE_CD)
 ORDER BY A.AGRDE_SE_CD
;

AGRDE_SE_CD|POPLTN_SE_CD|POPLTN_CNT|
-----------+------------+----------+
000        |F           |   1951273|
000        |M           |   2053757|
000        |연령대별남녀합계    |   4005030|
010        |F           |   2325821|
010        |M           |   2492660|
010        |연령대별남녀합계    |   4818481|
020        |F           |   3229935|
020        |M           |   3572849|
020        |연령대별남녀합계    |   6802784|
030        |F           |   3346230|
030        |M           |   3554522|
030        |연령대별남녀합계    |   6900752|
전체합계       |연령대별남녀합계    |  22527047|
```

<br>
<br>

<h2>CUBE 함수를 이용한 합계 데이터 출력</h2>

https://www.youtube.com/watch?v=Hr3lD0BV3Rk&ab_channel=SQL%EC%A0%84%EB%AC%B8%EA%B0%80%EC%A0%95%EB%AF%B8%EB%82%98

```SQL

        SELECT A.AGRDE_SE_CD
             , A.POPLTN_SE_CD
             , SUM(A.POPLTN_CNT) AS POPLTN_CNT
          FROM TB_POPLTN A
         WHERE A.STD_YM = '202010'
           AND A.AGRDE_SE_CD < 020
           AND A.POPLTN_SE_CD IN ('M', 'F')
         GROUP BY CUBE(A.AGRDE_SE_CD, A.POPLTN_SE_CD)
         ORDER BY A.AGRDE_SE_CD;

AGRDE_SE_CD|POPLTN_SE_CD|POPLTN_CNT|
-----------+------------+----------+
000        |F           |   1951273|
000        |M           |   2053757|
000        |            |   4005030|
010        |F           |   2325821|
010        |M           |   2492660|
010        |            |   4818481|
           |F           |   4277094|
           |M           |   4546417|
           |            |   8823511|

--  (GROUP BY AGRDE_SE_CD, POPLTN_SE_CD)  + (GROUP BY AGRDE_SE_CD + 합계) + (GROUP BY POPLTN_SE_CD + 합계)
ROLLUP과 다르게 순서에 영향을 주지 않는다.

```

<H2>GROUPING SETS</H2>

https://www.youtube.com/watch?v=0nTPWiHDJOA&ab_channel=%EC%A0%84%EA%B4%91%EC%B2%A0OCP

```SQL

--코드 6-65 합계 데이터 출력 - GROUPING SETS 사용
SELECT AGRDE_SE_CD
     , POPLTN_SE_CD
     , SUM(POPLTN_CNT) POPLTN_CNT
  FROM TB_POPLTN A
 WHERE A.STD_YM = '202010'
   AND A.POPLTN_SE_CD IN ('M', 'F')
 GROUP BY GROUPING SETS(AGRDE_SE_CD, POPLTN_SE_CD, ())
 ORDER BY AGRDE_SE_CD, POPLTN_SE_CD, POPLTN_CNT
;

AGRDE_SE_CD|POPLTN_SE_CD|POPLTN_CNT|
-----------+------------+----------+
000        |            |   4005030|
010        |            |   4818481|
020        |            |   6802784|
030        |            |   6900752|
040        |            |   8312221|
050        |            |   8655292|
060        |            |   6669780|
070        |            |   3687451|
080        |            |   1714191|
090        |            |    250335|
100        |            |     21699|
           |F           |  25990297|
           |M           |  25847719|
           |            |  51838016|

```