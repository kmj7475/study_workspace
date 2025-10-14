package com.example.demo.dto;

import lombok.Data;

@Data
public class BookDto {
    private Integer bookNo;
    private String bookName;
    private String author;
    private String company;
    private Long price;
    private java.util.Date publishDate;
    private String category;
    private String about;
    private String image;
    private String isbn;
    private Integer empNo;

    // 요청용 DTO
    @Data
    public static class CreateRequest {
        private String bookName;
        private String author;
        private String company;
        private Long price;
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
        private java.util.Date publishDate;
        private String category;
        private String about;
        private String image;
        private String isbn;
        private Integer empNo;
    }

    // 삭제용 DTO
    @Data
    public static class DeleteRequest {
        private Integer bookNo;
    }

    // 응답용 DTO
    @Data
    public static class Response {
        private Integer bookNo;
        private String bookName;
        private String author;
        private String company;
        private Long price;
        private java.util.Date publishDate;
        private String category;
        private String about;
        private String image;
        private String isbn;
        private Integer empNo;
    }
}
