<h1>옵티마이저와 실행계획</h1>

<h2>옵티마이저란</h2>
옵티마이저는 사용자가 요청한 SQL문에 대한 최적의 실행 방법을 결정하는 역활을 수행합니다. 
<BR>
옵티마이저가 도출해 낸 실행 방법을 실행계획이라고 합니다.
<BR>
<U>사용자의 요구사항을 만족하는 결과를 추출할 수 있는 다양한 실행 방법들을 도출한 후, 그중에서 최적의 실행 방법을 결정하는 것이</U> 옵티마이저의 역활입니다.

<BR>
<BR>

<h2>비용 기반 옵티마이저</h2>
비용 기반 옵티마이저는 특정 SQL문을 처리하는 데 다양한 실행 계획 중에서 비용이 가장 적게드는 실행계획을 선택하는 방식입니다.
<BR>
비용 기반 옵티마이저는 테이블, 인덱스 등의 통계정보와 시스템 통계정보를 이용하여 최적의 실행계획을 도출합니다.

<BR>
<BR>

<h2>옵티마이저의 구성요소</h2>

<br>
대부분의 프로그래밍 언어는 프로그램을 작성하는 개발자가 어떻게 동작/실행될지를 결정하지만 <U> SQL문은 사용자가 SQL문법에 맞게 SQL문을 작성해서 실행시키면 해당 SQL문 내부에서 어떠한 과정으로 동작/실행될지는 옵티마이저가 결정합니다.</U>
<BR>
결국 SQL문을 작성해서 실행하는 것은 결과집합을 만들거나 데이터 처리를 하기 위한 과정일 뿐이며, 이SQL문을 실행하는 내부 모듈을 만드는 역활은 옵티마이저가 담당합니다.

>사용자는  SQL문을 실행할 뿐이고, 실행한 SQL문이 내부에서 어떻게 실행될지는 옵티마이저가 판단하여 실행 프로시저를 만들고 **그 프로시저가 연산을 수행한 후, 사용자에게 처리결과를 돌려주게 됩니다.**DFSDF

ㅇ<BR>

>|구성요소|설명|
>|-------|----|
>|질의 변환기(Query Transformer)| <ul>사용자가 작성한 SQL문을 옵티마이저가 처리하기에 보다 용이한 형태로 변환한다.</ul>|
>|비용 예측기(Estimator)| <ul>실행된 여러 실행계획들의 비용을 예측하는 모듈이다.</ul><ul>실행계획의 정확한 비용을 측정하기 위해서 연산의 중간집합의 크기 및 결과집합의 크기, 분포도 등의 예측을 한다. 보다 나은 예측을 위해서 정확한 통계정보가 필요하다.</ul>|
>|대안계획 생성기(Plan Generator)| <ul>동일한 결과를 생성하는 다양한 실행계획들을 생성하는 모듈이다.</ul><ul>연산의 적용 순서, 연산 방법 변경, 조인 순서 변경 등을 통해서 다양한 실행계획들을 생성한다.</ul><ul>동일한 결과를 생성하는 가능한 모든 실행계획들을 생성해야 보다 나은 최적화를 찾을수 있다</ul>|

<BR>
<BR>

> 옵티머이저가 착안하는 정보
> * 테이블, 칼럼, 인덱스 구조에 관한 기본 정보
> * 오브젝트 통계 : 테이블 통계, 인덱스 통계, 히스토그램 통계
> * 시스템 통계 : CPU속도, Single Block I/O 속도, Multi Block I/O 속도
> * 옵티마이저 관련 파라미터
> > 옵티마이저는 하나의 SQL문을 호출 시 후보군이 될만한 무수히 많은 실행계획을 도출하며, 짧은 순간 각각의 효율성을 판단하는 역활을 합니다.

<BR>
<BR>

<H2>실행계획 확인 방법</H2>

https://www.youtube.com/watch?v=01VbLThCcm4&ab_channel=%EC%A0%84%EA%B4%91%EC%B2%A0OCP

