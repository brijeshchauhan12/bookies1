
package com.ma.bookies1.dto;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class BookDto {
    private Integer userid;
    private Long id; // Assuming id is a Long type
    private String title;
    private String author; // Consider changing to List<String> if supporting multiple authors
    private String isbn;
    private String publisher;

    private Integer pages;
    private String language;
    private String edition;
    private String description;
    private String genre; // Consider changing to List<String> if supporting multiple genres
    private Double price;
    private Integer quantity;
    private String country;
    private String board;
    private String grade;
    private String year;


}