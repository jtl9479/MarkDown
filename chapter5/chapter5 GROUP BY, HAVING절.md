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







