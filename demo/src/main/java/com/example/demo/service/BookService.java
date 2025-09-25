package com.example.demo.service;

import com.example.demo.domain.Book;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);
    Book updateBook(Book book);
    void removeBookById(Integer bookNo);
    Optional<Book> getBookByBookNo(Integer bookNo);
    List<Book> searchBooks(Specification<Book> spec);
}
