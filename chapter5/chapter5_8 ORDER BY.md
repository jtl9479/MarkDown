<h1>ORDER BY 절</h1>

<h2>ORDER BY 절 개요</h2>

ORDER BY 절은 SELECT문에서 조회한 데이터 집합을 특정 칼럼 기준으로 정렬한 후 데이터를 출력한다.

**ORDER BY절에 대한 설명**
 * ORDER BY절은 SQL문장으로 조회된 데이터를 다양한 목적에 맞게, 특정 칼럼을 기준으로 정렬하여 출력하는 데 사용한다.
 * ORDER BY절에 칼럼명 대신에 SELECT 절에서 사용한 별칭명이나 칼럼 순서를 나타내는 정수도 사용 가능하다(SELECT -> ORDER BY)
 * ORDER BY절에 선언한 칼럼 뒤에 정렬 방식을 선언할 수 있으며 오름차순(ASC-> 디폴트 값), 내림차순(DESC)가 있다.
 * 숫자형 데이터는 오름차순으로 정렬할 경우 가장 작은 값부터 출력, 날짜형 데이터는 가장 빠른 날짜가 먼저 출력된다.
 * NULL 값을 가장 큰 값으로 간주하여 오름차순으로 정렬할 경우 가장 마지막에 내림차순으로 정렬 했을 경우 제일 먼저 노출된다.
 * ORDER BY절을 수행할 때 SELECT절에 선언하지 않은 칼럼을 기준으로도 정렬이 가능하다.

<h2>SELECT문의 실행 순서</h2>

|순서|절|설명|
|---|--|----|
|1|FROM|조회 테이블을 참조한다.|
|2|WHERE|조회 대상 행을 조회한다.|
|3|GROUP BY|대상 행을 그룹화 한다.|
|4|HAVING|그룹화한 값에서 조건에 맞는 값을 조회한다.|
|5|SELECT|SELECT절에 선언한 칼럼이나 식을 계산한다.|
|6|ORDER BY|출력되는 결과집합을 정렬한다.|

<u>SELECT문을 실행하면 우선 FROM절에서 조회대상 테이블을 참조한 후 WHERE절에 선언한 조회대상 행을 조회한다. 그 후 GROUP BY절에 선언한 칼럼들을 기준으로 그룹화한 후 HAVING절에 있는 조건으로 그룹화된 집합에서 다시 조회한다. 그 후 SELECT절에 기재한 칼럼을 출력하고 마지막으로 ORDER BY절에 선언한 칼럼을 기준으로 정렬한 데이터를 사용자에게 노출한다.</u>

<BR>


<h2>GROUP BY절 사용 시 정렬작업</h2>
ORDER BY절을 사용할 떄 SELECT문에 GROUP BY절을 사용한 경우 반드시 SELECT절에 선언한 칼럼 혹은 표현식을 ORDER BY절에 선언해야합니다.<BR>

```SQL
SELECT SUM(POPLTN_CNT) AS T
	 , AGRDE_SE_CD
  FROM TB_POPLTN
 GROUP BY AGRDE_SE_CD
 ORDER BY T
```

ORDER BY 선언할때 SELECT절에 선언한 칼럼이나 GROUP BY절에서 선언한 칼럼을 적용하지 않을 경우

```SQL
SQL Error [979] [42000]: ORA-00979: not a GROUP BY expression
```
해당 에러가 발생한다.

<BR>

<h2>GROUP BY절과 부분 범위 정리</h2>

데이터가 너무 많은 테이블에서 부분적으로 10건의 데이터만 조회할 경우
ROWNUM,ORDER BY절을 이용하여 상위 10건의 데이터를 조회할수 있다.

```SQL
SELECT *
  FROM TB_BSSH
 WHERE ROWNUM <=10
 ORDER BY LO;
```
해당 쿼리를 이용할 경우 많은 데이터를 일일히 조회하지 않고 상위 10건의 데이터만 조회가 가능하다.

하지만 만일 사용자가 'LO' 칼럼을 기준으로 상위 10건의 데이터를 조회하고자 했을 경우 제대로 데이터가 출력되지 않을 수 있다.
해당 쿼리에서는 'LO'으로 정렬하는것 보다 ROWNUM을 통해 임의로 10개의 데이터를 조회하는것이 먼저이기 떄문이다.

만일 **전체데이터 'LO' 기준으로 상위 데이터 10개를 뽑고자 한다면**
```SQL
SELECT A.*
  FROM (
  	SELECT 
  			LO
  		,	CMPNM_NM
	  FROM TB_BSSH
	 ORDER BY LO
  ) A
 WHERE ROWNUM <=10;
```
서브쿼리를 통해 정렬을 진행 후 ROWNUM으로 10개의 데이터를 조회해야한다, 





