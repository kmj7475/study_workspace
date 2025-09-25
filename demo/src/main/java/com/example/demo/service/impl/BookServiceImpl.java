package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void removeBookById(Integer bookNo) {
        bookRepository.deleteById(bookNo);
    }

    @Override
    public Optional<Book> getBookByBookNo(Integer bookNo) {
        return bookRepository.findByBookNo(bookNo);
    }

    @Override
    public List<Book> searchBooks(Specification<Book> spec) {
        return bookRepository.findAll(spec);
    }
}
