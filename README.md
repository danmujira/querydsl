
# QueryDSL & Elasticsearch

0. 테스트 버전
   1. Java 11
   2. SpringBoot 2.5.7
   3. QueryDsl 4.4.0
   4. Docker(ElasticSearch 7.9.1)
1. JPA에서 기본적으로 Repository에 extends로 많이 사용하는 JpaRepository와 QuerydslPredicateExecutor와 더불어 QuerydslRepositorySupport를 추가함
2. spring-boot-starter-data-elasticsearch를 이용하여 ElasticSearch를 연동함.
   1. application-local.yml에서 search.elestic.url 설정
   2. UserDocument 클래스에 index명 설정
   3. bulk의 필드는 id, name, phone, state, role, tag, createAt
3. 지원 RestAPI 
   1. GET /api/v1/user : 유저 리스트 
   2. GET /api/v1/es :  Elasticsearch만을 이용한 검색(name,phone,state,role)
   3. POST /api/v1/user : 유저 등록 
   4. PUT /api/v1/user : 유저 수정(핸드폰번호)
4. Custom Exception Response 
   1. 한명의 유저 조회 시 유저가 없는 경우 Custom Exception 처리 후 리턴