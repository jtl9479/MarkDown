https://cmelcmel.tistory.com/m/34

<H1>GROUP BY, HAVING</H1>

<H2>집계 함수</H2>

집계 함수는 각각의 그룹별로 단 하나의 행을 리턴해주는 함수 입니다.<br>
각각의 그룹별로 리턴되는 단 하나의 행은 집계 결과입니다.<br>

**즉 집계 함수란 여러 행들의 그룹이 모여서 그룹당 단 하나의 결과 행을 되돌려주는 함수입니다.**

집계 함수는 GROUP BY절에 기재한 칼럼을 기준으로 그룹으로 모인 상태에서 각 그룹의 집계를 계산하는 데 사용합니다.<br>

<u>집계 함수는 SELECT절 HAVING절, ORDER BY절에 사용할 수 있습니다.</u>

<H2>ALL과 DISTINCT</H2>

SELECT 문 내 SELECT절에 ALL 또는 DISTINCT를 기재할 수 있습니다.<br>
ALL은 디폴트 설정이며, 아무것도 기재하지 않으면 자동으로 ALL로 설정이 되어진다.&nbsp;DISTINCT를 기재하면 해당 SELECT문의 결과 중 중복값이 제거 되어 노출된다.<br>
ex) SELECT 조회 값이 1,2,2 일경우 ALL을 사용할 경우 1,2,2 전부 노출되지만, DISTINCT를 사용할 경우 중복 값2가 제외된 1,2 만 노출된다.
<br>

<H2>집계 함수의 종류</H2>

|항목|결과|
|----|----|
|COUNT(*)|NULL 값을 포함한 행의 수를 출력한다.|
|COUNT(표현식)|표현식의 값이 NULL이 아닌 행의 수를 출력한다.|
|SUM(표현식)|표현식이 NULL 값인 것을 제외한 합계를 출력한다.|
|AVG(표현식)|표현식이 NULL 값인 것을 제외한 평균을 출력한다.|
|MAX(표현식)|표현식이 NULL 값인 것을 제외한 최댓값을 출력한다.|
|MIN(표현식)|표현식이 NULL 값인 것을 제외한 최솟값을 출력한다.|
|STFFEV(표현식)|표현식이 NULL 값인 것을 제외한 표준편차을 출력한다.|
|VARIANCE(표현식)|표현식이 NULL 값인 것을 제외한 분산을 출력한다.|

COUNT(*)은 NULL값을 포함한 행의 수를 출력한다. 그 외의 함수는 모두 표현식이 NULL 값인 것을 제외한 집계 결과를 되돌려준다.

<H2>공집합과 MAX함수</H2>

공집합이란 SELECT문의 결과가 단 1건도 나오지 않은 경우를 말한다.

```SQL
SELECT 
 		MANAGER_ID
 	,	DEPARTMENT_ID 
 	, 	DEPARTMENT_NAME 
  FROM DEPARTMENTS
 WHERE DEPARTMENT_NAME ='KRK';
```

|MANAGER_ID|DEPARTMENT_ID|DEPARTMENT_NAME|
|----------|-------------|---------------|

해당 조건에 맞는 값이 없으므로 1건의 데이터도 조회가 되지 않았으며,
이러한 현상을 공집합이라 한다.<BR>
간혹 공집합이 아닌 '데이터 없음' 이라는 컬럼 값을 노출하고 싶으면 NVL,   MAX 함수를 사용하면 된다.

```SQL
 SELECT 
 		NVL(TO_CHAR(MAX(MANAGER_ID)),'결과 없음')
 	,	NVL(TO_CHAR(MAX(DEPARTMENT_ID)),'결과 없음')
 	,	NVL(TO_CHAR(MAX(DEPARTMENT_NAME )),'결과 없음')
  FROM DEPARTMENTS
 WHERE DEPARTMENT_NAME ='KRK';

```
|MANAGER_ID|DEPARTMENT_ID|DEPARTMENT_NAME|
|----------|-------------|---------------|
|결과 없음 | 결과 없음| 결과 없음|

해당 쿼리를 조회할 경우 공집합이 아니라 1건의 데이터를 출력한다.

<H2>GROUP BY 절</H2>

GROUP BY 절은 일반적으로 FROM절 아래에 위치한다.<br>
GROUP BY 절에 기재한 컬럼을 기준으로 결과집합을 그룹화 한다.<br>
ex) 일자별 매출 집계를 출력하고 싶다면 일자 칼럼을 GROUP BY절에 기재하고, 매출액을 MAX, MIN등의 함수로 계산한다.<br>

