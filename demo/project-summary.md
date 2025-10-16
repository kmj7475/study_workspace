# 프로젝트 대화 요약

## 1. Book 도메인 설계 및 개선
- Book 엔티티의 필드 선언을 올바른 자바 문법으로 수정.
- Lombok의 `@Data` 어노테이션을 적용하여 getter/setter/생성자 자동 생성.
- JPA 어노테이션을 활용해 컬럼 제약조건(`nullable`, `length`, `columnDefinition`) 명시.
- `price` 필드에 기본값 0, `publishDate` 필드에 기본값 SYSDATE 적용.

## 2. Employee 도메인 설계
- Employee 엔티티 생성: `empNo`(PK), `empName` 필드 정의.
- Book과 Employee는 다대일(ManyToOne) 관계로 매핑.

## 3. Book-Employee 관계 매핑
- Book: `@ManyToOne`으로 Employee 참조, `@JoinColumn(name = "emp_no")` 사용.
- Employee: `@OneToMany(mappedBy = "employee")`로 Book 리스트 참조.

## 4. Dibs 도메인 설계 및 Book과의 관계
- Dibs 엔티티 생성: `memberNo`(PK), `bookNo`(FK) 필드 유지.
- Book과 Dibs는 일대다(OneToMany) 관계.
- Dibs에서 `@ManyToOne`으로 Book 참조, `@JoinColumn(name = "book_no", insertable = false, updatable = false)`로 매핑.

## 5. BookRepository 구현
- `JpaRepository<Book, Integer>`, `JpaSpecificationExecutor<Book>` 상속.
- `findByBookNo(Integer bookNo)`로 단건조회 메서드 추가.
- 다중조건 검색은 Specification을 활용하여 구현.

## 6. BookSpecification 구현
- Book 도메인 객체를 받아 다중조건 검색을 위한 Specification 제공.
- 각 필드별로 null/empty 체크 후 조건 추가.

## 7. BookService/BookServiceImpl 구현
- CRUD 및 다중조건 검색 메서드 정의.
- 서비스 계층에서 사용하는 이름으로 메서드명 변경(`createBook`, `updateBook`, `removeBookById`, `getBookByBookNo`, `searchBooks`).

## 8. BookController 구현
- 전체조회: `@ModelAttribute Book`을 받아 다중조건 검색.
- 단건조회, 등록, 수정, 삭제 기능 구현.
- 각 기능에 맞는 뷰(book_list.html, book_form.html, book_detail.html)와 연결.

## 9. View 파일(Thymeleaf 템플릿) 작성
- `book_list.html`: 검색 폼 및 도서 목록 테이블.
- `book_form.html`: 도서 등록/수정 폼.
- `book_detail.html`: 도서 상세 정보 및 수정/삭제 버튼.

## 10. 오류 및 개선사항 처리
- JPA 관계 매핑 오류(`referencedColumnName` 불일치) 해결: Book의 PK명과 정확히 일치하도록 수정.
- Repository 파일에는 인터페이스만 작성, 불필요한 코드 제거.

---

**참고:**  
- 모든 엔티티, 리포지토리, 서비스, 컨트롤러, 뷰 파일은 Spring Boot & JPA 표준에 맞게 작성됨.
- 다중조건 검색은 Specification 패턴을 활용.
- Thymeleaf 템플릿은 기본적인 CRUD 흐름에 맞춰 작성됨.
