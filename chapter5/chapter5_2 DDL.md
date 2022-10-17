<h2>DDL</h2>

<h3>DDL의 정의</h3>
DB를 구성하고 있는 다양한 객체(사용자, 테이블, 인덱스, 뷰, 트리거, 프로시저, 사용자 정의 함수 등)를 정의,변걍,제거하는데 사용한다. <br>
    -> 즉 DDL은 이처럼 물리적 DB객체의 구조를 정의하는 데 사용된다.<br>

<br>


<h3>CREATE TABLE</h3>

테이블 생성하는 문법

```SQL
CREATE TABLE 테이블 명
(
    컬럼명1 ,
    컬럼명2 ,
    컬럼명3 ,
);

CREATE TABLE TB_RN_TMP --도로명임시
(
	RN_CD VARCHAR(12) NOT NULL ,--도로명코드
	RN VARCHAR(120) NOT NULL    --도로명
);
```
위 쿼리를 실행할 경우 TB_RN_TMP 테이블이 생성된다.

>테이블 생성 시 주의 사항
>> * 테이블명은 단수형을 사용할 것을 권고한다.
>> * 테이블명은 특정 사용ㅇ자가 가지고 있는 테이블 내에서 다른 테이블과 중복되면 안 된다.
>> * 한 테이블 내에서 동일한 칼럼명이 존재하면 안된다.
>> * 테이블 생성문 끝은 ';' 으로 끝나야 한다.
>> * 칼럼의 데이터형은 반드시 지정해야 한다.
>> * 테이블명과 칼럼명은 반드시 문자로 시작해야 한다.
>> * 테이블명에 사용하는 문자는 A-Z,a-z,0-9,_$,# 문자만 허용한다.

<BR>
<BR>

<h3>제약조건(Constraint) </h3>
제약조건(Constraint)은 테이블에 입력되는 데이터가 사용자가 원하는 조건을 만족하는 데이터만 삽입되게 하기위한 것이다..<br>
제약조건은 데이터의 무결성을 유지하기 위한 DBMS의 보편적인 방법으로, 테이블의 특정 칼럼에 설정하는 제약이다.

<BR>

>제약조건
>> |제약조건|설명|
>> |-------|----|
>> | 기본키 | <ul>테이블에 저장된 행들 중에서 특정 행을 고유하게 식별하기 위해서 사용한다.</ul><ul>기본키 생성 시 DBMS는 유일 인덱스를 자동으로 생성한다.</ul><ul>기본키는 UNIQUE 제약조건과 NOT NULL 제약조건을 만족해야한다.</ul> |
>> |고유키 | <ul>테이블에 저장된 행들 중에서 특정 행을 고유하게 식별하기 위해 생성한다.</ul><ul>고유키는 UNIQUE 제약조건만 만족하면 NULL 입력이 가능하다.</ul>|
>> |NOT NULL| <ul>해당 칼럼은 필수값 컬럼이므로(NULL입력 금지) 어떤 값이라도 들어와야 한다.</ul>|
>>|CHECK|<ul>입력할 수 있는 값의 종류 혹은 범위를 제한한다.</ul>|
>>|외래키| <ul>다른 테이블의 기본키를 외래키로 지정하는 경우 생성한다.</ul><ul>참조 무결성 제약조건이라고도 한다.</ul>|
>>|기본 값, 디폴트 값| <ul>해당 칼럼에 아무런 값도 입력하지 않았을 때 지정한 디폴트 값으로 데이터가 입력된다.</ul>

<BR>
<BR>

<h3>테이블 생성 및 제약조건 생성 실습 </h3>

<BR>

>부모 테이블 생성
```SQL
--코드 5-2 부모 테이블 생성
CREATE TABLE TB_SUBWAY_STATN_TMP --지하철역임시
(
  SUBWAY_STATN_NO CHAR(6) NOT NULL --지하철역번호
, LN_NM VARCHAR2(50) NOT NULL --노선명
, STATN_NM VARCHAR2(50) NOT NULL --역명
--지하철역번호를 PK로 지정
, CONSTRAINT PK_TB_SUBWAY_STATN_TMP PRIMARY KEY(SUBWAY_STATN_NO) -- 인덱스명
);
```

>자식 테이블명 생성
```SQL
--코드 5-3 자식 테이블 생성
CREATE TABLE TB_SUBWAY_STATN_TK_GFF_TMP --지하철역승하차임시
(
  SUBWAY_STATN_NO CHAR(6) NOT NULL --지하철역번호
, STD_YM CHAR(6) NOT NULL --기준년월
, BEGIN_TIME CHAR(4) NOT NULL --시작시간
, END_TIME CHAR(4) NOT NULL --종료시간
, TK_GFF_SE_CD VARCHAR2(6) NOT NULL --승하차구분코드
, TK_GFF_CNT NUMBER(15) NOT NULL --승하차횟수
, CONSTRAINT PK_TB_SUBWAY_STATN_TK_GFF_TMP PRIMARY KEY (SUBWAY_STATN_NO, STD_YM, BEGIN_TIME, END_TIME, TK_GFF_SE_CD)
); --인덱스명
```

지하철역임시 테이블과 지하철역승하차임시테이블 간에 부모/자식 관계를 설정한다.<br>
외래키를 생성하는 것이며 참조 무결성 제약조건이라고도 한다.

