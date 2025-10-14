package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService; 

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 전체조회 (다중조건 검색)
    @GetMapping
    public String list(@ModelAttribute BookDto bookDto, Model model) {
        List<BookDto.Response> books = bookService.searchBooks(bookDto);
        model.addAttribute("books", books);
        return "book_list";
    }

    // 단건조회
    @GetMapping("/{bookNo}")
    public String detail(@PathVariable Integer bookNo, Model model) {
        Optional<BookDto.Response> book = bookService.getBookByBookNo(bookNo);
        book.ifPresent(b -> model.addAttribute("book", b));
        return "book_detail";
    }

    // 등록 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("book", new BookDto.CreateRequest());
        return "book_form";
    }

    // 등록 처리
    @PostMapping
    public String create(@ModelAttribute BookDto.CreateRequest bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/books";
    }

    // 수정 폼
    @GetMapping("/{bookNo}/edit")
    public String editForm(@PathVariable Integer bookNo, Model model) {
        Optional<BookDto.Response> book = bookService.getBookByBookNo(bookNo);
        book.ifPresent(b -> model.addAttribute("book", b));
        return "book_form";
    }

    // 수정 처리
    @PutMapping("/{bookNo}")
    public String update(@PathVariable Integer bookNo, @ModelAttribute BookDto.UpdateRequest bookDto) {
        bookService.updateBook(bookNo, bookDto);
        return "redirect:/books/" + bookNo;
    }

    // 삭제
    @DeleteMapping("/{bookNo}")
    public String delete(@PathVariable Integer bookNo) {
        bookService.removeBookById(bookNo);
        return "redirect:/books";
    }
}

