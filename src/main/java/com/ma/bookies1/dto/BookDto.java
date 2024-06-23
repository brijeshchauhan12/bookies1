package com.ma.bookies1.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class BookDto {
    private String title;
    private String author;

    private String grade;

    private Integer userid;
}
