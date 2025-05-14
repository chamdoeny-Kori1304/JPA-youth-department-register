

## Member 구조

```

MemberService ──▶ Members (도메인 리포지토리 인터페이스)
│
▼
MembersJpaAdapter (JPA Adapter)
▼
MemberRepository (Spring Data JPA)

```

## class 이름 설명
Members가 복수형이 이유는 도메인 설계(DDD)에서는 리포지토리를 하나의 "컬렉션처럼 보이게" 하라는 가이드가 있습니다.

| 이름                  | 의미                                      | 비유                      |
| ------------------- | --------------------------------------- | ----------------------- |
| `Members`           | **도메인에서 Member들의 모음**을 의미하는 리포지토리 인터페이스 | 마치 `List<Member>` 같은 느낌 |
| `MemberRepository`  | 기술적으로 JPA를 이용해 DB에 접근하는 **저장소 인터페이스**   | DB 테이블에 직접 매핑되는 객체      |
| `MembersJpaAdapter` | JPA 기반 구현체. 도메인 인터페이스(`Members`)를 구현    | 어댑터(연결자) 역할             |
