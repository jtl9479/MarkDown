<h1>인덱스 기본</h1>

인덱스란 원하는 데이터를 쉽게 찾을 수 있도록 돕는 책의 찾아보기와 유사한 개념입니다. <br> 검색조건에 부합하는 데이터를 효과적으로 빠르게 검색할 수 있도록 돕는 역활을 한다.
<br>
1개의 테이블은 0개 ~ N개의 인덱스를 가질 수 있으며, 한 테이블에 과도하게 많은 인덱스가 존재할 경우 SQL 작업 시 부하가 발생할 수 있다.

<BR>
<BR>

<H2>B * Tree 인덱스</H2>
DBMS에서 널리 사용되는 가장 일반적인 인덱스로써, 루트블록, 브랜치블록, 리프블록으로 구성됩니다.
<BR>
가장 상위에 존재하는 블록이 루트 블록이고, 브랜치 블록은 분기를 목적으로 하는 블록입니다. 리프 블록은 트리의 가장 아래 단계에 존재하는 블록입니다.리프 블록은 인덱스를 구성하는 칼럼의 데이터와 해당 데이터를 가지고 있는 행의 위치를 가리키는 레코드 식별자인 ROWID로 구성되어 있습니다.

