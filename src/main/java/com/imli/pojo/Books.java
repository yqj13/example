package com.imli.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    private Integer id;
    private String bookName;
    private String author;
    private String bookStyle;
    private Integer bookPrice;
}
