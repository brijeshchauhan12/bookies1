
package com.ma.bookies1.dto;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class bookDto2 {
    private Integer Userid;
    private Long Id; // Assuming id is a Long type
    private String Title;
    private String Author; // Consider changing to List<String> if supporting multiple authors
    private String Isbn;
    private String Publisher;
    private Integer Pages;
    private String Language;
    private String Edition;
    private String Description;
    private String Genre; // Consider changing to List<String> if supporting multiple genres
    private Double Price;
    private Integer Quantity;
    private String Country;
    private String Board;
    private String Grade;
    private String Year;
}