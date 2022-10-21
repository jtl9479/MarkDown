<H1>서브쿼리</H1>
서브쿼리란 하나의 SQL문 안에 포함되어 있는 또 다른 SQL문을 말한다.
<BR>
<BR>

<H3>서브쿼리 사용 시 주의점</H3>

> * 서브쿼리는 소괄호 "()"로 감싸서 사용한다.
> * 서브쿼리는 단일행 또는 복수행 비교 연산자와 함께 사용 가능하다.
> * 단일행 비교 연산자는 서브쿼리의 결과가 반드시 0 또는 1건 이여야 한다.
> * 복수행 비교 연산자는 서브쿼리의 결과 건수와 상관없다. 1건 혹은 여러 건도 가능하다.
> * 서브쿼리 내에서는 ORDER BY절을 사용하지 못한다. <U>ORDER BY절은 전체 SQL문 내에서 오직 1개만 올 수 있기 때문에</U> ORDER BY절은 메인쿼리의 맨 마지막 SQL문 아래에 위치해야 한다.

<BR>
<BR>

<H3>서브쿼리 사용이 가능한 위치</H3>

> |위치|설명|
> |---|----|
> |SELECT절| SELECT절에 위치한 서브쿼리를 스칼라 서브쿼리라고 한다.|
> |FROM절| FROM절에 위치한 서브쿼리를 인라인뷰 서브쿼리라고 한다.|
> |WHERE절| WHERE절에 위치한 서브쿼리를 서브쿼리라고 한다.|
> |HAVING절| HAVING절에 위치한 서브쿼리를 서브쿼리라고 한다.|
> |INSERT문의 VALUES절| INSERT문의 VALUES절에 위치한 서브쿼리를 서브쿼리라고 한다.|
> |UPDATE문의 SET절| UPDATE문의 SET절에 위치한 서브쿼리를 서브쿼리라고 한다.|

<BR>
<BR>

<H3>서브쿼리의 동작 방식</H3>

> |동작방식 | 설명  |
> |--------|-------|
> |비연관 서브쿼리| <UL>서브쿼리가 메인쿼리의 칼럼을 가지고 있지 않은 형태의 서브쿼리다.</UL><UL>메인쿼리에 값을 제공하기 위한 목적으로 주로 사용한다.</UL>|
> |연관 서브쿼리 | <UL>서브쿼리가 메인쿼리의 값을 가지고 있는 형태의 서브쿼리다.</UL><UL>일반즉으로 메인쿼리가 먼저 수행되어 읽혀 진 데이터를 서브쿼리에서 조건이 맞는지 확인하고자 할 때 주로 사용한다.</UL>|

<BR>

>비연관 서브쿼리 예제
```SQL
SELECT * 
  FROM student 
  WHERE dep_id =
  (
    SELECT id from department WHERE name = 'Computer'
  );
```

<BR>

> 연관 서브쿼리 예제
```SQL
SELECT   name, gender, age
  FROM     student Greater
  WHERE    age >
  (SELECT   AVG (age)
     FROM     student average
     WHERE      greater.dep_id = average.dep_id) ;
```

<BR>
<BR>

<H3>반환  형태에 따른 서브쿼리 분류 </H3>

> |반환 형태 | 설명 |
> |---------|------|
> | 단일행 서브쿼리 | <UL>서브쿼리의 실행 결과가 항상 1건 이하인 서브쿼리를 의미한다.</UL><UL>항상 단일행 비교 연산자와 함께 사용된다.</UL><UL>단일행 비교 연산자 [ =, <, <=, >, =>, <> ] </UL>|
> | 다중행 서브쿼리 | <ul>서브쿼리의 실행 결과가 여러 건인 서브쿼리를 의미한다.</ul><ul> 다중행 서브쿼리는 다중행 비교 연산자와 함께 사용한다.</ul><ul>다중행 비교 연산자 [ IN, ALL, ANY, SOME, EXISTS]</ul>
> | 다중컬럼 서브쿼리 | <UL>서브쿼리의 실행 결과로 여러 칼럼을 반환한다.</UL><UL>메인쿼리의 조건 절에 여러 컬럼을 동시에 비교할 수 있다.</UL><UL>메인쿼리 칼럼이랑 비교할 경우 메인쿼리의 컬럼 수와 순서가 동일해야 한다.</UL>

<BR>
<BR>

<H3>단일행 서브쿼리</H3>
단일행 서브쿼리는 서브쿼리의 결과가 0건 혹은 1건인 SQL문이 서브쿼리로 존재하는 SQL문을 말한다.

<BR>

