package com.example.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookDto {
    private Integer bookNo;
    private String bookName;
    private String author;
    private String company;
    private Long price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date publishDate;
    private String category;
    private String about;
    private String image;
    private String isbn;
    private Integer empNo;

    // 요청용 DTO
    @Data
    public static class CreateRequest {
        private Integer bookNo;
        private String bookName;
        private String author;
        private String company;
        private Long price;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private java.util.Date publishDate;
        private String category;
        private String about;
        private String image;
        private String isbn;
        private Integer empNo;
    }

    // 수정용 DTO
    @Data
    public static class UpdateRequest {
        private String bookName;
        private String author;
        private String company;
        private Long price;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private java.util.Date publishDate;
        private String category;
        private String about;
        private String image;
        private String isbn;
        private Integer empNo;
    }
    // 응답용 DTO
    @Data
    public static class Response {
        private Integer bookNo;
        private String bookName;
        private String author;
        private String company;
        private Long price;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private java.util.Date publishDate;
        private String category;
        private String about;
        private String image;
        private String isbn;
        private Integer empNo;
    }
}
