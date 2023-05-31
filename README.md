
# QueryDSL

1. JPA에서 기본적으로 Repository에 extends로 많이 사용하는 JpaRepository와 QuerydslPredicateExecutor와 더불어 QuerydslRepositorySupport를 추가함

2. 지원 RestAPI
- GET /api/v1/user : 유저 리스트
- GET /api/v1/user/{name} :  한명의 유저 조회
- POST /api/v1/user : 유저 등록
- PUT /api/v1/user : 유저 수정(핸드폰번호)

3. Custom Exception Response
- 한명의 유저 조회 시 유저가 없는 경우 Custom Exception 처리 후 리턴