```SQL

--2022년 10월 1달 동안 08:00 부터 09:00까지 하차한 인원이 9호선 여의도역에서 하차한 인원수보다 많은 지하철역의 정 보 및 하차인원수를 구하라

SELECT A.TK_GFF_CNT
     , A.SUBWAY_STATN_NO
     , B.LN_NM
     , B.STATN_NM
  FROM TB_SUBWAY_STATN_TK_GFF A
     , TB_SUBWAY_STATN B
 WHERE A.STD_YM = '202010' --2020년 10월
   AND A.BEGIN_TIME = '0800' --출근시간대
   AND A.END_TIME = '0900' --출근시간대
   AND A.SUBWAY_STATN_NO = B.SUBWAY_STATN_NO
   AND TK_GFF_SE_CD = 'TGS002' --하차
   AND TK_GFF_CNT > (
                      SELECT K.TK_GFF_CNT
                        FROM TB_SUBWAY_STATN_TK_GFF K
                       WHERE K.SUBWAY_STATN_NO = '000615' --9호선 여의도역
                         AND K.STD_YM = '202010' --2020년 10월
                         AND K.BEGIN_TIME = '0800' --출근시간대
                         AND K.END_TIME = '0900' --출근시간대
                         AND K.TK_GFF_SE_CD = 'TGS002' --하차
                    )
ORDER BY A.TK_GFF_CNT DESC;
```

<BR>
<BR>

<H3>다중행 서브쿼리</H3>
다중행 서브쿼리는 서브쿼리의 결과가 2건 이상인 SELECT문이 서브쿼리로 존재한다.

<BR>

```SQL
--2020년 10월 1달 동안 08 ~09시 까지 하차한 인원수가 250.000명을 초과하는 역의 정보를 출력하라
SELECT B.SUBWAY_STATN_NO
     , B.LN_NM
     , B.STATN_NM
  FROM TB_SUBWAY_STATN B
 WHERE B.SUBWAY_STATN_NO IN (
                              SELECT K.SUBWAY_STATN_NO
                                FROM TB_SUBWAY_STATN_TK_GFF K
                               WHERE K.STD_YM = '202010' --2020년 10월
                                 AND K.BEGIN_TIME = '0800' --출근시간대
                                 AND K.END_TIME = '0900' --출근시간대
                                 AND K.TK_GFF_SE_CD = 'TGS002' --하차
                                 AND K.TK_GFF_CNT >= 250000
                            )
ORDER BY B.SUBWAY_STATN_NO
;
```

<BR>
<BR>

<H3>다중컬럼 서브쿼리</H3>
다중컬럼 서브쿼리는 서브쿼리가 리턴하는 컬럼의 수가 2개 이상인 서브쿼리를 말한다.

<BR>

```SQL
SELECT A.ADSTRD_CD
     , B.ADSTRD_NM
     , A.STD_YM
     , A.POPLTN_SE_CD
     , A.AGRDE_SE_CD
     , A.POPLTN_CNT
  FROM TB_POPLTN A
     , TB_ADSTRD B
 WHERE A.STD_YM = '202010'
   AND A.POPLTN_SE_CD = 'T'
   AND (A.AGRDE_SE_CD, A.POPLTN_CNT) IN
                                       (
                                         SELECT K.AGRDE_SE_CD
                                              , MAX(K.POPLTN_CNT) AS POPLTN_CNT
                                           FROM TB_POPLTN K
                                          WHERE K.STD_YM = '202010' --2020년 10월 기준
                                            AND K.POPLTN_SE_CD = 'T' --인구구분코드 : 전체
                                          GROUP BY K.AGRDE_SE_CD
                                       )
   AND A.ADSTRD_CD = B.ADSTRD_CD
 ORDER BY A.AGRDE_SE_CD
;
```

<BR>
<BR>

<H3>EXISTS문 서브쿼리 실습</H3>
EXISTS문은 서브쿼리 조건의 결과가 참이라면 결과집합에 포합시킵니다.
<BR>
수학적인 의미로 교집합에 해당한다.<BR>
A테이블 - B테이블 과의 교집합 부분을 출력한다.<BR>
NOT EXISTS문은 차집합에 해당한다.<BR>
A테이블 - B테이블 과의 차집합 부분을 출력한다.<BR>
<BR>

> EXISTS 예제 쿼리
```SQL
-- 2020년10월 1달 동안 승차 혹은 하차 인원수가 250,000명 이상인 시간대가 있었던 지하철을 출력하시오
SELECT A.* 
  FROM TB_SUBWAY_STATN A
 WHERE EXISTS ( SELECT SUBWAY_STATN_NO 
				  FROM TB_SUBWAY_STATN_TK_GFF AA
 			     WHERE STD_YM = '202010'
   				   AND TK_GFF_CNT > 250000
   				   AND AA.SUBWAY_STATN_NO = A.SUBWAY_STATN_NO);
```

