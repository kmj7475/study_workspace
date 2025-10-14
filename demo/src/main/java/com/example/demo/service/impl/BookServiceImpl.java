package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.domain.Employee;
import com.example.demo.dto.BookDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.spec.BookSpecification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private BookDto.Response toResponse(Book book) {
        if (book == null) return null;
        BookDto.Response dto = new BookDto.Response();
        dto.setBookNo(book.getBookNo());
        dto.setBookName(book.getBookName());
        dto.setAuthor(book.getAuthor());
        dto.setCompany(book.getCompany());
        dto.setPrice(book.getPrice());
        dto.setPublishDate(book.getPublishDate());
        dto.setCategory(book.getCategory());
        dto.setAbout(book.getAbout());
        dto.setImage(book.getImage());
        dto.setIsbn(book.getIsbn());
        dto.setEmpNo(book.getEmployee() != null ? book.getEmployee().getEmpNo() : null);
        return dto;
    }

    private void updateEntity(Book book, BookDto.UpdateRequest dto) {
        book.setBookName(dto.getBookName());
        book.setAuthor(dto.getAuthor());
        book.setCompany(dto.getCompany());
        book.setPrice(dto.getPrice());
        book.setPublishDate(dto.getPublishDate());
        book.setCategory(dto.getCategory());
        book.setAbout(dto.getAbout());
        book.setImage(dto.getImage());
        book.setIsbn(dto.getIsbn());        
         /* 
        //Employee 기능이 완성된 후 변경
        if (book.getEmployee() != null) {
            book.getEmployee().setEmpNo(dto.getEmpNo());
        }else{
            Employee employee = new Employee();
            employee.setEmpNo(dto.getEmpNo());
            book.setEmployee(employee); 
        }   
        */
    }

    @Override
    public BookDto.Response createBook(BookDto.CreateRequest dto) {
        Book book = new Book();
        book.setBookName(dto.getBookName());
        book.setAuthor(dto.getAuthor());
        book.setCompany(dto.getCompany());
        book.setPrice(dto.getPrice());
        book.setPublishDate(dto.getPublishDate());
        book.setCategory(dto.getCategory());
        book.setAbout(dto.getAbout());
        book.setImage(dto.getImage());
        book.setIsbn(dto.getIsbn());        
         /* 
        //Employee 기능이 완성된 후 변경
        Employee employee = new Employee();
        employee.setEmpNo(dto.getEmpNo());
        book.setEmployee(employee); 
        */
        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    @Override
    public BookDto.Response updateBook(Integer bookNo, BookDto.UpdateRequest dto) {
        Optional<Book> optional = bookRepository.findByBookNo(bookNo);
        if (optional.isEmpty()) return null;
        Book book = optional.get();
        updateEntity(book, dto);
        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    @Override
    public void removeBookById(Integer bookNo) {
        bookRepository.deleteById(bookNo);
    }

    @Override
    public Optional<BookDto.Response> getBookByBookNo(Integer bookNo) {
        return bookRepository.findByBookNo(bookNo).map(this::toResponse);
    }

    @Override
    public List<BookDto.Response> searchBooks(BookDto bookDto) {
        Specification<Book> spec = BookSpecification.search(bookDto);
        return bookRepository.findAll(spec)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}

