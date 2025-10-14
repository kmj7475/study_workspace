package com.example.demo.service;

import com.example.demo.dto.BookDto;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDto.Response createBook(BookDto.CreateRequest dto);
    BookDto.Response updateBook(Integer bookNo, BookDto.UpdateRequest dto);
    void removeBookById(Integer bookNo);
    Optional<BookDto.Response> getBookByBookNo(Integer bookNo);
    List<BookDto.Response> searchBooks(BookDto bookDto); 
}