```SQL

-- Ctrl + Shift + E 실행계획 출력
-- cost는 쿼리를 수행하는 동안 발생될 것으로 예상하는 I/O 횟수 또는 예상 소요시간을 표현한 값이다.
SELECT A.ADSTRD_CD
     , A.STD_YM
     , A.POPLTN_SE_CD
     , A.AGRDE_SE_CD
     , A.POPLTN_CNT
     , B.ADSTRD_NM
  FROM TB_POPLTN A
     , TB_ADSTRD B
 WHERE A.ADSTRD_CD = B.ADSTRD_CD
   AND A.ADSTRD_CD = '1154551000' --서울특별시 금천구 가산동
;

--실행내역은 SQL문을 요청하면 내부적으로 실행계획을 작성하고 실제로 실행까지 한 후 해당 SQL문의 실행이 어떠한 방식으로 되었으며 실제 걸린 시간과 비영은 얼마나 들었는지 나오는 것이다.

--통계레벨 설정 (지금부터 실행되는 SQL문이 실제로 어떠한 동작을 했는지 추적하라는 뜻)
ALTER SESSION SET STATISTICS_LEVEL = ALL;

--코드 7-2 SQL문 실행
-- SELECT 바러 옆에 주석을 달았으며, 해당 주석으로 SQL문을 구분이 가능해진다.
SELECT /* SELECT.TB_POPLTN.TB_ADSTRD.001 */
      A.ADSTRD_CD
    , A.STD_YM
    , A.POPLTN_SE_CD
    , A.AGRDE_SE_CD
    , A.POPLTN_CNT
    , B.ADSTRD_NM
 FROM TB_POPLTN A
    , TB_ADSTRD B
WHERE A.ADSTRD_CD = B.ADSTRD_CD
  AND A.ADSTRD_CD = '1154551000' --서울특별시 금천구 가산동
;

--코드 7-3 SQL문의 실행 정보 조회
--SQL_FULLTEXT 조건으로 해당쿼리 주석을 할 경우 조회가 진행 된다.
--조회 할경우 해당 SQL_ID 및 CHILD_NUMBER 값을 알수 있다.
SELECT SQL_ID
     , CHILD_NUMBER
     , SUBSTR(SQL_FULLTEXT, 1, 40)
  FROM V$SQL
 WHERE SQL_FULLTEXT LIKE '%SELECT.TB_POPLTN.TB_ADSTRD.001%'
   AND SQL_FULLTEXT NOT LIKE '%V$SQL%'
;

--실행내역 출력
-- 첫번째 인자 SQL_ID, 두번째 인자 CHILD_NUMBER 값을 넣어 조회를 진행한다.
SELECT *
  FROM TABLE (DBMS_XPLAN.DISPLAY_CURSOR('bjbwq79yht5nn',0,'ALLSTATS LAST -ROWS'))
;

> 결과
SQL_ID  8b4t71yp1ac9w, child number 0
-------------------------------------
--코드 7-2 SQL문 실행
 SELECT /* SELECT.TB_POPLTN.TB_ADSTRD.001 */
       
A.ADSTRD_CD
     , A.STD_YM
     , A.POPLTN_SE_CD
     , A.AGRDE_SE_CD
 
    , A.POPLTN_CNT
     , B.ADSTRD_NM
  FROM TB_POPLTN A
     , 
TB_ADSTRD B
 WHERE A.ADSTRD_CD = B.ADSTRD_CD
   AND A.ADSTRD_CD = 
'1154551000' --서울특별시 금천구 가산동

 
Plan hash value: 1817048328
 
--------------------------------------------------------------------------------------------------
| Id  | Operation                    | Name             | Starts | A-Rows |   A-Time   | Buffers |
--------------------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT             |                  |      1 |     33 |00:00:00.01 |      41 |
|   1 |  NESTED LOOPS                |                  |      1 |     33 |00:00:00.01 |      41 |
|   2 |   TABLE ACCESS BY INDEX ROWID| TB_ADSTRD        |      1 |      1 |00:00:00.01 |       3 |
|*  3 |    INDEX UNIQUE SCAN         | PK_TB_ADSTRD     |      1 |      1 |00:00:00.01 |       2 |
|   4 |   TABLE ACCESS BY INDEX ROWID| TB_POPLTN        |      1 |     33 |00:00:00.01 |      38 |
|*  5 |    INDEX RANGE SCAN          | IDX_TB_POPLTN_03 |      1 |     33 |00:00:00.01 |       5 |
--------------------------------------------------------------------------------------------------
 
Predicate Information (identified by operation id):
---------------------------------------------------
 
   3 - access("B"."ADSTRD_CD"='1154551000')
   5 - access("A"."ADSTRD_CD"='1154551000')
 
```