<BR>

> NOT EXXSIS 예제 쿼리
```SQL
-- 2020년10월 1달 동안 승차 혹은 하차 인원수가 250,000명 이상인 시간대가 없었던 지하철을 출력하시오
SELECT A.* 
  FROM TB_SUBWAY_STATN A
 WHERE NOT EXISTS ( SELECT SUBWAY_STATN_NO 
				  FROM TB_SUBWAY_STATN_TK_GFF AA
 			     WHERE STD_YM = '202010'
   				   AND TK_GFF_CNT > 250000
   				   AND AA.SUBWAY_STATN_NO = A.SUBWAY_STATN_NO);
```

<H3>스칼라 서브쿼리 실습</H3>
SQL문의 SELECT절에서 사용하는 서브쿼리를 스칼라 서브쿼리라고 한다.

**<U>스칼라 서브쿼리는 결과 행의 건수가 1건 이하여야만 SQL에러가 발생하지 않는다.</U>**

```SQL

 SELECT절 내에서 스칼라  서브쿼리를 사용하여 행정구역(TB_ADSTRD) 테이블에 존재하는 행정구역명(ADSTRD_NM)컬럼을 조회하고, 조회 조건은 행정구역코드(ADSTRD_CD)컬럼을 조건으로 비교한다.

 --코드 6-42 스칼라 서브쿼리 실습
SELECT A.ADSTRD_CD
     , (SELECT L.ADSTRD_NM
          FROM TB_ADSTRD L
         WHERE L.ADSTRD_CD = A.ADSTRD_CD) AS ADSTRD_NM
     , A.STD_YM
     , A.POPLTN_SE_CD
     , A.AGRDE_SE_CD
     , A.POPLTN_CNT
  FROM TB_POPLTN A
 WHERE A.STD_YM = '202010'
   AND A.POPLTN_SE_CD = 'T'
   AND (A.AGRDE_SE_CD, A.POPLTN_CNT) IN
                                        (
                                         SELECT K.AGRDE_SE_CD
                                              , MAX(K.POPLTN_CNT) AS POPLTN_CNT
                                           FROM TB_POPLTN K
                                          WHERE K.STD_YM = '202010' --2020년 10월 기준
                                            AND K.POPLTN_SE_CD = 'T' --인구구분코드 : 전체
                                          GROUP BY K.AGRDE_SE_CD
                                        )
;
```
해당 쿼리는 INNER JOIN으로 해결이 가능하다.

<BR>
<BR>

<H3>인라인뷰 서브쿼리 실습</H3>
SQL문의 FROM절에서 사용하는 서브쿼리를 인라인뷰 서브쿼리라고 한다.

 **<U> 인라인뷰 서브쿼리는 메인SQL문에서 인라인뷰 서브쿼리가 출력한 칼럼 혹은 값을 사용할 수 있다. </U>**

 ```SQL
--코드 6-44 인라인뷰 서브쿼리의 사용
SELECT B.SUBWAY_STATN_NO
     , B.LN_NM
     , B.STATN_NM
     , A.STD_YM
     , A.BEGIN_TIME
     , A.END_TIME
     , A.TK_GFF_SE_CD
     , A.TK_GFF_CNT
  FROM
     (
       SELECT A.SUBWAY_STATN_NO
            , A.STD_YM
            , A.BEGIN_TIME
            , A.END_TIME
            , A.TK_GFF_SE_CD
            , A.TK_GFF_CNT
         FROM TB_SUBWAY_STATN_TK_GFF A
        WHERE A.STD_YM = '202010'
          AND A.BEGIN_TIME = '1800'
          AND A.END_TIME = '1900'
          AND A.TK_GFF_SE_CD = 'TGS002'
          AND A.TK_GFF_CNT > 150000
     ) A
     , TB_SUBWAY_STATN B
 WHERE A.SUBWAY_STATN_NO = B.SUBWAY_STATN_NO
 ORDER BY A.TK_GFF_CNT DESC
;
 ```
<BR>
<BR>

<H3>HAVING 서브쿼리 실습</H3>

