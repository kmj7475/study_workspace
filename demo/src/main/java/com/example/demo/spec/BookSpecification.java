package com.example.demo.spec;

import com.example.demo.domain.Book;
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
            if (book.getPrice() != null && !book.getPrice().isEmpty())
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
}
