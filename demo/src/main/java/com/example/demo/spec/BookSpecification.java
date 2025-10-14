package com.example.demo.spec;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookDto;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> search(Book book) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();
            if (book.getBookName() != null && !book.getBookName().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("bookName"), "%" + book.getBookName() + "%"));
            if (book.getAuthor() != null && !book.getAuthor().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("author"), "%" + book.getAuthor() + "%"));
            if (book.getCompany() != null && !book.getCompany().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("company"), "%" + book.getCompany() + "%"));
            if (book.getPrice() > 0 )
                predicate = cb.and(predicate, cb.equal(root.get("price"), book.getPrice()));
            if (book.getCategory() != null && !book.getCategory().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("category"), "%" + book.getCategory() + "%"));
            if (book.getAbout() != null && !book.getAbout().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("about"), "%" + book.getAbout() + "%"));
            if (book.getImage() != null && !book.getImage().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("image"), "%" + book.getImage() + "%"));
            if (book.getIsbn() != null && !book.getIsbn().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("isbn"), "%" + book.getIsbn() + "%"));
            return predicate;
        };
    }

    // BookDto를 매개변수로 받는 오버로딩 메서드
    public static Specification<Book> search(BookDto bookDto) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();
            if (bookDto.getBookName() != null && !bookDto.getBookName().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("bookName"), "%" + bookDto.getBookName() + "%"));
            if (bookDto.getAuthor() != null && !bookDto.getAuthor().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("author"), "%" + bookDto.getAuthor() + "%"));
            if (bookDto.getCompany() != null && !bookDto.getCompany().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("company"), "%" + bookDto.getCompany() + "%"));
            if (bookDto.getPrice() != null)
                predicate = cb.and(predicate, cb.equal(root.get("price"), bookDto.getPrice()));
            if (bookDto.getCategory() != null && !bookDto.getCategory().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("category"), "%" + bookDto.getCategory() + "%"));
            if (bookDto.getAbout() != null && !bookDto.getAbout().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("about"), "%" + bookDto.getAbout() + "%"));
            if (bookDto.getImage() != null && !bookDto.getImage().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("image"), "%" + bookDto.getImage() + "%"));
            if (bookDto.getIsbn() != null && !bookDto.getIsbn().isEmpty())
                predicate = cb.and(predicate, cb.like(root.get("isbn"), "%" + bookDto.getIsbn() + "%"));
            return predicate;
        };
    }
}
