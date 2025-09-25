package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Book;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    // 단건조회: bookNo 기준
    Optional<Book> findByBookNo(Integer bookNo);
    // 전체조회(다중조건)는 JpaSpecificationExecutor<Book>의 findAll(Specification<Book>) 사용
}