GROUP BY절<br>

 * GROUP BY절을 통해 소그룹별 기준 칼럼을 정한 후, SELECT절에서는 집계 함수를 사용한다.
 * 집계 함수의 결과는 NULL값을 가진 행을 제외하고 수행한다.
 * GROUP BY절에서는 SELECT절과 달리 ALIAS명(별칭)을 사용할 수 없다.
 * <u>GROUP BY절보다 WHERE절이 먼저 수행되므로 집계 함수는 WHERE절에 올수 없다 </u>
 * WHERE절은 전체 데이터를 GROUP으로 나누기 전에 행들을 필터 처리한다.
  즉 WHERE절에 의해 리턴되는 행들을 대상으로 GROUP BY를 한다. 
  그러므로 집계 함수는 WHERE절에는 올 수가 없다.

  ```SQL
 SELECT A.POPLTN_SE_CD 
      , SUM(A.POPLTN_CNT) "인구수 합계"
   FROM TB_POPLTN A
  WHERE A.STD_YM = '202010'
  GROUP BY A.POPLTN_SE_CD;
  ```

A.POPLTN_SE_C 컬럼을 기준으로 A.POPLTN_CNT 합을 구했다.


<H2>HAVING절</H2>

HAVING절<br>

 * WHERE절에서는 집계 함수를 쓸 수 없다.
 * 집계된 결과집합을 기준으로 특정 조건을 주고 싶은 경우 HAVING절을 이용한다.
 * HAVING절은 WHERE절과 비슷하지만 그룹을 나타내는 결과집합의 행에 조건이 적용된다는 점에서 차이가 있다.
 * HAVING절에 들어가는 조건은 GROUP BY절의 기준 항목이나 소그룹의 집계 함수가 사용된다.
 * GROUP BY절에 의한 소그룹별로 만들어진 집계 데이터 중 HAVING절에서 제한 조건을 두어 조건을 만족하는 내영만 출력한다.(GROUP BY -> HAVING)
 * HAVING절은 일반적으로 GROUP BY절 뒤에 위치한다.
 * HAVING절은 일반적으로 GROUP BY절 앞에 나와도 결과는 동일하다.

 HAVING절은 WHERE절과 유사한 기능을 하지만, WHERE절은 테이블에서 추출할 행을 고른다면, HAVING절은 **그룹핑한 결과집합에 대한 조건을 주어 추출할 집계 데이터를 제한하는 역활을 한다.**

```SQL
SELECT 
	  POPLTN_SE_CD AS 성별
	, AGRDE_SE_CD AS 연령층
	, SUM(POPLTN_CNT) AS 인원
  FROM TB_POPLTN
 GROUP BY POPLTN_SE_CD, AGRDE_SE_CD
 HAVING SUM(POPLTN_CNT) < 100000
 ORDER BY AGRDE_SE_CD ;
```

각각의 성별과 연령층을 기준으로(GROUP BY) 인원이 10만 미만인 것(HAVING)을 출력한다.<BR>

<H2>CASE WHEN 사용</H2>

CASE WHEN문을 이용하여 결과집합을 출력할때 사용자가 원하는 조건에 따라 분기처리하여 결과집합을 출력할수 있다.

```SQL
SELECT A.SUBWAY_STATN_NO
     , SUM(CASE WHEN A.TK_GFF_SE_CD = 'TGS001'
                THEN A.TK_GFF_CNT ELSE 0 END) AS "승차인원수합계"
     , SUM(CASE WHEN A.TK_GFF_SE_CD = 'TGS002'
                THEN A.TK_GFF_CNT ELSE 0 END) AS "하차인원수합계"
     , SUM(CASE WHEN A.BEGIN_TIME = '0800' AND A.END_TIME = '0900' AND A.TK_GFF_SE_CD = 'TGS001'
                THEN A.TK_GFF_CNT ELSE 0 END) AS "출근시간대승차인원수합계"
     , SUM(CASE WHEN A.BEGIN_TIME = '0800' AND A.END_TIME = '0900' AND A.TK_GFF_SE_CD = 'TGS002'
                THEN A.TK_GFF_CNT ELSE 0 END) AS "출근시간대하차인원수합계"
     , SUM(CASE WHEN A.BEGIN_TIME = '1800' AND A.END_TIME = '1900' AND A.TK_GFF_SE_CD = 'TGS001'
                THEN A.TK_GFF_CNT ELSE 0 END) AS "퇴근시간대승차인원수합계"
     , SUM(CASE WHEN A.BEGIN_TIME = '1800' AND A.END_TIME = '1900' AND A.TK_GFF_SE_CD = 'TGS002'
                THEN A.TK_GFF_CNT ELSE 0 END) AS "퇴근시간대하차인원수합계"
     , SUM(TK_GFF_CNT) AS "승하차인원수합계"
  FROM TB_SUBWAY_STATN_TK_GFF A
 WHERE A.STD_YM = '202010'
GROUP BY A.SUBWAY_STATN_NO
HAVING SUM(CASE WHEN A.TK_GFF_SE_CD = 'TGS001' --승차인원수
                THEN A.TK_GFF_CNT ELSE 0 END) >= 1000000
    OR SUM(CASE WHEN A.TK_GFF_SE_CD = 'TGS002' --하차인원수
                THEN A.TK_GFF_CNT ELSE 0 END) >= 1000000
;
```