>외래키 생성
```SQL
--코드 5-4 외래키 생성
ALTER TABLE TB_SUBWAY_STATN_TK_GFF_TMP --지하철역승하차임시 테이블에
ADD CONSTRAINT FK_TB_SUBWAY_STATN_TK_GFF_TMP1 --참조 무결성 제약조건을 생성
FOREIGN KEY (SUBWAY_STATN_NO) --지하철역승하차임시 테이블의 지하철역번호 칼럼은
REFERENCES TB_SUBWAY_STATN_TMP (SUBWAY_STATN_NO)--지하철역임시 테이블의 지하철역번호를 참조
;
```
지하철역승하차임시 테이블과 지하철역임시테이블 간의 외래키가 생성 되었으며,
앞으로 지하철역승하차임시 테이블에 데이터 입력시 지하철역번호칼럼은(SUBWAY_STATN_NO) 지하철역 임시 테이블의 지하철역번호(SUBWAY_STATN_NO)칼럼으로 존재해야만 입력이 가능해진다.<BR>
만약 지하철역임시테이블에 존재하지 않는 지하철역 반호를 입력하려고 하면 참조 무결성 제약조건 위반이므로 DBMS에서 입력을 허용하지 않는다.

만일 지하철역임시 테이블에 없는 지하철번호(SUBWAY_STATN_NO)를 가지고 지하철역승하차임시 테이블 행을 추가할 경우
```SQL
SQL Error [2291] [23000]: ORA-02291: integrity constraint (SQLD.FK_TB_SUBWAY_STATN_TK_GFF_TMP1) violated - parent key not found
```
해당 문구 에러 노출된다.

<BR>

<h3>ALTER TABLE</h3>
ALTER TABLE은 칼럼 및 제약조건을 추가/수정/제거하는 데 이용한다.

ALTER TABLE ~ ADD 문을 이용하여 칼럼을 추가할수 있다.

>칼럼 추가
```SQL
ALTER TABLE TB_SUBWAY_STATN_TMP ADD (OPEN_YN CHAR(1));
```
해당 쿼리를 통해 TB_SUBWAY_STATN_TMP 테이블에 OPEN_YN 칼럼을 추가했다.

<br>
<br>

ALTER TABLE ~ DROP COLUMN 문을 이용하여 칼럼을 제거할수 있다.

>칼럼 제거
```SQL
ALTER TABLE TB_SUBWAY_STATN_TMP DROP COLUMN OPEN_YN CHAR(1);
```
해당 쿼리를 통해 TB_SUBWAY_STATN_TMP 테이블에 OPEN_YN 칼럼을 제거했다.

<br>
<br>


ALTER TABLE ~ MODIFY 문을 이용하여 칼럼 데이터형 및 제약조건을 변경할수 있다.

>칼럼 데이터형 및 제약조건을 변경
```SQL
ALTER TABLE TB_SUBWAY_STATN_TMP MODIFY (OPEN_YN NUMBER DEFAULT 0 NOT NULL NOVALIDATE);
```
해당 쿼리를 통해 TB_SUBWAY_STATN_TMP 테이블에 OPEN_YN 칼럼 데이터 형을 CHAR -> NUMBER, DEFAULT 값 추가, NULL 허용 -> NOT NULL 로 수정했다.
<br>
<br>


ALTER TABLE ~ RENAME 컬러명 TO 새컬럼명  문을 이용하여 칼럼명을 변경할수 있다.

>칼럼명 변경
```SQL
ALTER TABLE TB_SUBWAY_STATN_TMP RENAME COLUMN OPER_YN TO OPERATION_YN;
```
해당 쿼리를 통해 OPER_YN 칼럼명에서 OPERATION_YN 칼럼명으로 변경하였다.
<br>
<br>


ALTER TABLE ~ DROP CONSTRAINT  문을 이용하여 칼럼에 걸려있는 제약조건을 제거할수 있다.

>제약조건 제거
```SQL
ALTER TABLE TB_SUBWAY_STATN_TK_GFF_TMP
DROP CONSTRAINT FK_TB_SUBWAY_STATN_TK_GFF_TMP1;
```
해당 쿼리를 통해 TB_SUBWAY_STATN_TK_GFF_TMP테이블에 걸려 있는 FK_TB_SUBWAY_STATN_TK_GFF_TMP1 제약 조건을 제거하였다.
<br>
<br>


RENAME ~ TO  문을 이용하여 테이블명을 변경할수 있다.

>테이블명 변경
```SQL
RENAME TB_SUBWAY_STATN_TK_GFF_TMP TO TB_SUBWAY_STATN_TK_GFF_TMP_2;
```
해당 쿼리를 통해 TB_SUBWAY_STATN_TK_GFF_TMP테이블명을 TB_SUBWAY_STATN_TK_GFF_TMP_2로 변경했다.
<br>
<br>

TRUNCATE문으로 테이블에 저장된 데이터를 제거할 수 있다.<br>
테이블 객체는 그대로 두고 내부의 데이터만 영구적으로 제거한다.<br>
TRUNCATE작업은 ROLLBACK문을 이용한 복구가 불가능하다.(해당 쿼리는 오토커밋이다.)<br>

>TRUNCATE를 이용한 데이터 제거
```SQL
--코드 5-12 테이블 내 데이터 제거
TRUNCATE TABLE TB_SUBWAY_STATN_TK_GFF_TMP_2;
```

<br>
<br>

DROP TABLE문을 사용하여 해당 테이블 객체를 완전히 삭제한다.
>DROP TABLE문을 이용한 테이블 제거
```SQL
--코드 5-13 테이블 제거
DROP TABLE TB_SUBWAY_STATN_TK_GFF_TMP_2;
DROP TABLE TB_SUBWAY_STATN_TMP;
DROP TABLE TB_RN_TMP;
```