```SQL
SELECT A.SUBWAY_STATN_NO
     , (SELECT L.STATN_NM || '(' || L.LN_NM ||')'
          FROM TB_SUBWAY_STATN L
         WHERE L.SUBWAY_STATN_NO = A.SUBWAY_STATN_NO
       ) AS STATN_INFO
     , MAX(A.TK_GFF_CNT) AS TK_GFF_CNT
  FROM TB_SUBWAY_STATN_TK_GFF A
 WHERE A.STD_YM = '202010'
   AND A.BEGIN_TIME = '1800'
   AND A.END_TIME = '1900'
   AND A.TK_GFF_SE_CD = 'TGS002'
 GROUP BY A.SUBWAY_STATN_NO
HAVING MAX(A.TK_GFF_CNT)> (
                                       SELECT 
                                       	 TRUNC(AVG(A.TK_GFF_CNT))
                                         FROM TB_SUBWAY_STATN_TK_GFF A
                                        WHERE A.STD_YM = '202010'
                                          AND A.BEGIN_TIME = '1800'
                                          AND A.END_TIME = '1900'
                                          AND A.TK_GFF_SE_CD = 'TGS002'
                                     )
ORDER BY TK_GFF_CNT DESC
;
```
<BR>
<BR>

<H3>UPDATE문의 SET절에 위치하는 서브쿼리</H3>

```SQL
UPDATE TB_POPLTN A
   SET A.ADSTRD_NM = ( SELECT K.ADSTRD_NM
                         FROM TB_ADSTRD K
                        WHERE K.ADSTRD_CD = A.ADSTRD_CD
                     )
;
```
<BR>
<BR>

<H3>INSERT문에 사용되는 서브쿼리</H3>

```SQL
--코드 6-52 INSERT문의 VALUES절에 사용된 서브쿼리
INSERT INTO TB_SUBWAY_STATN_TK_GFF_SUM
     VALUES ('000615',
              (SELECT SUM(TK_GFF_CNT)
                 FROM TB_SUBWAY_STATN_TK_GFF
                WHERE SUBWAY_STATN_NO = '000615' --9호선 여의도역
              )
            )
;
```

<BR>
<BR>

<H3>뷰</H3>

> 뷰의 장점
SELECT문을 뷰로 생성해놓고, 해당 뷰만 호출하면 뷰 내부에 있는 SQL문을 호출할 수 있다.
>|장점|설명|
>|---|----|
>|독립성| <UL>테이블 구조가 변경되어도 뷰를 사용하는 응용 프로그램은 변경하지 않아도 된다.</UL>|
>|편리성| <UL>복잡한 질의를 뷰로 생성함으로써 관련 질의를 단순하게 작성할 수 있다. </UL><UL>또한 해당 형태의 SQL문을 자주 사용할 때 뷰를 이용하면 편리하게 사용할 수 있다.</UL>|
>|보안성| <UL>개인정보와 같은 숨기고 싶은 민감한 정보가 존재한다면, 뷰를 생성할 때 해당 정보를 제외시키고 생성함으로써 사용자들에게 정보 노출을 하지 않을 수 있다.</UL>|

<br>
<br>

>뷰 생성

```SQL
--코드 6-55 뷰 생성
CREATE OR REPLACE VIEW V_STARBUCKS_POPLTN_INFO AS
SELECT A.BSSH_NO
     , A.CMPNM_NM
     , A.BHF_NM
     , A.ADSTRD_CD
     , B.ADSTRD_NM
     , SUM(C.POPLTN_CNT) AS SUM_POPLTN_CNT
  FROM TB_BSSH A
     , TB_ADSTRD B
     , TB_POPLTN C
 WHERE ( CMPNM_NM LIKE '%스타벅스%'
         OR
         UPPER(CMPNM_NM) LIKE '%STARBUCKS%'
       )
   AND A.ADSTRD_CD = B.ADSTRD_CD
   AND B.ADSTRD_CD = C.ADSTRD_CD
   AND C.STD_YM = '202010'
   AND C.POPLTN_SE_CD = 'T'
 GROUP BY A.BSSH_NO, A.CMPNM_NM, A.BHF_NM, A.ADSTRD_CD, B.ADSTRD_NM
 ORDER BY A.BSSH_NO, A.CMPNM_NM, A.BHF_NM, A.ADSTRD_CD, B.ADSTRD_NM
;
--코드 6-56 뷰 조회
SELECT A.BSSH_NO
     , A.CMPNM_NM
     , A.BHF_NM
     , A.ADSTRD_CD
     , A.ADSTRD_NM
     , A.SUM_POPLTN_CNT
  FROM V_STARBUCKS_POPLTN_INFO A
;

--코드 6-57 뷰 조회 시 WHERE절 사용
SELECT A.BSSH_NO
     , A.CMPNM_NM
     , A.BHF_NM
     , A.ADSTRD_CD
     , A.ADSTRD_NM
     , A.SUM_POPLTN_CNT
  FROM V_STARBUCKS_POPLTN_INFO A
 WHERE ADSTRD_NM LIKE '%경기도%고양시%'
;

--코드 6-58 뷰 제거
DROP VIEW V_STARBUCKS_POPLTN_INFO; 
```

해당 쿼리는 VIEWS 에서 확인이 가능하다.





 



